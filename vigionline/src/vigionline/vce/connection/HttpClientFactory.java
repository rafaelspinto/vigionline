package vigionline.vce.connection;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

import vigionline.common.configuration.ConfigurationManager;

public class HttpClientFactory extends DefaultHttpClient {

	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getHttpClient(String username,
			String password) {
		DefaultHttpClient client;
		/********************************/
		DefaultHttpClient seed = new DefaultHttpClient();
		SchemeRegistry registry = new SchemeRegistry();
		Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(),
				80);
		registry.register(http);
		VigionlineConnManager mgr = new VigionlineConnManager(seed.getParams(),
				registry);
		client = new DefaultHttpClient(mgr, seed.getParams());
		/********************************/
		client.getCredentialsProvider().setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(username, password));
		ConfigurationManager conf = ConfigurationManager.getInstance();

		if (conf.hasProxy()) {
			HttpHost proxy = new HttpHost(conf.getProxyHost(),
					conf.getProxyPort());
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);
		}
		//client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(10, true));
		return client;
	}
}
