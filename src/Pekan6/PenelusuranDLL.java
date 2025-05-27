package Pekan6;

public class PenelusuranDLL {
	static void forwardTraversal(NodeDLL head) {
		NodeDLL curr = head;
		while (curr != null) {
			System.out.print(curr.data + " <-> ");
			curr = curr.next;
		}
		System.out.println();
		}
	static void backwardTraversal(NodeDLL tail) {
		NodeDLL curr = tail;
		while (curr != null) {
			System.out.print(curr.data + " <-> ");
			curr = curr.prev;
		}
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		System.out.println("Fadil Insanus Siddik");
		System.out.println("2411532013");
		System.out.println();
		
		NodeDLL head = new NodeDLL(1);
		NodeDLL second = new NodeDLL(2);
		NodeDLL third = new NodeDLL(3);
		
		head.next = second;
		second.prev = head;
		second.next = third;
		third.prev = second;
		System.out.println("Penelusuran maju: ");
		forwardTraversal(head);
		
		System.out.println("Penelusuran mundur: ");
		backwardTraversal(third);
	}
}
