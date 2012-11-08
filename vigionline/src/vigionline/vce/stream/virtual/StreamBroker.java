package vigionline.vce.stream.virtual;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


public final class StreamBroker {

	public Map<Object, BlockingQueue<Messages.Message>> _nonDiscardingQueue = new ConcurrentHashMap<Object, BlockingQueue<Messages.Message>>();
	public Map<Object, BlockingQueue<Messages.Message>> _discardingQueue = new ConcurrentHashMap<Object, BlockingQueue<Messages.Message>>();
	public Boolean _isProducing = Boolean.TRUE;
	public final int BUFFER_SIZE = 100;

	public Object addNonDiscardingQueue() {
		int size = _nonDiscardingQueue.size();
		BlockingQueue<Messages.Message> q = new ArrayBlockingQueue<Messages.Message>(BUFFER_SIZE);
		_nonDiscardingQueue.put(q, q);
		return q;
	}

	public Object addDiscardingQueue() {
		int size = _discardingQueue.size();
		BlockingQueue<Messages.Message> q = new ArrayBlockingQueue<Messages.Message>(1);
		_discardingQueue.put(q, q);
		return q;
	}

	public void removeNonDiscardingQueue(Object q) {
		_nonDiscardingQueue.remove(q);
	}

	public void removeDiscardingQueue(Object q) {
		_discardingQueue.remove(q);
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

	public Messages.Message getFromNonDiscardingQueue(Object q) throws InterruptedException {
		return _nonDiscardingQueue.get(q).take();
	}

	public Messages.Message getFromDiscardingQueue(Object q) throws InterruptedException {
		return _discardingQueue.get(q).take();
	}

	public boolean isEmpty() {
		return _nonDiscardingQueue.isEmpty() && _discardingQueue.isEmpty();
	}

	public void shutdown()
	{
		_isProducing =  Boolean.FALSE;
		put(new Messages.TerminateMessage());
		_nonDiscardingQueue.clear();
		_discardingQueue.clear();
	}
}
