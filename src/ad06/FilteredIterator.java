package ad06;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterator<T> implements Iterator<T> {
	private T curr;	
	boolean currSet = false;	
	private T checkedValue;
	private Predicate<T> filter;
	private Iterator<T> srcIterator;
    public FilteredIterator(Iterator<T> srcIterator, Predicate<T> filter) {
    	super();
    	this.srcIterator = srcIterator;
    	this.filter = filter;
    		
    }

	@Override
	public boolean hasNext() {
		return currSet || setNext();
	}

	@Override
	public T next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		currSet = false;
		return curr;
	}
	private boolean setNext() {		
		while (srcIterator.hasNext()) {
            checkedValue = srcIterator.next();
            if (filter.test(checkedValue)) {
                curr = checkedValue;
                currSet = true;
                return true;
            }
        }
		return false;
    }

	
	
}