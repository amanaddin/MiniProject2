package priorityQueue;

import java.util.Comparator;

public class HeapPriorityQueue<T> implements PriorityQueue<T> {

	private T[] heap;
	private int size;
	private int maxSize;
	@SuppressWarnings("rawtypes")
	private Comparator  comp;
	
	@SuppressWarnings({ "rawtypes" })
	public HeapPriorityQueue(int maxSize, Comparator comp) {
		this.maxSize = maxSize;
		this.comp = comp;
		clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() { 
	    heap = (T[]) (new Object[maxSize]); 
	              this.size = 0; 
	 } 

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Method that is specific for a static implementation of the heap, checks if
	 * the underlying array is full.
	 * 
	 * @return true if the underlying array is full
	 */
	public boolean isFull() {
		return size == maxSize;
	}

	@Override
	public int size() {
		return size;
	}

	private int parent(int currentIndex) {
		return (currentIndex - 1) / 2;
	}

	private int leftChild(int currentIndex) {
		return 2 * currentIndex + 1;
	}

	private int rightChild(int currentIndex) {
		return 2 * currentIndex + 2;
	}

	@SuppressWarnings("unchecked")
	private void reHeapUp(int currentIndex) {
		T temp = heap[currentIndex];
		while (currentIndex > 0 && comp.compare(heap[currentIndex], heap[parent(currentIndex)]) > 0) {
			temp = heap[currentIndex];
			heap[currentIndex] = heap[parent(currentIndex)];
			heap[parent(currentIndex)] = temp;
			currentIndex = parent(currentIndex);
		}
		heap[currentIndex] = temp;

	}

	@SuppressWarnings("unchecked")
	private void reHeapDown(int currentIndex) {

		T element = heap[currentIndex];
		int index;
		while (currentIndex < size / 2) {
			if (rightChild(currentIndex) < size
					&& comp.compare(heap[leftChild(currentIndex)], heap[rightChild(currentIndex)]) < 0)
				index = rightChild(currentIndex);
			else
				index = leftChild(currentIndex);
			if (comp.compare(element, heap[index]) >= 0)
				break;
			heap[currentIndex] = heap[index];
			currentIndex = index;
		}
		heap[currentIndex] = element;
	}

	@Override
	public void enqueue(T newElement) {
		if (isFull()) {
			throw new PriorityQueueFullException("Heap is full!");
		} else {
			heap[size] = newElement;
			reHeapUp(size++);

		}
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new PriorityQueueEmptyException("Cannot dequeue empty Queue!");
		} else {
			T dequeuedElement = heap[0];
			heap[0] = heap[size - 1];
			size--;
			reHeapDown(0);
			return dequeuedElement;
		}
	}

	@Override
	public T getFront() {
		if (isEmpty()) {
			throw new PriorityQueueEmptyException("Cannot get front of empty Queue!");
		} else {
			return heap[0];
		}
	}

	@Override
	public String toString() {
		String stringRepresentation = "";
		for (int i = 0; i < size; i++) {
			stringRepresentation = stringRepresentation + heap[i] + " ";
		}
		return stringRepresentation;
	}
}
