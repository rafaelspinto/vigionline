package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RolePermission implements Serializable {

	private static final long serialVersionUID = -5604176637015426427L;
	private int _idRolePermission, _idRole, _idPermission;
	
	public int getIdRolePermission() { return _idRolePermission; }
	public void setIdRolePermission(int idRolePermission) { this._idRolePermission = idRolePermission; }
	public int getIdRole() { return _idRole; }
	public void setIdRole(int idRole) { this._idRole = idRole; }
	public int getIdPermission() { return _idPermission; }
	public void setIdPermission(int idPermission) { this._idPermission = idPermission; }
}
