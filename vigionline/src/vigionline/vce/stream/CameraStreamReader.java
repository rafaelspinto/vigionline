package vigionline.vce.stream;

public class CameraStreamReader extends AbstractStreamReader {

	private StreamParser _streamParser;

	public CameraStreamReader(StreamParser streamParser) {
		_streamParser = streamParser;
	}

	protected byte[] getNextImage() {
		return _streamParser.readImageFromStream();
	}

	@Override
	protected byte[] getPrevImage() {
		throw new UnsupportedOperationException();
	}
}
