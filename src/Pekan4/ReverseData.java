package Pekan4;

import java.util.*;
class ReverseData {

	public static void main(String[] args) {
	Queue<Integer> q = new LinkedList<Integer>();
	q.add(1);
	q.add(2);
	q.add(30);
	System.out.println("sebelum reverse" + q);
	Stack<Integer> s = new Stack<Integer>();
	while (!q.isEmpty()) {
	s.push(q.remove());
	}
	while (!s.isEmpty()) {
	q.add(s.pop());
	}
	System.out.println("sesudah reverse= " + q);
		// TODO Auto-generated method stub

	}

}
