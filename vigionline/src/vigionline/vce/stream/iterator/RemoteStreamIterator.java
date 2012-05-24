package vigionline.vce.stream.iterator;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.http.client.ClientProtocolException;

import vigionline.common.model.Model;
import vigionline.vce.stream.ConnectionManager;
import vigionline.vce.stream.StreamParser;

public class RemoteStreamIterator extends StreamIterator<byte[]> {

	protected StreamParser _streamParser;
	private ConnectionManager _connectionManager;

	public RemoteStreamIterator(ConnectionManager conManager, Model model)
			throws ClientProtocolException, IOException {
		this._connectionManager = conManager;
		this._streamParser = new StreamParser(conManager.getInputStream(),
				model);
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

	@Override
	public void shutdown() {
		_connectionManager.shutdown();
	}
}
