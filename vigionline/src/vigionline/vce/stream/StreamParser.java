package vigionline.vce.stream;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import vigionline.common.model.Model;

public class StreamParser {
	private DataInputStream _dis;
	private Model _model;

	public StreamParser(DataInputStream dis, Model model) {
		_dis = dis;
		_model = model;
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
							read = _dis.read(byteBuf, 0, lineEndBytes.length);
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
					curr = _dis.read();
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
			try {
				outputStream.close();
			} catch (IOException e) {
				// VOID
			}
			outputStream = null;
		}
		return imageInByte;
	}

	private byte[] readMJPGStream() {
		byte[] image = null;
		if (_dis != null) {
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
			while (counter < n && (read = _dis.read()) != -1) {
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
}
