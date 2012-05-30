package vigionline.vce.connection;

import vigionline.common.configuration.ConfigurationManager;

public class ProxyManager {
	private ConfigurationManager _configurationManager;

	public ProxyManager() {
		_configurationManager = ConfigurationManager.getInstance();
	}

	public void setProxy() {
		// HTTP/HTTPS Proxy
		System.setProperty("http.proxyHost",
				_configurationManager.getString("proxyHost"));
		System.setProperty("http.proxyPort",
				_configurationManager.getString("proxyPort"));
		System.setProperty("https.proxyHost",
				_configurationManager.getString("proxyHost"));
		System.setProperty("https.proxyPort",
				_configurationManager.getString("proxyPort"));

		// SOCKS Proxy
		System.setProperty("socksProxyHost",
				_configurationManager.getString("socksHost"));
		System.setProperty("socksProxyPort",
				_configurationManager.getString("socksPort"));
	}
}
