package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDivision implements Serializable {

	private static final long serialVersionUID = 8888612632588734880L;
	private int _idUserDivision, _idUser, _idDivision;

	public int getIdUserDivision() {
		return _idUserDivision;
	}

	public void setIdUserDivision(int idUserDivision) {
		this._idUserDivision = idUserDivision;
	}

	public int getIdUser() {
		return _idUser;
	}

	public void setIdUser(int idUser) {
		this._idUser = idUser;
	}

	public int getIdDivision() {
		return _idDivision;
	}

	public void setIdDivision(int idDivision) {
		this._idDivision = idDivision;
	}
}
