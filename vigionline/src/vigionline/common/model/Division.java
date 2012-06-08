package vigionline.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Division implements Serializable {

	private static final long serialVersionUID = 5244396536827729334L;
	private int _idDivision;
	private String _name;
	
	public int getIdDivision() {	return _idDivision;	}
	public void setIdDivision(int idRole) {	this._idDivision = idRole; }
	public String getName() {	return _name; }
	public void setName(String name) {	this._name = name;}
}