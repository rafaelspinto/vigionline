package vigionline.vce.stream;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import vigionline.common.model.Model;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class StreamParser {
	private BufferedInputStream _bis;
	private Model _model;
	private boolean _isEndOfStream;

	public StreamParser(InputStream inputStream, Model model) {
		_bis = new BufferedInputStream(inputStream);
		_model = model;
		_isEndOfStream = false;
	}

	private int removeEncapsulation(int n) {
		int counter = 0;
		for (int i = 0; i < n; ++i) {
			boolean endOfEncapsulation = false;
			String lineEnd = "\n";
			int read = 0;
			byte[] lineEndBytes = lineEnd.getBytes();
			byte[] byteBuf = new byte[lineEndBytes.length];

			try {
				while (!endOfEncapsulation && read != -1) {
					String t = "";
					if (byteBuf != null) {
						try {
							read = _bis.read(byteBuf, 0, lineEndBytes.length);
							t = new String(byteBuf);
							if (t.equals(lineEnd))
								endOfEncapsulation = true;
						} catch (Exception e) {
							endOfEncapsulation = true;
						}
					}
				}
				counter += byteBuf.length;
			} finally {
				byteBuf = null;
				lineEndBytes = null;
			}
		}
		return counter;
	}

	private byte[] readJPG() {
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
			} catch (IOException e) {
				// VOID
			}
			outputStream = null;
		}
		try (ByteArrayInputStream bais = new ByteArrayInputStream(imageInByte);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		) {
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(bais);
			BufferedImage im = decoder.decodeAsBufferedImage();
			Graphics2D g = im.createGraphics();
			String date = millisToString(System.currentTimeMillis());

			int wt, x, width = im.getWidth(), y, height = im.getHeight();
			Font dataFont = new Font("Monospaced", Font.BOLD, 20);
			FontMetrics fm = g.getFontMetrics(dataFont);
			wt = fm.stringWidth(date);
			int ht = fm.getHeight();

			x = (width - wt) >> 1;
			y = height - 5;

			g.setColor(Color.BLACK);
			g.fillRect(x, y - ht + 1, wt + 4, ht + 2);
			g.setColor(Color.WHITE);
			g.setFont(dataFont);
			g.drawString(date, x, y);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
			encoder.encode(im);
			return baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageInByte;
	}

	private byte[] readMJPGStream() {
		byte[] image = null;
		if (_bis != null) {
			removeEncapsulation(_model.getBeginLinesToDiscard());
			image = readJPG();
			removeEncapsulation(_model.getEndLinesToDiscard());
		}
		return image;
	}

	public byte[] readEncapsulation(int n) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int counter = 0;
		int read = 0;
		byte[] result = null;
		try {
			while (counter < n && (read = _bis.read()) != -1) {
				bos.write(read);
				if (read == '\n')
					counter++;
			}
			result = bos.toByteArray();
		} finally {
			bos.close();
			bos = null;
		}
		return result;
	}

	public byte[] readImageFromStream() {
		if (_model.isMJPEG())
			return readMJPGStream();
		else
			return readJPG();
	}

	public boolean isEndOfStream() {
		return _isEndOfStream;
	}

	private String millisToString(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		int mil = cal.get(Calendar.MILLISECOND);

		String data = String.format("%02d-%02d-%04d %02d:%02d:%02d.%03d", day,
				mon, year, hour, min, sec, mil);
		return data;
	}
}
