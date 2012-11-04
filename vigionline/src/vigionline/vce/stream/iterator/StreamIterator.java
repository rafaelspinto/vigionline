package vigionline.vce.stream.iterator;

import vigionline.vce.exception.EndOfStreamException;

interface StreamIterator<T>{
    public boolean hasNext();
    public T next() throws EndOfStreamException;
    public void remove();
}
