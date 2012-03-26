package vigionline.common.model;

import java.io.Serializable;

public class PermissionType implements Serializable {

	private static final long serialVersionUID = -93685804003280539L;
	private int _idPermissionType;
	private String _type;
	
	public int getIdPermissionType() {	return _idPermissionType; }
	public void setIdPermissionType(int idPermissionType) { this._idPermissionType = idPermissionType;	}
	public String getType() { return _type; }
	public void setType(String type) { this._type = type; }
}
