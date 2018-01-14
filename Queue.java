import java.util.Iterator;



public class Queue<Value> implements Iterable<Value>{
	// Linked List implementation of Queue Data Structure
	private Node front,rear;
	private class Node{
		Value v;
		Node next;
		public Node(Value v){
			next = null;
			this.v = v;
					
		}
	}
	public void enqueue(Value v){
		Node temp = new Node(v);
		if(rear == null){
			rear = temp;
			front = temp;
			return;
		}
		rear.next = temp;
		rear = temp;
	}
	public Value dequeue(){
		if(isEmpty()){
			return null;
		}
		Value v = front.v;
		if(front == rear){
			rear = null;
		}
		front = front.next;
		return v;
	}
	public boolean isEmpty(){
		return rear == null;
	}
	
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	private class ListIterator implements Iterator{
		private Node x = front;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return x != null;
		}

		@Override
		public Value next() {
			// TODO Auto-generated method stub
			Value v = x.v;
			x = x.next;
			return v;
		}
		
	}
	
}
