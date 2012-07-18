package vigionline.vce.stream.iterator;

import java.util.Iterator;

public abstract class AbstractFrameIterator<T> implements Iterator<T> {
	protected byte[] _prev, _next;
	abstract public boolean isEndOfStream();
	abstract public void shutdown();
}
