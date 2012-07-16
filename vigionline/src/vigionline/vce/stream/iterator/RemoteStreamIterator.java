package vigionline.vce.stream.iterator;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.http.client.ClientProtocolException;

import vigionline.common.model.Model;
import vigionline.vce.connection.ConnectionManager;
import vigionline.vce.stream.parser.FrameParserWithDate;
import vigionline.vce.stream.parser.IFrameParser;
import vigionline.vce.stream.parser.JpegParser;

public class RemoteStreamIterator extends StreamIterator<byte[]> {

	protected IFrameParser _streamParser;
	private ConnectionManager _connectionManager;
	private Model _model;

	public RemoteStreamIterator(ConnectionManager conManager, Model model)
			throws ClientProtocolException, IOException {
		this._connectionManager = conManager;
		this._streamParser = new FrameParserWithDate(new JpegParser(conManager.getInputStream()));
		this._next = _streamParser.getNextFrame();
		this._model = model;
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
		try {
			if (!_model.isMJPEG())
				_streamParser.setInputStream(_connectionManager.getInputStream());
			_next = _streamParser.getNextFrame();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
