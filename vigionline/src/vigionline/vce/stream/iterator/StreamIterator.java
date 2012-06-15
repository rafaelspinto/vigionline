package vigionline.vce.stream.iterator;

import java.util.Iterator;

public abstract class StreamIterator<T> implements Iterator<T> {
	protected byte[] _prev, _next;
	abstract public boolean isEndOfStream();
	abstract public void shutdown();
}
