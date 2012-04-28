package vigionline.vce.stream;

import java.io.IOException;

import vigionline.common.model.Model;

public class CameraStreamReaderFactory {

	private ConnectionManager _conManager;
	private StreamParser _streamParser;
	private CameraStreamIterator _cameraStreamIterator;

	public CameraStreamReaderFactory(ConnectionManager conManager, Model model)
			throws IOException {
		_streamParser = new StreamParser(conManager.getInputStream(), model);
		_cameraStreamIterator = new CameraStreamIterator(_streamParser);
	}

	public ConnectionManager getConnectionManager() {
		return _conManager;
	}

	public StreamParser getStreamParser() {
		return _streamParser;
	}

	public CameraStreamIterator getCameraStreamIterator() {
		return _cameraStreamIterator;
	}
}
