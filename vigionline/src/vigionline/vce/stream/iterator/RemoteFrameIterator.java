package vigionline.vce.stream.iterator;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.apache.http.client.ClientProtocolException;
import vigionline.common.model.Model;
import vigionline.vce.connection.ConnectionManager;
import vigionline.vce.stream.parser.FrameParserWithDate;
import vigionline.vce.stream.parser.IFrameParser;
import vigionline.vce.stream.parser.JpegParser;

public class RemoteFrameIterator extends AbstractFrameIterator<byte[]> {

    protected IFrameParser _streamParser;

    public RemoteFrameIterator(ConnectionManager conManager, Model model) throws ClientProtocolException, IOException {
        this._streamParser = new FrameParserWithDate(new JpegParser(conManager.getInputStream()));
        this._next = _streamParser.getNextFrame();
    }

    @Override
    public boolean hasNext() {
        return _next != null && !_streamParser.isEndOfStream();
    }

    @Override
    public byte[] next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        _prev = _next;
        _next = _streamParser.getNextFrame();
        return _prev;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEndOfStream() {
        return _streamParser.isEndOfStream();
    }

    @Override
    public void shutdown() {
        _streamParser = null;
        _next = null;
    }
}
