package vigionline.vce.stream.virtual;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public final class StreamBroker {

	public Map<Integer, LinkedBlockingQueue<byte[]>> _queue = new ConcurrentHashMap<Integer, LinkedBlockingQueue<byte[]>>();
	public Boolean _isProducing = Boolean.TRUE;
	private int _size = 0;

	public int addQueue() {
		_size++;
		LinkedBlockingQueue<byte[]> q = new LinkedBlockingQueue<byte[]>();
		_queue.put(_size, q);
		return _size;
	}

	public void removeQueue(int idQueue) {
		_queue.remove(idQueue);
	}

	public void put(byte[] image) throws InterruptedException {
		for (LinkedBlockingQueue<byte[]> q : _queue.values())
			q.put(image);
	}

	public byte[] get(int q) throws InterruptedException {
		return _queue.get(q).take();
	}

	public boolean isEmpty() {
		return _queue.isEmpty();
	}
	
	public void clearConsumerQueue()
	{
		_queue.clear();
	}
}
