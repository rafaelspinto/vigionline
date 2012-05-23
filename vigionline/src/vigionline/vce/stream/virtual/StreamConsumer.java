package vigionline.vce.stream.virtual;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public final class StreamConsumer implements StreamingOutput {

	private StreamBroker _broker;
	private int _idQueue;

	public StreamConsumer(int idQueue, StreamBroker broker) {
		this._broker = broker;
		this._idQueue = idQueue;
	}

	@Override
	public void write(OutputStream outputStream) throws IOException,
			WebApplicationException {

		try {
			byte[] image = _broker.get(_idQueue);
			while (_broker._isProducing || image != null) {
				outputStream.write("--myboundary\r\n".getBytes());
				outputStream.write("Content-Type: image/jpeg\r\n\r\n"
						.getBytes());
				outputStream.write(image);
				outputStream.flush();
				image = _broker.get(_idQueue);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			outputStream.close();
			_broker.removeQueue(_idQueue);
		}
	}
}
