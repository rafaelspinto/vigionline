package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Model implements Serializable {

	private static final long serialVersionUID = -6576336543575331101L;
	private int _idModel, _idManufacturer, _width, _height;
	private String _name, _videoUrl, _audioUrl;
		
	public int getIdModel() { return _idModel; }
	public void setIdModel(int idModel) {	this._idModel = idModel; }
	public int getIdManufacturer() { return _idManufacturer; }
	public void setIdManufacturer(int idManufacturer) { this._idManufacturer = idManufacturer; }
	public int getWidth() { return _width; }
	public void setWidth(int width) {	this._width = width; }
	public int getHeight() { return _height; }
	public void setHeight(int height) { this._height = height; }
	public String getName() { return _name; }
	public void setName(String name) { this._name = name; }
	public String getVideoUrl() { return _videoUrl; }
	public void setVideoUrl(String videoUrl) { this._videoUrl = videoUrl; }
	public String getAudioUrl() { return _audioUrl; }
	public void setAudioUrl(String audioUrl) { this._audioUrl = audioUrl; }
}
