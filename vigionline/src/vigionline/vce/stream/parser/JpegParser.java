package vigionline.vce.stream.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JpegParser implements IFrameParser {
	private InputStream _bis;
	private boolean _isEndOfStream;

	public JpegParser(InputStream inputStream) {
		_bis = inputStream;
		_isEndOfStream = false;
	}

	public void setInputStream(InputStream inputStream) {
		_bis = inputStream;
	}

	public byte[] getNextFrame() {
		return _bis != null ? parseJpeg() : null;
	}

	public boolean isEndOfStream() {
		return _isEndOfStream;
	}

	private byte[] parseJpeg() {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			byte[] beg = moveToBeginningOfFrame();
			if (beg != null) {
				outputStream.write(beg);
				byte[] img = moveToEndOfFrame();
				if (img != null) {
					outputStream.write(img);
					return outputStream.toByteArray();
				}
			}
		} catch (IOException ioe) {
		}
		return null;
	}

	private byte[] moveToBeginningOfFrame() {
		try {
			int prev = 0, curr = 0;
			boolean beginningOfFrame = false;

			while (!beginningOfFrame && curr != -1) {
				curr = _bis.read();
				if (prev == 0xff && curr == 0xd8) // beginning of JPEG
				{
					beginningOfFrame = true;
					return new byte[] { (byte) 0xff, (byte) 0xd8 };
				}
				prev = curr;
				if (curr == -1)
					_isEndOfStream = true;
			}
		} catch (IOException e) {
		}
		return null;
	}

	private byte[] moveToEndOfFrame() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		int prev = 0, curr = 0;
		boolean endOfJPEG = false;
		boolean goodJPEG = false;

		try {
			while (!endOfJPEG && curr != -1) {
				try {
					curr = _bis.read();
					if (prev == 0xff && curr == 0xd9) // end of JPEG 0xFF
					{
						endOfJPEG = true;
						goodJPEG = true;
					}
					prev = curr;
					outputStream.write(curr);
					if (goodJPEG)
						imageInByte = outputStream.toByteArray();
				} catch (IOException e) {
					endOfJPEG = true;
				}
			}
		} finally {
			if (curr == -1)
				_isEndOfStream = true;
			try {
				outputStream.close();
			} catch (IOException e) {}
			outputStream = null;
		}
		return imageInByte;
	}
}