package vigionline.vce.stream.parser;

import vigionline.vce.exception.EndOfStreamException;

public interface IFrameParser {
    byte[] getNextFrame() throws EndOfStreamException;
}
