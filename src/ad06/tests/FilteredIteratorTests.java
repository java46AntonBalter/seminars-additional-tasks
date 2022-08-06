package ad06.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ad06.FilteredIterator;

class IntRangeIterator implements Iterator<Integer> {
	int max;
	int nextValue;

	public IntRangeIterator(int min, int max) {
		super();
		if (max < min || max + 1 <= min) { // avoid invalid order and overflow
			throw new IllegalArgumentException();
		}
		this.nextValue = min;
		this.max = max;
	}

	@Override
	public boolean hasNext() {
		return nextValue < max;
	}

	@Override
	public Integer next() {
		if (!hasNext())
			throw new NoSuchElementException();
		return nextValue++;
	}
}

class EvenPredicate implements Predicate<Integer> {
	@Override
	public boolean test(Integer val) {
		return (val % 2 == 0);
	}
}

class TruePredicate implements Predicate<Integer> {
	@Override
	public boolean test(Integer val) {
		return true;
	}
}

class FilteredIteratorTests {
	private IntRangeIterator intIter;
	private Predicate<Integer> filter;
	private FilteredIterator<Integer> filteredIter;
    @Test
	void allValuesPassedTest() {
    	intIter = new IntRangeIterator(0, 10);
		filter = new TruePredicate();
		filteredIter = new FilteredIterator<>(intIter, filter);
		Integer[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Integer[] res = setResult(filteredIter);
		assertArrayEquals(expected, res);
    }
	@Test
	void noValuesPassedTest() {
		intIter = new IntRangeIterator(1, 1);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(intIter, filter);
		Integer[] expected = {};
		Integer[] res = setResult(filteredIter);
		assertArrayEquals(expected, res);		
	}
	@Test
	void firstButNotLastValuesPassedTest() {
		intIter = new IntRangeIterator(0, 10);
		filter = new EvenPredicate();
		 filteredIter = new FilteredIterator<>(intIter, filter);
		Integer[] expected = { 0, 2, 4, 6, 8 };
		Integer[] res = setResult(filteredIter);
		assertArrayEquals(expected, res);
	}
	@Test
	void notFirstButLastValuesPassedTest() {
		intIter = new IntRangeIterator(1, 11);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(intIter, filter);
		Integer[] expected = { 2, 4, 6, 8, 10 };
		Integer[] res = setResult(filteredIter);
		assertArrayEquals(expected, res);
	}
	@Test
	void firstAndLastValuesDontPassTest() {
		intIter = new IntRangeIterator(1, 10);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(intIter, filter);
		Integer[] expected = { 2, 4, 6, 8 };
		Integer[] res = setResult(filteredIter);
		assertArrayEquals(expected, res);
	}
	
	@Test
	void nullSupportTest() {
		Iterator<Integer> Iter = Arrays.asList(1, null).iterator();
		filter = new TruePredicate();
		filteredIter = new FilteredIterator<>(Iter, filter);
		Integer[] expected = { 1, null };
		Integer[] res = setResult(filteredIter);
		assertArrayEquals(expected, res);
	}

	private Integer[] setResult(FilteredIterator<Integer> filteredIter) {
		Integer[] res = {};
		int index = 0;
		while (filteredIter.hasNext()) {
			res = Arrays.copyOf(res, res.length + 1);
			res[index++] = filteredIter.next();
		}
		return res;
	}
	

}
