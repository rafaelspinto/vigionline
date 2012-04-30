package vigionline.vce.stream;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import vigionline.common.configuration.ConfigurationManager;
import vigionline.common.model.Camera;
import vigionline.common.model.Model;

public class ConnectionManager {

	private String _url, _username, _password;
	private DefaultHttpClient _httpClient;
	private InputStream _inputStream;
	private HttpResponse _httpResponse;

	public ConnectionManager(Camera camera, Model model) {
		this._url = UriBuilder.buildVideoUri(camera, model);
		this._username = camera.getUsername();
		this._password = camera.getPassword();
		this._httpClient = new DefaultHttpClient();
		this._inputStream = null;
	}

	public InputStream getInputStream() throws ClientProtocolException,
			IOException {
		if (_inputStream == null) {
			_httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(_username, _password));

			ConfigurationManager conf = ConfigurationManager.getInstance();

			if (conf.hasProxy()) {
				HttpHost proxy = new HttpHost(conf.getProxyHost(),
						conf.getProxyPort());
				_httpClient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
			}
			
			HttpGet httpget = new HttpGet(_url);
			_httpResponse = _httpClient.execute(httpget);
			HttpEntity entity = _httpResponse.getEntity();
			_inputStream = entity.getContent();
		}
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
		_httpClient.getConnectionManager().shutdown();
	}
}
