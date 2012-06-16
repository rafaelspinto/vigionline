package vigionline.vce.stream.iterator;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import vigionline.common.Utils;
import vigionline.common.database.mapper.ImageMapper;

public class DatabaseStreamIterator extends StreamIterator<Messages.Message> {

	private ResultSet _cursor;
	private boolean _hasNext;

	public DatabaseStreamIterator(int idCamera, Date initialDate)
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
			/** File might not exist in filesystem **/
			if (_cursor.isLast()) {
				return new Messages.PoisonMessage();
			}
			byte[] image = Utils.jpegToByteArray(new File(_cursor
					.getString("filename")));

			Messages.ImageMessage img = new Messages.ImageMessage();
			img.image = image;
			return img;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new Messages.PoisonMessage();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEndOfStream() {
		return _hasNext;
	}

	@Override
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
