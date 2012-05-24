package vigionline.vce.record;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.virtual.StreamHandler;

public class RecordHandler {
	private final ExecutorService _threadPool;
	private final Map<Integer, Recorder> _recorders;

	public RecordHandler() {
		this._threadPool = Executors.newCachedThreadPool();
		this._recorders = new ConcurrentHashMap<Integer, Recorder>();
	}

	public Recorder getRecorder(Camera camera) {
		return _recorders.get(camera.getIdCamera());
	}

	public void submitRecorder(Camera camera, Model model, StreamHandler streamHandler) {
		Recorder recorder = null;
		if ((recorder = getRecorder(camera)) == null) {
			recorder = new Recorder(streamHandler, camera, model);
			_recorders.put(camera.getIdCamera(), recorder);
			_threadPool.submit(recorder);
			System.out.println("Submitting recorder");
		}
	}

	public void shutdown() {
		_threadPool.shutdown();
		_recorders.clear();
	}
}
