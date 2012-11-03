package vigionline.vce.stream.parser;

public interface IFrameParser {
    byte[] getNextFrame();
    boolean isEndOfStream();
}
