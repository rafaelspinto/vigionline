package vigionline.vce.stream.iterator;

import vigionline.vce.stream.virtual.Messages;
import vigionline.vce.stream.virtual.StreamBroker;

public abstract class AbstractLocalFrameIterator extends AbstractFrameIterator<Messages.Message> {

    protected StreamBroker _broker;
    protected int _idConsumer;

    public AbstractLocalFrameIterator(StreamBroker broker, int idConsumer) {
        this._broker = broker;
        this._idConsumer = idConsumer;
    }

    @Override
    public boolean hasNext() {
        return _broker._isProducing.booleanValue();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}