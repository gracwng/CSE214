public class IntList {
    private IntNode head;
    private IntNode cursor;
    private IntNode tail;

    public IntList(){
        head = null;
        cursor = null;
        tail = null;
    }

    //adding new node to the head of the linked list
    public void addNewHead(int data){
        IntNode newNode = new IntNode(data);
        newNode.setLink(head);
        head = newNode;
        if (tail == null){
            tail = head;
        }
        cursor = head;

    }

    //add new node after the cursor
    public void addIntAfter(int data){
        //this is what happens if the list is empty. if the list is empty, then you set the new node as head, tail and cursor
        IntNode newNode = new IntNode(data);
        if (head == null){
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        else{
            //this works even if the cursor is at the tail 
            newNode.setLink(cursor.getLink());
            cursor.setLink(newNode);
            cursor = newNode;
            //what if the tail is empty and you're at the end of the list
            if (cursor.getLink() == null){
                tail = cursor;
            }
            //you have to check all cases, and update cursor, tail, and head when and if necessary
        }
        
    }

    //remove an integer after the cursor    //FIX THIS: consider all possibilites
    public void removeIntAfter(){
        if (cursor == tail){
            tail = cursor;
        }
        else{   //if the cursor is not pointing to the tail
            cursor.setLink(cursor.getLink().getLink());
        // what if cursor's get link is null, then the cursor is the tail

        }
    }

    //remove the head of the list
    public void removeHead(){
        if (head != null){
            head = head.getLink();
        }
        //if the head is empty, then you also have to set the tail to null
        if (head == null){
            tail = null;    //question: if head is null, doesn't it imply that the tail is null too?
        }
        cursor = head;
    }

    //move cursor forward
    public boolean advanceCursor(){
        //if linked list is null, you can't advance cursor
        if (cursor != tail){
            cursor = cursor.getLink();
            return true;
        }
        else return false;
    }

    //working with cursor

    //reset cursor to head

    public void resetCursor(){
        cursor = head;
    }

    //check if list is empty
    public boolean isEmpty(){
        //if cursor is null, then it implies that head and tail is null
        return (cursor == null);
    }

    //traverse a list to... find the number of nodes, count the number of even nodes. traversing allows us to visit all the nodes in the list
    //you will need a temporary variable to traverse the list
    
    //length of the list
    public int listLength(){
        IntNode temp = head;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.getLink();
        }
        return count;
    }

    //search the list requires traversing
    //returning boolean lets us know if there is a target but not what the actual target is
    public boolean listSearch(int target){
        //return is boolean because that element may or may not be present
        IntNode temp = head;
        while (temp != null){
            if (target == temp.getData()){
                //REMEMBER: set cursor to that position so you know where you are
                cursor = temp;
                return true;
            }
            temp = temp.getLink();  //this is how you advance the node
        }
        return false;
    }

    //we shouldnt replace temp with cursor. Don't use cursor bc then you will lose the place of the cursor which you need for other reasons

    //another way to search the list. this way returns the reference to the next node
    //the only difference is that the intNode is returned
            //this method doesn't change cursor

    // public IntNode listSearch(int target){
    //         IntNode temp = head;
    //         while(temp != null && target != temp.getData())  //does it matter if I change the order of the conditions?: yes! because if you reached the end fo the list, then you will get a NullPointerException. The first thing to check is to see if it is pointing to null
    //             temp = temp.getLink();
    //         if (temp == null)
    //         return false;
    //         cursor = temp;
    //         return true;
    // }

    // set cursor to specific position
    // public boolean listPosition(int position) throws IllegalArgumentException{
    //     if (position <= 0){
    //         throw new IllegalArgumentException ("Position cannot be negative");
    //     }
    //     else{
    //         IntNode temp = head;
    //         int i = 1;  //because head is in position 1
    //         while (i < position && temp != null){
    //             temp = temp.getLink();
    //             i++;
    //         }
    //     }
    //  fix this: 
    //     if (temp != null){
    //         cursor = temp;
    //     }
    //     return (temp != null);
    // }

    //copy a list (not clone)
    public static IntList listCopy (IntList source){
        //we are creating a new linked list with the content of the source list
        IntList copy = new IntList();
        IntNode temp = source.head;
        while (temp != null){
            copy.addIntAfter(temp.getData());    //USING METHODS WITHIN METHODS
            temp = temp.getLink();

        }
        return copy;
    }

    
    //when removing an element in a singly linked list, it's not as simple as a double linked list
    //this is because you can't go back, and you need the previous node in order to set that node's next link to the one after the cursor 
    //if we're trying to remove the element at the cursor. So you need a trailing cursor to keep the information of the previous node
    //
    public boolean removeElemnt(int target){
        IntNode temp = head;
        IntNode trailing = null;
        while (temp != null){
            if (temp.getData() == target){
                //what if there is only one element in the list
                if (head == tail){
                    head = null;
                    tail = null;
                    cursor = null;
                }
                //what if the 3element is the first element: 
                else if (temp == head){
                    cursor = head.getLink();
                }
                //what if element is the last element?
                else if (temp == tail){
                    //set cursor to trailing, and set trailings get next to null
                    cursor = trailing;
                    cursor.setLink(null);
                }
                //if the elemnt is not the first element or last element: 
                else {
                    trailing.setLink(temp.getLink());   //would this result in error since trailing is set to null?
                    cursor = head.getLink();
                }
                return true;
            }
            trailing = temp;
            temp = temp.getLink();
        }
        return false;
    }

    //lecture implementation of remove
    public boolean remove(int target){
        IntNode temp = head;
        IntNode trailing = null;
        while (temp != null && temp.getData() != target){
            trailing = temp;
            temp = temp.getLink();
        }
        if (temp != null){
            trailing.setLink(temp.getLink());
        }
        return (temp != null);
    }

    //optimizing the remove method that doesn't use a trailing node
    //what happens if you delete the tail? you'd have to reset it

    public boolean removeWithoutTrail(int target){
        IntNode temp = head;
        if (target == head.getData())
        head = head.getLink();
        else{
            while (temp != tail && temp.getLink().getData() != target){
                temp = temp.getLink();
            }
            if (temp != tail){
                temp.setLink(temp.getLink().getLink());
            }
            
        }
        return (temp != tail);
        }

        //additional intList methods

        public int getNodeData() throws EmptyListException {
            if (cursor == null){
                throw new EmptyListException("List is empty");
            }
            return (cursor.getData());
        }

        public void setNodeData(int element) throws EmptyListException {
            if (cursor == null){
                throw new EmptyListException("List is empty");
            }
            cursor.setData(element);
        }

        


    }



