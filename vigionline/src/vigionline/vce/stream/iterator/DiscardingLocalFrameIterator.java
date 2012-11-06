package vigionline.vce.stream.iterator;

import vigionline.vce.stream.virtual.Messages;
import vigionline.vce.stream.virtual.StreamBroker;

public class DiscardingLocalFrameIterator extends AbstractLocalFrameIterator {

	public DiscardingLocalFrameIterator(StreamBroker broker, int idConsumer) {
		super(broker, idConsumer);
	}

	@Override
	public Messages.Message next() {
		try {
			return _broker.getFromDiscardingQueue(_idConsumer);
		} catch (InterruptedException e) {
			return null;
		}
	}

	public void shutdown()
	{
		_broker.removeDiscardingQueue(_idConsumer);
	}
}
