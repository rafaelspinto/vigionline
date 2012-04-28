package vigionline.vce.stream;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class ConnectionManager {

	private String _url, _username, _password;
	private HttpClient _httpClient;
	private InputStream _inputStream;
	private HttpResponse _httpResponse;

	public ConnectionManager(String url, String user, String pwd) {
		this._url = url;
		this._username = user;
		this._password = pwd;
		this._httpClient = new DefaultHttpClient();
		this._inputStream = null;
	}

	public InputStream getInputStream() throws ClientProtocolException,
			IOException {
		if (_inputStream == null) {
			((AbstractHttpClient) _httpClient).getCredentialsProvider()
					.setCredentials(
							AuthScope.ANY,
							new UsernamePasswordCredentials(_username,
									_password));

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
}
