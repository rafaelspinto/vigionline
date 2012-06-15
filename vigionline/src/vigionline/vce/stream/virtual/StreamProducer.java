package vigionline.vce.stream.virtual;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.connection.ConnectionManager;
import vigionline.vce.stream.iterator.Messages;
import vigionline.vce.stream.iterator.RemoteStreamIterator;
import vigionline.vce.stream.iterator.StreamIterator;

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
		StreamIterator<byte[]> iterator = null;
		try {

			conManager = new ConnectionManager(_camera, _model);
			iterator = new RemoteStreamIterator(conManager, _model);

			while (iterator.hasNext() && !_broker.isEmpty()) {
				Messages.ImageMessage img = new Messages.ImageMessage();
				img.image = iterator.next();
				_broker.put(img);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conManager = null;
			iterator = null;
			_broker._isProducing = Boolean.FALSE;
			_streamHandler.removeProducer(_camera.getIdCamera());
			_broker.put(new Messages.PoisonMessage());
		}
	}

	public StreamBroker getBroker() {
		return _broker;
	}
}
