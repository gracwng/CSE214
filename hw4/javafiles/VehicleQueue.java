/**
 * Lanes in our simulator will be modelled as a Queue of Vehicles. You may implement a Queue of vehicles however you like, 
 * and are encouraged to use any Java API class you prefer. Remember that the VehicleQueue class must implement the following
 * methods in order to comply with the Queue ADT:
 * 
 * void enqueue(Vehicle v)
 * Vehicle dequeue()
 * int size()
 * boolean isEmpty()
 * Note: If you decide to use a Java API class to implement VehicleQueue, you must use inheritance (extend a Java API class) 
 * to simplify the class definition.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 //import java.util.Queue; //this package provides the methods of the queue ADT but it doesn't include the implementation of it. 
import java.util.LinkedList;    //therefore, I have to use an extra data structure that will allow me to implement the queue data structures
//this is different from the Stacks ADT/package in java which includes the interface and the implementation
//the purpose of extending the linkedList class is that it allows me access to all the methods and instances of a linkedList object without
//specifying its instance. Because of inheritance, I can create my own queue methods in this class with the additional inherited
//methods of the linkedlist class

/*By extending the LinkedList class, you are inheriting all the methods and properties of the LinkedList class, 
which allows you to use them in your VehicleQueue class. Your implementation of the enqueue() and dequeue() methods uses 
the addLast() and remove() methods respectively, which are inherited from the LinkedList class.
 */
public class VehicleQueue extends LinkedList <Vehicle>  {
    LinkedList<Vehicle> queue;

    public VehicleQueue(){
        queue = new LinkedList<Vehicle>();
    }
    //adds vehicle to the end of the linked list
    
    /**
     * Enqueues vehicle onto queue
     * @param v
     * enqueues vehicle v 
     */
    public void enqueue(Vehicle v){
        queue.addLast(v);
    }

    //removes vehicle from the front of the linked list
    /**
     * Dequeue removes vehicle from the front of the linked list
     * @return
     * removes vehicle
     */
    public Vehicle dequeue(){
        Vehicle v = queue.peek();
        queue.remove(v);
        return v;
    }
    
    //int size() method is already implemented by the linkedList class

    //returns true or false if the queue is empty
    /**
     * isEmpty checks if queue is empty
     * @returns if queue is empty or not
     */
    public boolean isEmpty(){
        return (queue.size() == 0);
    }

    // public String toString(){
    //     String s = "";
    //     VehicleQueue copyQueue = new VehicleQueue();
    //     while (!queue.isEmpty()){
    //         Vehicle x = dequeue();
    //         s += x.getSerialId() + " ";
    //         copyQueue.enqueue(x);
    //     }

    //     while (!copyQueue.isEmpty()){
    //         Vehicle x = copyQueue.dequeue();
    //         enque//ue(x);
    //     }

//         return s;
//     }

//     //  public String forwardWay(){
      
//     //  }

  }

/*do i have to include the isntance of a queue
 * 
 * No, you don't need to include an instance of a Queue in your VehicleQueue class since you are extending the LinkedList class, 
 * which already implements the Queue interface. By extending the LinkedList class, you are inheriting all the methods and 
 * properties of the LinkedList class, including those of the Queue interface, and you can use them in your VehicleQueue class.
 * 
 * Therefore, your VehicleQueue class doesn't need an additional instance of a Queue, and you can use the methods provided by the
 * LinkedList class to implement the enqueue, dequeue, isEmpty, and size methods for your queue.
 */
