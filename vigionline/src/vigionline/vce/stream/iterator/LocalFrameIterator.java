package vigionline.vce.stream.iterator;

import vigionline.vce.stream.virtual.Messages;
import vigionline.vce.stream.virtual.StreamBroker;

public class LocalFrameIterator extends AbstractFrameIterator<Messages.Message> {

	private StreamBroker _broker;
	private int _idConsumer;

	public LocalFrameIterator(StreamBroker broker, int idConsumer) {
		this._broker = broker;
		this._idConsumer = idConsumer;
	}

	@Override
	public boolean hasNext() {
		return _broker._isProducing.booleanValue();
	}

	@Override
	public Messages.Message next() {
		try {
			return _broker.get(_idConsumer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEndOfStream() {
		return ! _broker._isProducing.booleanValue();
	}

	@Override
	public void shutdown() {
		_broker.removeQueue(_idConsumer);
	}
}
