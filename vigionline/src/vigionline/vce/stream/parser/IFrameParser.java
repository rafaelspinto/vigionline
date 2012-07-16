package vigionline.vce.stream.parser;

import java.io.InputStream;

public interface IFrameParser {
	
	byte[] getNextFrame();
	boolean isEndOfStream();
	void setInputStream(InputStream inputStream);
}
