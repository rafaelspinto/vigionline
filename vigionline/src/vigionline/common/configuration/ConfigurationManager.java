package vigionline.common.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ConfigurationManager {
	
	private static ConfigurationManager _instance = new ConfigurationManager();
	public static ConfigurationManager getInstance(){	return _instance; }
			
	//TODO : Treat exceptions
	private ConfigurationManager()
	{
		_properties = new Properties();
		try {
			_properties.loadFromXML(this.getClass().getResourceAsStream("vigionline.properties"));
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
	public String getString(String key)	{	return _properties.getProperty(key); }
	public int getInt(String key)	{	return Integer.parseInt(_properties.getProperty(key)); }
}
