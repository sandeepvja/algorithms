
public class DequeTest {
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		boolean b = true;
		for (int i = 1; i <= 5; i++) {
			if (b) {
				deque.addFirst(i);
				b = false;
			}
			else {
				deque.addLast(i);
				b = true;
			}
		//	deque.addLast(i);
		}
		
		for (int i : deque) {
			System.out.print(i+" ");
		}
		System.out.println();
		b = true;
		int count = 0;
		while (!deque.isEmpty()) {
			count++;
			if (b) {
				System.out.print(deque.removeFirst()+" ");
				b = false;
			}
			else {
				System.out.print(deque.removeLast()+" ");
				b = true;
			}
		}
		System.out.println();
		System.out.println(count);
	}
}
