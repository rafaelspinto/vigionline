package vigionline.vce.stream.iterator;

public abstract class AbstractFrameIterator<T> implements StreamIterator<T>, AutoCloseable {
	protected byte[] _prev, _next;
	protected boolean _closed = false;
	abstract public void shutdown();
	public void close()
	{
		if( !_closed )
		{
			_closed = true;
			shutdown();
		}
	}
}
