package vigionline.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role implements Serializable {

	private static final long serialVersionUID = 5244396536827729334L;
	private int _idRole;
	private String _name;
	
	public int getIdRole() {	return _idRole;	}
	public void setIdRole(int idRole) {	this._idRole = idRole; }
	public String getName() {	return _name; }
	public void setName(String name) {	this._name = name;}
}
