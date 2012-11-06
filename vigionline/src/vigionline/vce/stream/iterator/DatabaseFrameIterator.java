package vigionline.vce.stream.iterator;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import vigionline.common.Utils;
import vigionline.vce.database.mapper.ImageMapper;
import vigionline.vce.stream.virtual.Messages;

public class DatabaseFrameIterator extends AbstractFrameIterator<Messages.Message> {

    private ResultSet _cursor;
    private boolean _hasNext;

    public DatabaseFrameIterator(int idCamera, Date initialDate)
            throws SQLException {
        this._cursor = new ImageMapper().getImagesFrom(idCamera, initialDate);
    }

    @Override
    public boolean hasNext() {
        try {
            return _cursor.next();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Messages.Message next() {
        try {
            /**
             * File might not exist in filesystem *
             */
            if (_cursor.isLast()) {
                return new Messages.TerminateMessage();
            }
            byte[] image = Utils.jpegToByteArray(new File(_cursor
                    .getString("filename")));

            Messages.FrameMessage img = new Messages.FrameMessage();
            img.frame = image;
            return img;

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return new Messages.TerminateMessage();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void shutdown() {
        try {
            _cursor.close();
            _cursor = null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
