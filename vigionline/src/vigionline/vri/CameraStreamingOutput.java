package vigionline.vri;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.CameraStreamReaderFactory;

import com.sun.jersey.api.core.HttpContext;

public class CameraStreamingOutput implements StreamingOutput {

	private final Camera _camera;
	private final Model _model;
	private final HttpContext _httpContext;
	private int _maxTries;

	public CameraStreamingOutput(Camera camera, Model model, HttpContext hc,
			int maxTries) {
		this._camera = camera;
		this._model = model;
		this._httpContext = hc;
		this._maxTries = maxTries;
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

		int nrTries = 0;
		while (nrTries < _maxTries) {
			try {
				Iterable<byte[]> iterable = new CameraStreamReaderFactory(
						_camera, _model).getCameraStreamReader().images();
				Iterator<byte[]> iter = iterable.iterator();

				if (iter != null) {
					while (iter.hasNext()) {

						outputStream.write("--myboundary\r\n".getBytes());
						outputStream.write("Content-Type: image/jpeg\r\n\r\n"
								.getBytes());
						outputStream.write(iter.next());
						outputStream.flush();
					}
				}
				outputStream.close();
			} catch (SocketException se) {
				se.printStackTrace();
				nrTries++;
			} finally {
				if (nrTries == _maxTries)
					throw new WebApplicationException(404);
			}
		}
	}
}
