package vigionline.vce.stream.virtual;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


public final class StreamBroker {

	public Map<Integer, BlockingQueue<Messages.Message>> _nonDiscardingQueue = new ConcurrentHashMap<Integer, BlockingQueue<Messages.Message>>();
	public Map<Integer, BlockingQueue<Messages.Message>> _discardingQueue = new ConcurrentHashMap<Integer, BlockingQueue<Messages.Message>>();
	public Boolean _isProducing = Boolean.TRUE;
	public final int BUFFER_SIZE = 100;
		
	public int addNonDiscardingQueue() {
		int size = _nonDiscardingQueue.size();
		ArrayBlockingQueue<Messages.Message> q = new ArrayBlockingQueue<Messages.Message>(BUFFER_SIZE);
		_nonDiscardingQueue.put(size, q);
		return size;
	}
	
	public int addDiscardingQueue() {
		int size = _discardingQueue.size();
		ArrayBlockingQueue<Messages.Message> q = new ArrayBlockingQueue<Messages.Message>(1);
		_discardingQueue.put(size, q);
		return size;
	}

	public void removeNonDiscardingQueue(int idQueue) {
		_nonDiscardingQueue.remove(idQueue);
	}
	
	public void removeDiscardingQueue(int idQueue) {
		_discardingQueue.remove(idQueue);
	}

	public void put(Messages.Message image) {
		if (image != null) {
			for (Queue<Messages.Message> q : _nonDiscardingQueue.values())
				q.offer(image);
			
			for (Queue<Messages.Message> q : _discardingQueue.values())
			{
				if(!q.isEmpty())
					q.remove();
				q.offer(image);
			}
		}
	}

	public Messages.Message getFromNonDiscardingQueue(int q) throws InterruptedException {
		return _nonDiscardingQueue.get(q).take();
	}
	
	public Messages.Message getFromDiscardingQueue(int q) throws InterruptedException {
		return _discardingQueue.get(q).take();
	}

	public boolean isEmpty() {
		return _nonDiscardingQueue.isEmpty() && _discardingQueue.isEmpty();
	}

	public void clearConsumerQueue() {
		_nonDiscardingQueue.clear();
		_discardingQueue.clear();
	}
}
