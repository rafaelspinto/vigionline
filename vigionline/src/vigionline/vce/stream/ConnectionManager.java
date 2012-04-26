package vigionline.vce.stream;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;

public class ConnectionManager {

	private HttpURLConnection _connection;
	private String _url, _username, _password;

	public ConnectionManager(String url, String user, String pwd) {
		this._url = url;
		this._username = user;
		this._password = pwd;
	}

	public HttpURLConnection getConnection() {
		if (_connection == null) {
			connect();
		}
		return _connection;
	}

	private void connect() {
		// Authentication
		Authenticator authenticator = new Authenticator(_username, _password);
		String encodedAuthorization = authenticator.getEncodedAuthorization();

		// Network optimizations
		Security.setProperty("sun.net.inetaddr.ttl", "0");
		Security.setProperty("sun.net.inetaddr.negative.ttl", "0");
		Security.setProperty("networkaddress.cache.ttl", "0");
		Security.setProperty("networkaddress.cache.negative.ttl", "0");
		Security.setProperty("http.keepAlive", "false");

		// Proxy
		//ProxyManager prx = new ProxyManager();
		//prx.setProxy();

		try {
			URL url = new URL(_url);
			_connection = (HttpURLConnection) url.openConnection();
			_connection.addRequestProperty("Authorization", "Basic "
					+ encodedAuthorization);
			_connection.addRequestProperty("Connection", "close");
			_connection.setDoInput(true);
			_connection.setDoOutput(false);
			_connection.setReadTimeout(60 * 1000);
			try {
				synchronized(_connection)
				{
					_connection.wait(10000);
				}
				_connection.connect();
			} catch (ConnectException e) {
				// TODO : Log
				System.out.println("DEBUG : url = "+_url);
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO : Log
			System.out.println("DEBUG : url = "+_url);
			e.printStackTrace();
		}
	}
}
