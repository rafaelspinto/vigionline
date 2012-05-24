package vigionline.common.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ConfigurationManager {

	private static ConfigurationManager _instance = new ConfigurationManager();

	public static ConfigurationManager getInstance() {
		return _instance;
	}

	// TODO : Treat exceptions
	private ConfigurationManager() {
		_properties = new Properties();
		try {
			_properties.loadFromXML(this.getClass().getResourceAsStream(
					"vigionline.properties"));
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Properties _properties;

	public String getString(String key) {
		return _properties.getProperty(key);
	}

	public int getInt(String key) throws NumberFormatException {
		return Integer.parseInt(_properties.getProperty(key));
	}

	/************ Program Settings **************/

	public boolean hasProxy() {
		String proxyHost = "";
		int proxyPort = 0;
		try
		{
			proxyHost = getString("proxyHost");
			proxyPort = getInt("proxyPort");
		}catch(NumberFormatException nfe)
		{
			return false;
		}
		return proxyHost != null && !proxyHost.isEmpty() && proxyPort > 0;
	}

	public String getProxyHost() {
		return getString("proxyHost");
	}

	public int getProxyPort() {
		return getInt("proxyPort");
	}
	
	public int getImageDirectory() {
		return getInt("imageDirectory");
	}
}
