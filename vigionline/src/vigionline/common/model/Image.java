package vigionline.common.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Image implements Serializable {

	private static final long serialVersionUID = -7366477520675577432L;
	private int _idImage, _idCamera, _size;
	private Date _date;
	private String _filename;
	
	public int getIdImage() { return _idImage;	}
	public void setIdImage(int idImage) { this._idImage = idImage; }
	public int getIdCamera() { return _idCamera; }
	public void setIdCamera(int idCamera){ this._idCamera = idCamera; }
	public int getSize() { return _size; }
	public void setSize(int size) {	this._size = size;	}
	public Date getDate() {	return _date; }
	public void setDate(Date date) { this._date = date; }
	public String getFilename() { return _filename; }
	public void setFilename(String filename) { this._filename = filename; }
}
