package vigionline.vri;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import vigionline.vce.stream.iterator.StreamIterator;

import com.sun.jersey.api.core.HttpContext;

public class CameraStreamingOutput implements StreamingOutput {

	private final HttpContext _httpContext;
	private StreamIterator<byte[]> _iterator;

	public CameraStreamingOutput(StreamIterator<byte[]> iterator, HttpContext hc) {
		this._iterator = iterator;
		this._httpContext = hc;
	}

	// Virtualizar Stream
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

			while (!_iterator.isEndOfStream()) {
				if (_iterator != null) {
					while (_iterator.hasNext()) {

						outputStream.write("--myboundary\r\n".getBytes());
						outputStream.write("Content-Type: image/jpeg\r\n\r\n"
								.getBytes());
						outputStream.write(_iterator.next());
						outputStream.flush();
					}
				}
			}
			outputStream.close();
		} catch (SocketException se) {
			se.printStackTrace();
			throw new WebApplicationException(404);
		} finally {
			outputStream = null;
			_iterator.shutdown();
		}
	}
}
