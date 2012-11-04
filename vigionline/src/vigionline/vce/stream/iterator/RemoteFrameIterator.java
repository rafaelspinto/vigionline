package vigionline.vce.stream.iterator;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.apache.http.client.ClientProtocolException;
import vigionline.common.model.Model;
import vigionline.vce.connection.ConnectionManager;
import vigionline.vce.exception.EndOfStreamException;
import vigionline.vce.stream.parser.FrameParserWithDate;
import vigionline.vce.stream.parser.IFrameParser;
import vigionline.vce.stream.parser.JpegParser;

public class RemoteFrameIterator extends AbstractFrameIterator<byte[]> {

    protected IFrameParser _streamParser;

    public RemoteFrameIterator(ConnectionManager conManager, Model model) throws ClientProtocolException, IOException, EndOfStreamException {
        this._streamParser = new FrameParserWithDate(new JpegParser(conManager.getInputStream()));
        this._next = _streamParser.getNextFrame();
    }

    @Override
    public boolean hasNext() {
        return _next != null;
    }

    @Override
    public byte[] next() throws EndOfStreamException {
        if (!hasNext()) {
            throw new EndOfStreamException();
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
    public void shutdown() {
        _streamParser = null;
        _next = null;
    }
}
