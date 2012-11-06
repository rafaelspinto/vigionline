package vigionline.vce.connection;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;

public class ConnectionManager implements AutoCloseable {

	private String _url, _username, _password;
	private DefaultHttpClient _httpClient;
	private InputStream _inputStream;
	private HttpResponse _httpResponse;
	private HttpGet _getMethod;
	private boolean _closed;

	public ConnectionManager(Camera camera, Model model) {
		this._url = UriBuilder.buildVideoUri(camera, model);
		this._username = camera.getUsername();
		this._password = camera.getPassword();
		this._inputStream = null;
		this._httpClient = null;
		this._getMethod = new HttpGet(_url);
	}

	public InputStream getInputStream() throws ClientProtocolException, IOException {
		_httpClient = HttpClientFactory.getHttpClient(_username, _password);
		_httpResponse = _httpClient.execute(_getMethod);
		_inputStream = _httpResponse.getEntity().getContent();
		return _inputStream;
	}

	public void close() {
		if( ! _closed ) 
		{
			_closed = true;
			_getMethod.releaseConnection();
			_httpClient.getConnectionManager().shutdown();
			_inputStream = null;
			_httpResponse = null;
		}
	}
}
