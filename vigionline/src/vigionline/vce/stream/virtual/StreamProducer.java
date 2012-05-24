package vigionline.vce.stream.virtual;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.ConnectionManager;
import vigionline.vce.stream.StreamIterator;
import vigionline.vce.stream.iterator.RemoteStreamIterator;

public class StreamProducer implements Runnable {

	private StreamBroker _broker;
	private Camera _camera;
	private Model _model;
	private boolean _stopProducing;

	public StreamProducer(StreamBroker broker, Camera camera, Model model) {
		this._broker = broker;
		this._camera = camera;
		this._model = model;
		this._stopProducing = false;
	}

	@Override
	public void run() {
		ConnectionManager conManager = null;
		StreamIterator<byte[]> iterator = null;
		try {
			conManager = new ConnectionManager(_camera, _model);
			iterator = new RemoteStreamIterator(conManager, _model);

			while (iterator.hasNext() && !_stopProducing && !_broker.isEmpty()) {
				_broker.put(iterator.next());
			}
		} catch (InterruptedException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
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
		}
	}

	public StreamBroker getBroker() {
		return _broker;
	}
}
