package vigionline.vce.stream.iterator;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.virtual.StreamBroker;
import vigionline.vce.stream.virtual.StreamHandler;

public class FrameIteratorFactory {

	public static AbstractLocalFrameIterator getNonDiscardingLocalStreamIterator(
			StreamHandler streamHandler, Camera camera, Model model) {
		StreamBroker broker = streamHandler.getBroker(camera, model);
		Object queue = broker.addNonDiscardingQueue();
		streamHandler.initProducer(broker, camera, model);
		return new NonDiscardingLocalFrameIterator(broker, queue);
	}
	
	public static AbstractLocalFrameIterator getDiscardingLocalStreamIterator(
			StreamHandler streamHandler, Camera camera, Model model) {
		StreamBroker broker = streamHandler.getBroker(camera, model);
		Object queue = broker.addDiscardingQueue();
		streamHandler.initProducer(broker, camera, model);
		return new DiscardingLocalFrameIterator(broker, queue);
	}
}
