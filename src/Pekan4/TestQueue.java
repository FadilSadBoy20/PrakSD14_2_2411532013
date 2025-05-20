package Pekan4;

public class TestQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		{
		inputQueue queue = new inputQueue(1000);
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		System.out.println("Front item is "+ queue.front());
		System.out.println("Rear item is "+ queue.rear());
		System.out.println(queue.dequeue()+ " dequeued from queue");
		System.out.println("Front item is " + queue.front());
		System.out.println("Rear item is " + queue.rear());
		}

	}

}
