package Pekan4;

import java.util.*;

public class IterasiQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> q = new LinkedList<>();
		q.add(" Praktikum");
		q.add(" Struktur");
		q.add(" Data");
		q.add(" dan");
		q.add(" Algoritma");
		Iterator<String> iterator = q.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "");
		}
	}

}