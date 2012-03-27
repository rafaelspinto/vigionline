package vigionline.common.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Action implements Serializable {

	private static final long serialVersionUID = 6985473681050829945L;
	private int _idAction, _idModel;
	private String _name, _action1, _action2;
	
	public int getIdAction() { return _idAction; }
	public void setIdAction(int idAction) { this._idAction = idAction;	}
	public int getIdModel(){ return this._idModel; }
	public void setIdModel(int idModel) { this._idModel = idModel; }
	public String getName() {	return _name; }
	public void setName(String name) { this._name = name; }
	public String getAction1() { return _action1; }
	public void setAction1(String action1) { this._action1 = action1; }
	public String getAction2() { return _action2;	}
	public void setAction2(String action2) { this._action2 = action2;	}
}
