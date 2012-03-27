package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Camera implements Serializable{

	private static final long serialVersionUID = 7309623592283388705L;
	private int _idCamera, _idLocation, _port;
	private String _name, _url, _username, _password;
	
	public int getIdCamera() {	return _idCamera; }
	public void setIdCamera(int idCamera) {	this._idCamera = idCamera; }
	public int getIdLocation() { return _idLocation; }
	public void setIdLocation(int idLocation) { this._idLocation = idLocation; }	
	public int getPort() { return _port; }
	public void setPort(int port) { this._port = port; }
	public String getName() { return _name; }
	public void setName(String name) { this._name = name; }
	public String getUrl() { return _url; }
	public void setUrl(String url) { this._url = url; }
	public String getUsername() { return _username; }
	public void setUsername(String username) { this._username = username; }
	public String getPassword() { return _password; }
	public void setPassword(String password) { this._password = password; }
}
