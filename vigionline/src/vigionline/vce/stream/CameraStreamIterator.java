package vigionline.vce.stream;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CameraStreamIterator implements Iterator<byte[]> {

	private StreamParser _streamParser;
	private byte[] _prev, _next;

	public CameraStreamIterator(StreamParser streamParser) {
		this._streamParser = streamParser;
		this._next = _streamParser.readImageFromStream();
	}

	@Override
	public boolean hasNext() {
		return _next != null && !_streamParser.isEndOfStream();
	}

	@Override
	public byte[] next() {
		if (!hasNext())
			throw new NoSuchElementException();
		_prev = _next;
		_next = _streamParser.readImageFromStream();
		return _prev;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public boolean isEndOfStream() {
		return _streamParser.isEndOfStream();
	}
}
