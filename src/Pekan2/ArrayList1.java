package Pekan2;

import java.util.ArrayList;

public class ArrayList1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add(1,"Cherry");
		for (String fruit : list) {
			System.out.println(fruit);
		}
	}
	// pekan 2
}
