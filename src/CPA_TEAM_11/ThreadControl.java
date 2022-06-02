package CPA_TEAM_11;

import java.util.PriorityQueue;

public class ThreadControl {
	public static void main (String[] args) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
			for(int i=7; i >= 1; i--) {
				heap.add(i);
				
			}
			heap.remove();
			System.out.println(heap);
	}
}