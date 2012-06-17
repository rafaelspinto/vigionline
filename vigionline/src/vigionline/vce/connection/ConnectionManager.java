package vigionline.vce.connection;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.UriBuilder;

public class ConnectionManager {

	private String _url, _username, _password;
	private DefaultHttpClient _httpClient;
	private InputStream _inputStream;
	private HttpResponse _httpResponse;
	private HttpGet _getMethod;

	public ConnectionManager(Camera camera, Model model) {
		this._url = UriBuilder.buildVideoUri(camera, model);
		this._username = camera.getUsername();
		this._password = camera.getPassword();
		this._inputStream = null;
		this._httpClient = null;
		this._getMethod = new HttpGet(_url);
	}

	public InputStream getInputStream() throws ClientProtocolException,	IOException {
		_httpClient = HttpClientFactory.getHttpClient(_username, _password);
		_httpResponse = _httpClient.execute(_getMethod);
		_inputStream = _httpResponse.getEntity().getContent();
		return _inputStream;
	}

	public boolean isUrlReady() {
		if (_httpResponse == null) {
			try {
				getInputStream();
			} catch (ClientProtocolException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}
		return (_httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
	}

	public void shutdown() {
		_getMethod.releaseConnection();
		_httpClient.getConnectionManager().shutdown();
	}
}