package vigionline.vce.stream.virtual;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


public final class StreamBroker {

	public Map<Integer, ArrayBlockingQueue<Messages.Message>> _queue = new ConcurrentHashMap<Integer, ArrayBlockingQueue<Messages.Message>>();
	public Boolean _isProducing = Boolean.TRUE;
	private int _size = 0;
	private static int BUFFER_SIZE = 100;

	public int addQueue() {
		_size++;
		ArrayBlockingQueue<Messages.Message> q = new ArrayBlockingQueue<Messages.Message>(
				BUFFER_SIZE);
		_queue.put(_size, q);
		return _size;
	}

	public void removeQueue(int idQueue) {
		_queue.remove(idQueue);
	}

	public void put(Messages.Message image) {
		if (image != null) {
			for (ArrayBlockingQueue<Messages.Message> q : _queue.values())
				q.offer(image);
		}
	}

	public Messages.Message get(int q) throws InterruptedException {
		return _queue.get(q).take();
	}

	public boolean isEmpty() {
		return _queue.isEmpty();
	}

	public void clearConsumerQueue() {
		_queue.clear();
	}
}
