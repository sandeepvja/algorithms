import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private int rear;
    private int random;
    

    private class RandomIterator<ItemType> implements Iterator<ItemType> {
        private ItemType[] iterableQueue;
        int iterRear;
        RandomIterator() {
            iterableQueue = (ItemType[]) new Object[rear + 1];
            for (int i = 0; i < iterableQueue.length; i++) {
                iterableQueue[i] = (ItemType) queue[i];
            }
            iterRear = rear;
            StdRandom.setSeed(System.currentTimeMillis());
        }

        @Override
        public boolean hasNext() {

            return (iterRear != -1);
        }

        @Override
        public ItemType next() {
            if (iterRear < 0) {
                throw new java.util.NoSuchElementException(); 
            }
            int rand = StdRandom.uniform(iterRear + 1);
            ItemType returnVal = iterableQueue[rand];
            iterableQueue[rand] = iterableQueue[iterRear--];
            return returnVal;
        }

        @Override
        public void remove() {
            // No Implementation
            throw new java.lang.UnsupportedOperationException();
        }

    }

    public RandomizedQueue() // construct an empty randomized queue
    {
        StdRandom.setSeed(System.currentTimeMillis());
        size = 16;
        rear = -1;
        random = -1;
        queue = (Item[]) new Object[size];
    }

    public boolean isEmpty() // is the queue empty?
    {
        return rear == -1 ? true : false;
    }

    public int size() // return the number of items on the queue
    {
        return rear + 1;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (rear + 1 == size)
            changeSize(2);
        queue[++rear] = item;
    }

    private void changeSize(double change) {
        size *= change;
        Item[] newqueue = (Item[]) new Object[size];
        for (int i = 0; i < Math.min(queue.length, newqueue.length); i++) {
            newqueue[i] = queue[i];
        }
        queue = newqueue;
    }

    public Item dequeue() // delete and return a random item
    {
        if (rear == -1)
            throw new java.util.NoSuchElementException();
        if (random == -1)
            random = getRandom();
        Item returnVal = queue[random];
        //copy last value into queue[random] 
        queue[random] = queue[rear--];
        if (size > 0 && rear == size / 4)
            changeSize(0.5);
        random = -1;
        return returnVal;
    }

    private int getRandom() {
        return StdRandom.uniform(rear + 1);
    }

    public Item sample() // return (but do not delete) a random item
    {
        if (rear == -1)
            throw new java.util.NoSuchElementException();
        random = getRandom();
        return queue[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator<Item>();
    }
}