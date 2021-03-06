package vigionline.vce.record;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import vigionline.common.configuration.ConfigurationManager;
import vigionline.common.model.Camera;
import vigionline.common.model.Image;
import vigionline.common.model.Model;
import vigionline.vce.database.mapper.ImageMapper;
import vigionline.vce.exception.EndOfStreamException;
import vigionline.vce.stream.iterator.AbstractLocalFrameIterator;
import vigionline.vce.stream.iterator.FrameIteratorFactory;
import vigionline.vce.stream.virtual.Messages;
import vigionline.vce.stream.virtual.StreamHandler;

public class Recorder implements Runnable {

	public Boolean STOP_RECORDING = Boolean.FALSE;
	private Camera _camera;
	private Model _model;
	private ImageMapper _imageMapper;
	private StreamHandler _streamHandler;
	private String _directory;
	private long _timestamp;
	private final long MAXIMUM_LAST_RECORDING_TIME = 1000 * 30;

	public Recorder(StreamHandler streamHandler, Camera camera, Model model) {
		this._streamHandler = streamHandler;
		this._camera = camera;
		this._model = model;
		this._directory = ConfigurationManager.getInstance()
			.getImageDirectory();
		this._imageMapper = new ImageMapper();
	}

	@Override
	public void run() {
		while (!STOP_RECORDING.booleanValue()) {
			try ( 
				AbstractLocalFrameIterator iterator = FrameIteratorFactory.getNonDiscardingLocalStreamIterator(_streamHandler, _camera, _model)
			    ){
				while (iterator.hasNext() && !STOP_RECORDING.booleanValue()) {
					Messages.Message msg = iterator.next();
					if (msg instanceof Messages.TerminateMessage) {
						STOP_RECORDING = Boolean.TRUE;
						break;
					}
					byte[] image = ((Messages.FrameMessage) msg).frame;
					try {
						storeFrame(image);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public boolean stillRecording() {
		return _timestamp > (System.currentTimeMillis() - MAXIMUM_LAST_RECORDING_TIME);
	}

	private String writeToDisk(byte[] img, Date date) throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		_timestamp = date.getTime();

		String dirname = _directory + "/" + cal.get(Calendar.YEAR) + "/"
			+ (cal.get(Calendar.MONTH) + 1) + "/"
			+ cal.get(Calendar.DAY_OF_MONTH) + "/"
			+ cal.get(Calendar.HOUR_OF_DAY) + "/camera"
			+ _camera.getIdCamera() + "/";

		String filename = dirname + _timestamp + ".jpg";

		File dir = new File(dirname);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File file = new File(filename);
		BufferedOutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(file));
			os.write(img);
		} finally {
			if (os != null) {
				os.flush();
				os.close();
			}
			os = null;
			file = null;
			dir = null;
			cal = null;
		}
		return filename;
	}

	private void storeFrame(byte[] img) throws SQLException {
		Image image = new Image();
		Date date = new Date(System.currentTimeMillis());
		try {
			String filename = writeToDisk(img, date);
			image.setIdCamera(_camera.getIdCamera());
			image.setFilename(filename);
			image.setSize(img.length);
			image.setDate(date);
			_imageMapper.insert(image);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			img = null;
			image = null;
		}
	}
}
