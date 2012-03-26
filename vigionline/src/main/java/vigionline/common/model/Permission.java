package vigionline.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Permission implements Serializable {

	private static final long serialVersionUID = -692473828444121530L;
	private int _idPermission, _idPermissionType;

	public int getIdPermission() {	return _idPermission; }
	public void setIdPermission(int idPermission) {	this._idPermission = idPermission; }
	public int getIdPermissionType() {	return _idPermissionType; }
	public void setIdPermissionType(int idPermissionType) {	this._idPermissionType = idPermissionType; }
}
