package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Manufacturer implements Serializable {

	private static final long serialVersionUID = 4540711143645687753L;
	private int _idManufacturer;
	private String _name;
	
	public int getIdManufacturer() { return _idManufacturer; }
	public void setIdManufacturer(int idManufacturer) {	this._idManufacturer = idManufacturer;	}
	public String getName() { return _name;	}
	public void setName(String name) { this._name = name;	}
}
