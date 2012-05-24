package vigionline.vce.stream.iterator;

import vigionline.vce.stream.virtual.StreamBroker;

public class LocalStreamIterator extends StreamIterator<byte[]> {

	private StreamBroker _broker;
	private int _idConsumer;

	public LocalStreamIterator(StreamBroker broker, int idConsumer) {
		this._broker = broker;
		this._idConsumer = idConsumer;
	}

	@Override
	public boolean hasNext() {
		return _broker._isProducing;
	}

	@Override
	public byte[] next() {
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
		return _broker._isProducing;
	}

	@Override
	public void shutdown() {
		_broker.removeQueue(_idConsumer);
	}
}
