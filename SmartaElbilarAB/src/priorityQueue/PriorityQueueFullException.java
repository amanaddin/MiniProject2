package priorityQueue;

@SuppressWarnings("serial")
public class PriorityQueueFullException extends RuntimeException {
	
	public PriorityQueueFullException(String message) {
		super(message);
	}
}
