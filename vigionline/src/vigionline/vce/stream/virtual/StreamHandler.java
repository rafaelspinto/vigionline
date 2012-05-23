package vigionline.vce.stream.virtual;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;

public final class StreamHandler {

	private ExecutorService _threadPool;
	private Map<Integer, StreamProducer> _producers;
	private Map<Integer, StreamBroker> _brokers;

	public StreamHandler() {
		this._threadPool = Executors.newCachedThreadPool();
		this._producers = new ConcurrentHashMap<Integer, StreamProducer>();
		this._brokers = new ConcurrentHashMap<Integer, StreamBroker>();
	}

	public StreamBroker getBroker(Camera camera, Model model) {
		StreamBroker broker = null;
		if ((broker = _brokers.get(camera.getIdCamera())) == null) {
			broker = new StreamBroker();
			_brokers.put(camera.getIdCamera(), broker);
		}
		return broker;
	}

	public void initProducer(StreamBroker broker, Camera camera, Model model) {
		StreamProducer producer = null;
		if ((producer = _producers.get(camera.getIdCamera())) == null) {
			producer = new StreamProducer(broker, camera, model);
			_producers.put(camera.getIdCamera(), producer);
			_threadPool.submit(producer);
		}
	}

	public void shutdown() {
		_threadPool.shutdown();
	}
}
