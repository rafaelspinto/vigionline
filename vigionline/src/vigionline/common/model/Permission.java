package vigionline.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Permission implements Serializable {

	private static final long serialVersionUID = -692473828444121530L;
	private int _idPermission, _idCamera, _idDivision;

	public int getIdPermission() {	return _idPermission; }
	public void setIdPermission(int idPermission) {	this._idPermission = idPermission; }
	public int getIdCamera() { return _idCamera; }
	public void setIdCamera(int idCamera) { this._idCamera = idCamera; }
	public int getIdDivision(){ return _idDivision; }
	public void setIdDivision(int idDivision) { this._idDivision = idDivision; }
}
