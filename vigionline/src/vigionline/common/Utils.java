package vigionline.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
				throw new RuntimeException(e);
			}
		} catch (FileNotFoundException e) {
			return null;
		}
		return imageInBytes;
	}
	
	public static boolean deleteFile(String filename)
	{
		File file = new File(filename);
		return file.delete();
	}
	
	public static java.sql.Date makeDateFromFormFields(String day, int hour, int min) throws ParseException
	{
		SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new java.sql.Date(parser.parse(day).getTime());
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		date.add(Calendar.HOUR_OF_DAY, hour);
		date.add(Calendar.MINUTE, min);

		return new java.sql.Date(date.getTimeInMillis());
	}
}
