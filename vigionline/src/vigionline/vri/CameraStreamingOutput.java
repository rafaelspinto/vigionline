package vigionline.vri;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import vigionline.common.model.Model;
import vigionline.vce.stream.CameraStreamIterator;
import vigionline.vce.stream.CameraStreamReaderFactory;
import vigionline.vce.stream.ConnectionManager;

import com.sun.jersey.api.core.HttpContext;

public class CameraStreamingOutput implements StreamingOutput {

	private final Model _model;
	private final ConnectionManager _connectionManager;
	private final HttpContext _httpContext;

	public CameraStreamingOutput(ConnectionManager conManager, Model model,
			HttpContext hc) {
		this._model = model;
		this._connectionManager = conManager;
		this._httpContext = hc;
	}

	@Override
	public void write(OutputStream outputStream) throws IOException,
			WebApplicationException {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("boundary", "--myboundary");
		MediaType entityMediaType = new MediaType("multipart",
				"x-mixed-replace", parameters);

		_httpContext.getResponse().getHttpHeaders()
				.putSingle("Content-Type", entityMediaType);
		_httpContext.getResponse().getHttpHeaders().remove("Content-Length");

		try {
			CameraStreamIterator iter = new CameraStreamReaderFactory(
					_connectionManager, _model).getCameraStreamIterator();

			while (!iter.isEndOfStream()) {
				if (iter != null) {
					while (iter.hasNext()) {

						outputStream.write("--myboundary\r\n".getBytes());
						outputStream.write("Content-Type: image/jpeg\r\n\r\n"
								.getBytes());
						outputStream.write(iter.next());
						outputStream.flush();
					}
				}
			}
			outputStream.close();
		} catch (SocketException se) {
			se.printStackTrace();
			throw new WebApplicationException(404);
		}
	}
}
