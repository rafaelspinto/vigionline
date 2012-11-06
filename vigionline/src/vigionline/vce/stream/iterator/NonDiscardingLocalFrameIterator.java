package vigionline.vce.stream.iterator;

import vigionline.vce.stream.virtual.Messages;
import vigionline.vce.stream.virtual.StreamBroker;

public class NonDiscardingLocalFrameIterator extends AbstractLocalFrameIterator {

	public NonDiscardingLocalFrameIterator(StreamBroker broker, int idConsumer) {
		super(broker, idConsumer);
	}

	@Override
	public Messages.Message next() {
		try {
			return _broker.getFromNonDiscardingQueue(_idConsumer);
		} catch (InterruptedException e) {
			return null;
		}
	}

	public void shutdown() {
		_broker.removeNonDiscardingQueue(_idConsumer);
	}
}
