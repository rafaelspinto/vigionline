package vigionline.common.lang;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageLocator {

	private final String MESSAGES_FILE = "vigionline.VigionlineMessages";
	private ResourceBundle _messages;
	private Locale _currentLocale;
	private String _language, _country;
	
	private void setDefaultLocale()
	{
		this._language = "en";
		this._country = "EN";
		this._currentLocale = new Locale(this._language, this._country);
	}
	
	public MessageLocator(String language, String country)
	{
		this._language = language;
		this._country = country;
		if( language == null || country == null || language.isEmpty() || country.isEmpty() )
		{
			this._language = "en";
			this._country = "EN";
		}
		this._currentLocale = new Locale(this._language, this._country);
		try
		{
			this._messages = ResourceBundle.getBundle(MESSAGES_FILE, _currentLocale);
		}
		catch(MissingResourceException mre)
		{
			setDefaultLocale();
			this._messages = ResourceBundle.getBundle(MESSAGES_FILE, _currentLocale);
		}
	}
	// Default constructor
	public MessageLocator()	{	this("en","EN"); }
	
	public String getMessage(String msg) { return this._messages.getString(msg); }
	public String getLanguage() { return this._language; }
	public String getCountry() { return this._country; }
}
