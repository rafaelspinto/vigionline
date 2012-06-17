package vigionline.vce.connection;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import vigionline.common.configuration.ConfigurationManager;

public class HttpClientFactory extends DefaultHttpClient {

	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getHttpClient(String username, String password) {
		ConfigurationManager conf = ConfigurationManager.getInstance();
		DefaultHttpClient seed = new DefaultHttpClient();
		SchemeRegistry registry = new SchemeRegistry();
		Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(),	80);
		Scheme https = new Scheme("https", PlainSocketFactory.getSocketFactory(), 443);
		registry.register(http);
		registry.register(https);
		HttpParams params = seed.getParams();
		params.setParameter("http.connection-manager.max-per-host",conf.getMaxConnectionsPerHost());
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		/***** Proxy Stuff *****/
		if (conf.hasProxy()) {
			HttpHost proxy = new HttpHost(conf.getProxyHost(), conf.getProxyPort());
			params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		/**********************/
		VigionlineConnManager mgr = new VigionlineConnManager(params, registry);
		DefaultHttpClient client = new DefaultHttpClient(mgr, params);
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));	
		return client;
	}
}
