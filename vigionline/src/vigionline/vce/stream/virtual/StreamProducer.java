package vigionline.vce.stream.virtual;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.connection.ConnectionManager;
import vigionline.vce.stream.iterator.AbstractFrameIterator;
import vigionline.vce.stream.iterator.RemoteFrameIterator;

public class StreamProducer implements Runnable {

	private StreamHandler _streamHandler;
	private StreamBroker _broker;
	private Camera _camera;
	private Model _model;

	public StreamProducer(StreamHandler streamHandler, StreamBroker broker,
			Camera camera, Model model) {
		this._streamHandler = streamHandler;
		this._broker = broker;
		this._camera = camera;
		this._model = model;
	}

	@Override
	public void run() {
		ConnectionManager conManager = null;
		AbstractFrameIterator<byte[]> iterator = null;
		try {
			conManager = new ConnectionManager(_camera, _model);
			iterator = new RemoteFrameIterator(conManager, _model);
			while (iterator.hasNext() && !_broker.isEmpty()) {
				Messages.FrameMessage img = new Messages.FrameMessage();
				img.frame = iterator.next();
				_broker.put(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conManager != null)
				conManager.shutdown();
			if(iterator != null)
				iterator.shutdown();
			_broker._isProducing = Boolean.FALSE;
			_broker.put(new Messages.TerminateMessage());
			_streamHandler.removeProducer(_camera.getIdCamera());
			_broker = null;
			_streamHandler = null;
			conManager = null;
			iterator = null;
		}
	}

	public StreamBroker getBroker() {
		return _broker;
	}
}
