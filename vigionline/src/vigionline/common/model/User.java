package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable{
	
	private static final long serialVersionUID = 5385760639754842923L;
	private int _idUser;
	private String _name, _username, _password;
	
	public int getIdUser() { return _idUser;}
	public void setIdUser(int idUser) { this._idUser = idUser;}
	public String getName() {	return _name;	}
	public void setName(String name) {	this._name = name;	}
	public String getUsername() {	return _username;	}
	public void setUsername(String username) {	this._username = username;	}
	public String getPassword() {	return _password;	}
	public void setPassword(String password) {	this._password = password;}
}
