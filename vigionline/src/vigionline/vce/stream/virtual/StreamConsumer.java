package vigionline.vce.stream.virtual;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import com.sun.jersey.api.core.HttpContext;

public final class StreamConsumer implements StreamingOutput {

	private StreamBroker _broker;
	private HttpContext _context;
	private int _idQueue;

	public StreamConsumer(int idQueue, StreamBroker broker, HttpContext hc) {
		this._broker = broker;
		this._context = hc;
		this._idQueue = idQueue;
	}

	@Override
	public void write(OutputStream outputStream) throws IOException,
			WebApplicationException {

		/*** SET HEADERS ***/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("boundary", "--myboundary");
		MediaType entityMediaType = new MediaType("multipart",
				"x-mixed-replace", parameters);

		_context.getResponse().getHttpHeaders()
				.putSingle("Content-Type", entityMediaType);
		_context.getResponse().getHttpHeaders().remove("Content-Length");

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
