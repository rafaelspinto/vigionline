package vigionline.vce.stream.iterator;

public abstract class AbstractFrameIterator<T> implements StreamIterator<T> {
	protected byte[] _prev, _next;
	abstract public void shutdown();
}
