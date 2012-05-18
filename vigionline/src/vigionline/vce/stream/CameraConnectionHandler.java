package vigionline.vce.stream;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;

public class CameraConnectionHandler {

	public class CameraHandler {
		public int _idCamera;
		public boolean _isEnded;
		private final Queue<Queue<byte[]>> _requesters;

		public CameraHandler(int idCamera) {
			_idCamera = idCamera;
			_isEnded = false;
			_requesters = new ConcurrentLinkedQueue<>();
		}

		public void addRequester(Queue<byte[]> q) {
			_requesters.add(q);
		}

		public void removeRequester(Queue<byte[]> q) {
			_requesters.remove(q);
		}

		public Queue<Queue<byte[]>> getRequesters() {
			return _requesters;
		}
	}

	private final ExecutorService producers = Executors.newFixedThreadPool(100);
	private Map<Integer, CameraHandler> _cameraHandlers = new ConcurrentHashMap<Integer, CameraHandler>();

	public CameraHandler submitConsumer(Camera camera, Model model,
			Queue<byte[]> reqQueue) {

		System.out.println("Submitting new Consumer");
		int idCamera = camera.getIdCamera();

		CameraHandler camHandler = null;
		if ((camHandler = _cameraHandlers.get(idCamera)) == null) {
			/*** Create Iterator ***/
			ConnectionManager conManager = new ConnectionManager(camera, model);
			CameraStreamIterator iterator;
			try {
				iterator = new CameraStreamIterator(conManager, model);

				/*** Create Handler ***/
				camHandler = createCameraHandler(idCamera, iterator);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*** Retrieve BlockingQueue ***/
		camHandler.addRequester(reqQueue);
		System.out.println("Submitted new Consumer");
		return camHandler;
	}

	private CameraHandler createCameraHandler(final int idCamera,
			final StreamIterator<byte[]> iterator) {

		final CameraHandler camHandler = new CameraHandler(idCamera);

		producers.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {

				try {
					System.out.println("Started CameraHandler");
					_cameraHandlers.put(idCamera, camHandler);
					Queue<Queue<byte[]>> reqs = camHandler.getRequesters();
					while (iterator.hasNext() && reqs.size() > 0) {
						for (Queue<byte[]> q : reqs) {
							q.add(iterator.next());
							System.out.println("PUTTING "
									+ System.currentTimeMillis());
						}
					}
				} finally {
					camHandler._isEnded = true;
					_cameraHandlers.remove(idCamera);
					System.out.println("Ended CameraHandler");
				}
				return null;
			}
		});
		return camHandler;
	}

	public void shutdown() {
		producers.shutdown();
		try {
			producers.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
