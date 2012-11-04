package vigionline.vce.stream.parser;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import javax.imageio.ImageIO;
import vigionline.vce.exception.EndOfStreamException;

public class FrameParserWithDate implements IFrameParser {

    private IFrameParser _parser;

    public FrameParserWithDate(IFrameParser parser) {
        this._parser = parser;
    }

    @Override
    public byte[] getNextFrame() throws EndOfStreamException {
        return addDateToImage(_parser.getNextFrame());
    }

    private byte[] addDateToImage(byte[] originalImage) {
        if (originalImage == null) {
            return null;
        }
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(originalImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            BufferedImage im = ImageIO.read(bais);
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

            ImageIO.write(im, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            return originalImage;
        }
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
