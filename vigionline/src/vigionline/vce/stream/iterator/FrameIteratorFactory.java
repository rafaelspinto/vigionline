package vigionline.vce.stream.iterator;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.virtual.StreamBroker;
import vigionline.vce.stream.virtual.StreamHandler;

public class FrameIteratorFactory {

	public static LocalFrameIterator getLocalStreamIterator(
			StreamHandler streamHandler, Camera camera, Model model) {
		StreamBroker broker = streamHandler.getBroker(camera, model);
		int idQueue = broker.addQueue();
		streamHandler.initProducer(broker, camera, model);
		return new LocalFrameIterator(broker, idQueue);
	}
}
