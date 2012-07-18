package vigionline.vce.connection;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;

public class UriBuilder {

	public static String buildVideoUri(Camera camera, Model model) {
		return camera.getUrl() + ":" + camera.getPort() + "/"
				+ model.getVideoUrl();
	}
}
