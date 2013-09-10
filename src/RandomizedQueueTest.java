import java.util.Iterator;


public class RandomizedQueueTest {
    private RandomizedQueue<Integer> randQueue = new RandomizedQueue<Integer>();
    public RandomizedQueueTest() {
        
    }
    public boolean iteratorTest() {
        for (int i = 0; i < 20; i++) {
            randQueue.enqueue(i);
        }
     //   Iterator iter = randQueue.iterator();
        int count = 0;
        for (int i : randQueue) {
         //   System.out.println(i);
            count++;
        }
        return count == 21;
    }
    public boolean resizeTest() {
        for (int i = 0; i < 20; i++) {
            randQueue.enqueue(i);
        }
        int dequeuedVal;
        for (int i = 0; i < 20; i++) {
            dequeuedVal = randQueue.dequeue();
            System.out.println(dequeuedVal);
        }
        randQueue.enqueue(1);
        
        return randQueue.size() == 1;
    }
    public boolean checkSample() {

        int sample = randQueue.sample();
        int dequeuedValue = randQueue.dequeue();
        return sample == dequeuedValue;
    }
    public static void main(String[] args) {
        RandomizedQueueTest test = new RandomizedQueueTest();
        System.out.println("Resize test: "+test.resizeTest());
        System.out.println("Iterator test: "+test.iteratorTest());
        System.out.println("Sampling test: "+test.checkSample());
    }
}
