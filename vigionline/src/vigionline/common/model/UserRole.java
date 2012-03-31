package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRole implements Serializable {

	private static final long serialVersionUID = 8888612632588734880L;
	private int _idUserRole, _idUser, _idRole;

	public int getIdUserRole() { return _idUserRole;	}
	public void setIdUserRole(int idUserRole) {	this._idUserRole = idUserRole; }
	public int getIdUser() { return _idUser; }
	public void setIdUser(int idUser) {	this._idUser = idUser;	}
	public int getIdRole() {	return _idRole; }
	public void setIdRole(int idRole) {	this._idRole = idRole;	}
}
