package vigionline.vce.stream;

import java.io.DataInputStream;
import java.io.IOException;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;

public class CameraStreamReaderFactory {

	private ConnectionManager _conManager;
	private StreamParser _streamParser;
	private CameraStreamReader _cameraStreamReader;

	public CameraStreamReaderFactory(Camera camera, Model model) throws IOException {
		_conManager = new ConnectionManager(camera.getUrl()+":"+camera.getPort()+"/"+model.getVideoUrl(),
				camera.getUsername(), camera.getPassword());
		_streamParser = new StreamParser(new DataInputStream(_conManager
				.getConnection().getInputStream()), model);
		_cameraStreamReader = new CameraStreamReader(_streamParser);
	}

	public ConnectionManager getConnectionManager() {
		return _conManager;
	}

	public StreamParser getStreamParser() {
		return _streamParser;
	}

	public CameraStreamReader getCameraStreamReader() {
		return _cameraStreamReader;
	}
}
