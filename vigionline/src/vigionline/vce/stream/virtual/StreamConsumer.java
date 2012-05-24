package vigionline.vce.stream.virtual;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import vigionline.vce.stream.iterator.LocalStreamIterator;

public final class StreamConsumer implements StreamingOutput {

	private LocalStreamIterator _iterator;

	public StreamConsumer(LocalStreamIterator iterator) {
		this._iterator = iterator;
	}

	@Override
	public void write(OutputStream outputStream) throws IOException,
			WebApplicationException {

		try {
			while (_iterator.hasNext()) {
				outputStream.write("--myboundary\r\n".getBytes());
				outputStream.write("Content-Type: image/jpeg\r\n\r\n"
						.getBytes());
				outputStream.write(_iterator.next());
				outputStream.flush();
			}
		} finally {
			outputStream.close();
			_iterator.shutdown();
		}
	}
}
