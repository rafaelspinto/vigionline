package vigionline.vce.stream.actions;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public interface IActionExecuter {
	HttpResponse execute() throws ClientProtocolException, IOException;
}
