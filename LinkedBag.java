public class LinkedBag<T> implements BagInterface<T>{
    Node firstNode;
    int numberOfEntries;

    /*gets current number of items in this bag 
    @return the number integer of entries in current bag*/
    @Override
    public int getCurrentSize(){
        return numberOfEntries;
    }

    /* checks whether bag is empty or not
     @return true or false if it's empty or not*/
    public boolean isEmpty(){
        return numberOfEntries==0;
    }

    /* checks if you can add an entry to this bag, if not return false, if yes add it and return true
     @param newEntry The object to be added is a newEntry
     @return if adding was successful, false if failure*/
    public boolean add(T newEntry){
        Node newNode = new Node(newEntry);

        newNode.next = firstNode;

        firstNode = newNode;
        numberOfEntries++;

        return true;

    }
    

    /*removes any item from the bag
     @returns the removed entry or if the removal was successful or if empty, return null*/
    public T remove(){
        T result = null;
        if (firstNode!=null){
            result = firstNode.getData();
            firstNode=firstNode.next;
            numberOfEntries--;
        }
        return result;
    }

    /* removes a specific item from bag
     @param anEntry, the object to be removed is anEntry
     @returns true if successful, false if failure*/
    public boolean remove(T anEntry){
        boolean result = false;

        Node nodeN = getReferenceTo(anEntry);
        if(nodeN !=null){
            //replace located entry with entry in first
            nodeN.setData(firstNode.getData());
            firstNode = firstNode.next;

            numberOfEntries--;
   
            result = true; 
        }
        return result;
    }

    /* removes all entries in bag
     */
    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }
    
    /* checks how much times an item reoccurs in the bag
     @param anEntry, the entry to be counted
     @returns the integer that the item appears*/
    public int getFrequencyOf(T anEntry){
        int result = 0;
        Node currentNode = firstNode;
        while(currentNode!=null){
            if(anEntry.equals(currentNode.getData())){
                result++;
            }
            currentNode = currentNode.next;
        }
        return result;
    }

    /*  checks whether the item contains an item or not
     @param anEntry, the item that's checked to see if the bag contains it
     @returns true if the bag contains, false if not*/
    public boolean contains(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode!=null)){
            if(anEntry.equals(currentNode.getData())){
                found = true;
            }
            else{
                currentNode = currentNode.next;
            }
        }
        return found;
    }

    /* looks at all objects that are within the bag
     @returns a newly allocated array of all entires inside the bag, if array is empty then it will return another empty array*/
     @SuppressWarnings("unchecked")
    public T[] toArray(){
        T[] bag = (T[])new Object[numberOfEntries];
        Node currentNode = firstNode;
        int index = 0;
        while(currentNode!=null){
            bag[index] = currentNode.getData();
            index++;
            currentNode = currentNode.next;
        }
        return bag;
    }

    public LinkedBag(){
        firstNode=null;
        numberOfEntries = 0;
    }


    Node getReferenceTo(T anEntry){
            boolean found = false;
            Node currentNode = firstNode;

            while(!found && (currentNode!=null)){
                if(anEntry.equals(currentNode.getData())){
                    found = true;
                }
                else{
                    currentNode = currentNode.next;
                }
            }
            return currentNode;

        }


    private class Node{
        T data;
        Node next;

        Node(T data){
            this.data = data;
            this.next = null;
        }

        T getData(){
            return data;
        }

        private void setData(T data){
            this.data = data;
        }


    }
}
