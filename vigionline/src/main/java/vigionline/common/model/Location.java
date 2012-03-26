package vigionline.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Location implements Serializable {

	private static final long serialVersionUID = 2084532033880034018L;
	private int _idLocation;
	private String _name;
	
	public int getIdLocation() { return _idLocation; }
	public void setIdLocation(int idLocation) {	this._idLocation = idLocation;	}
	public String getName() {	return _name; }
	public void setName(String name) { this._name = name; }
}
