package vigionline.vce.stream;

import sun.misc.BASE64Encoder;

public class Authenticator {

	private String _username, _password, _encodedAuthorization;

	public Authenticator(String username, String password) {
		_username = username;
		_password = password;

		BASE64Encoder enc = new sun.misc.BASE64Encoder();
		String userpassword = _username + ":" + _password;
		_encodedAuthorization = enc.encode(userpassword.getBytes());
	}

	public String getEncodedAuthorization() {
		return _encodedAuthorization;
	}
}
