public class Subset {
    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        while (k != 0) {
            System.out.println(queue.dequeue());
            k--;
        }
    }
}
