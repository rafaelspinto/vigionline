package vigionline.vwc;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	private String message, nextUrl;

	public Message(String message, String nextUrl)
	{
		this.message = message;
		this.nextUrl = nextUrl;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
}
