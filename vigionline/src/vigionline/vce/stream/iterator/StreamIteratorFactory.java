package vigionline.vce.stream.iterator;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.virtual.StreamBroker;
import vigionline.vce.stream.virtual.StreamHandler;

public class StreamIteratorFactory {

	public static LocalStreamIterator getLocalStreamIterator(
			StreamHandler streamHandler, Camera camera, Model model) {
		StreamBroker broker = streamHandler.getBroker(camera, model);
		int idQueue = broker.addQueue();
		streamHandler.initProducer(broker, camera, model);
		return new LocalStreamIterator(broker, idQueue);
	}
}
