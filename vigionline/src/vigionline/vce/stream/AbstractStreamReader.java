package vigionline.vce.stream;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractStreamReader {
	abstract protected byte[] getNextImage();
	abstract protected byte[] getPrevImage();

	public Iterable<byte[]> images() {
		return new Iterable<byte[]>() {
			public Iterator<byte[]> iterator() {
				return new Iterator<byte[]>() {

					byte[] prev, next = getNextImage();

					@Override
					public boolean hasNext() {
						return next != null;
					}

					@Override
					public byte[] next() {
						if (next == null)
							throw new NoSuchElementException();
						prev = null;
						prev = next;
						next = getNextImage();
						return prev;
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			};
		};
	}
}
