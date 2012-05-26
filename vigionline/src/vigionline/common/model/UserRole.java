package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRole implements Serializable {

	private static final long serialVersionUID = 8888612632588734880L;
	private int _idUserRole;
	private String _username, _rolename;

	public int getIdUserRole() { return _idUserRole;	}
	public void setIdUserRole(int idUserRole) {	this._idUserRole = idUserRole; }
	public String getUsername() { return _username; }
	public void setUsername(String username) {	this._username = username;	}
	public String getRolename() {	return _rolename; }
	public void setRolename(String rolename) {	this._rolename = rolename;	}
}
