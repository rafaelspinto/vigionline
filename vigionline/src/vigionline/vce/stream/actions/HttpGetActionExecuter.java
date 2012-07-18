package vigionline.vce.stream.actions;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import vigionline.common.model.Action;
import vigionline.common.model.Camera;
import vigionline.vce.connection.HttpClientFactory;

public class HttpGetActionExecuter implements IActionExecuter {

	private Camera _camera;
	private Action _action;

	public HttpGetActionExecuter(Camera camera, Action action) {
		this._camera = camera;
		this._action = action;
	}

	public HttpResponse execute() throws ClientProtocolException, IOException {
		HttpResponse response = null;
		DefaultHttpClient client = HttpClientFactory.getHttpClient(
				_camera.getUsername(), _camera.getPassword());
		/*** ACTION 1 ***/
		if (_action.getAction1() != null) {
			HttpGet get = new HttpGet(_camera.getUrl() + ":"
					+ _camera.getPort() + "/" + _action.getAction1());
			response = client.execute(get);
			get.releaseConnection();
		}

		/*** ACTION 2 ***/
		if (_action.getAction2() != null) {
			HttpGet get = new HttpGet(_camera.getUrl() + ":"
					+ _camera.getPort() + "/" + _action.getAction2());
			response = client.execute(get);
			get.releaseConnection();
		}
		return response;
	}
}
