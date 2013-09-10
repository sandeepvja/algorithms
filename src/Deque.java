import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
   private Node first;
   private Node last;
   private int count;
   private class Node {
	   private Item value;
	   private Node next;
	   private Node prev;
	   public Node(Item value, Node prev, Node next) {
		   this.value = value;
		   this.next = next;
		   this.prev = prev;
	   }
   }
   private class DequeIterator implements Iterator<Item> {
	private Node current = first;
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Item next() {
		Item item = current.value;
		current = current.next;
		if (current == null) {
			throw new NoSuchElementException();
		}
		return item;
	}

	@Override
	public void remove() {
		// No Implementation
		throw new java.lang.UnsupportedOperationException();
	}
	   
   }
   public Deque()                     // construct an empty deque
   {
	   first = null;
	   last = null;
	   count = 0;
   }
   public boolean isEmpty()           // is the deque empty?
   {
	   return first == null && last == null;
   }
   public int size()                  // return the number of items on the deque
   {
	   return count;
   }
   public void addFirst(Item item)    // insert the item at the front
   {
	   if (item == null) 
		   throw new java.lang.NullPointerException();
	   Node newFirst = new Node(item, null, first);
	   if (first != null) 
	       first.prev = newFirst;
	   first = newFirst;
	   if (last == null) last = first;
	   count++;
   }
   public void addLast(Item item)     // insert the item at the end
   {
	   if (item == null) 
		   throw new java.lang.NullPointerException();
	   Node newLast = new Node(item, last, null);
	   if (last != null) 
	       last.next = newLast;
	   last = newLast;
	   if (first == null) 
	       first = last;
	   count++;
   }
   
   public Item removeFirst()          // delete and return the item at the front
   {
	   if (first == null)
		   throw new NoSuchElementException();
	   Item item = first.value;
	   first = first.next;
	   if (first != null) 
	       first.prev = null;
	   if (first == null) 
	       last = null;
	   count--;
	   return item;
   }
   public Item removeLast()           // delete and return the item at the end
   {
	   if (last == null)
		   throw new NoSuchElementException();
	   Item item = last.value;
	   last = last.prev;
	   if (last != null) 
	       last.next = null;
	   if (last == null) 
	       first = null;
	   count--;
	   return item;
   }
// return an iterator over items in order from front to end
   public Iterator<Item> iterator() 
   {
	   return new DequeIterator();
   }
}