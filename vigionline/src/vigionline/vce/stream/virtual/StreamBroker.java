package vigionline.vce.stream.virtual;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public final class StreamBroker {

	public Map<Integer, ArrayBlockingQueue<byte[]>> _queue = new ConcurrentHashMap<Integer, ArrayBlockingQueue<byte[]>>();
	public Boolean _isProducing = Boolean.TRUE;
	private int _size = 0;
	private static int BUFFER_SIZE = 100;

	public int addQueue() {
		_size++;
		ArrayBlockingQueue<byte[]> q = new ArrayBlockingQueue<byte[]>(BUFFER_SIZE);
		_queue.put(_size, q);
		return _size;
	}

	public void removeQueue(int idQueue) {
		_queue.remove(idQueue);
	}

	public void put(byte[] image) throws InterruptedException {
		if(image != null)
		{
			for (ArrayBlockingQueue<byte[]> q : _queue.values())
				q.offer(image);
		}
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
