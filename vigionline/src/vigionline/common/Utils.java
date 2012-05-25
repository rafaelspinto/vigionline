package vigionline.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utils {
	public static byte[] jpegToByteArray(File file) {
		byte[] imageInBytes = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			try {
				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
			} catch (IOException ex) {
				return null;
			}
			imageInBytes = bos.toByteArray();
			try {
				bos.close();
				fis.close();
			} catch (IOException e) {
				// TODO : Treat exceptions
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			return null;
		}
		return imageInBytes;
	}
}
