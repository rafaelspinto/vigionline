package vigionline.vce.stream;

import java.util.Iterator;

public abstract class StreamIterator<T> implements Iterator<byte[]> {
	protected byte[] _prev, _next;
	abstract public boolean isEndOfStream();
	abstract public void shutdown();
}
