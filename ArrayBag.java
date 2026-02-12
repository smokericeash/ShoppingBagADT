import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T>{
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 30;
    private static final int MAX_CAPACITY = 100;
    boolean integrityOK;

    //default constructor
    public ArrayBag(){
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor to create an ArrayBag with a certain capacity 
    public ArrayBag(int capacity){
        if(capacity <= MAX_CAPACITY){
            numberOfEntries = 0;
            //casting here
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[capacity];
            bag = tempBag;
            integrityOK=true;
        }
        else{
            throw new IllegalStateException("You have attempted to create a bag above the max capacity");
        }
    } //end constructor 


    /*gets current number of items in this bag 
    @return the number integer of entries in current bag*/
    public int getCurrentSize(){
        checkIntegrity();
        return numberOfEntries;
    }

    /* checks whether bag is empty or not
     @return true or false if it's empty or not*/
    public boolean isEmpty(){
        checkIntegrity();
        return numberOfEntries==0;
    }

    /* checks if you can add an entry to this bag, if not return false, if yes add it and return true
     @param newEntry The object to be added is a newEntry
     @return if adding was successful, false if failure*/
    public boolean add(T newEntry){
        checkIntegrity();
        boolean result = false;

        if(newEntry==null){
            return false;
        }
        if(isFull()){
            doubleCapacity();
        }   
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        result = true;
        return result;

    }

    /*removes any item from the bag
     @returns the removed entry or if the removal was successful or if empty, return null*/
    public T remove(){  
        checkIntegrity();
        T result = removeEntry(numberOfEntries-1);
        return result;

    }

    public T removeEntry(int index){
        T result = null;

        if(!isEmpty() && index >=0){
            result = bag[index];
            bag[index] = bag[numberOfEntries-1];
            bag[numberOfEntries-1] = null;
            numberOfEntries--;
        }

        return result;
    }

    /* removes a specific item from bag
     @param anEntry, the object to be removed is anEntry
     @returns true if successful, false if failure*/
    public boolean remove(T anEntry){
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return result!=null;
    }

    /* removes all entries in bag
     */
    public void clear(){
        checkIntegrity();
        while(!(numberOfEntries==0)){
            bag[numberOfEntries-1] = null;
            numberOfEntries--;
        }
    }
    
    /* checks how much times an item reoccurs in the bag
     @param anEntry, the entry to be counted
     @returns the integer that the item appears*/
    public int getFrequencyOf(T anEntry){
        checkIntegrity();
        int count = 0;
        for(int i=0; i < numberOfEntries ; i++){
            if(bag[i].equals(anEntry)){
                count++;
            }
        }
        return count;
    }

    /*  checks whether the item contains an item or not
     @param anEntry, the item that's checked to see if the bag contains it
     @returns true if the bag contains, false if not*/
    public boolean contains(T anEntry){
        checkIntegrity();
        if(getIndexOf(anEntry) > -1){
            return true;
        }
        return false;
    }

    //getIndexOf shows the location and if the array contains a certain item
    private int getIndexOf(T anEntry){
        checkIntegrity();
        int where = -1;
        boolean found = false;
        int index = 0;
        while(!found && (index < numberOfEntries)){
            if(anEntry!= null && anEntry.equals(bag[index])){
                found = true;
                where = index;
            }
            index++;
        }
        return where;
    }



    /* looks at all objects that are within the bag
     @returns a newly allocated array of all entires inside the bag, if array is empty then it will return another empty array*/
    public T[] toArray(){
        checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] copy = (T[])new Object[numberOfEntries];
        for(int i = 0; i < numberOfEntries; i++){
            copy[i] = bag[i];
        }
        return copy;
    }

    public boolean isFull(){
        checkIntegrity();
        return numberOfEntries==bag.length;
        
    }

    private void checkIntegrity(){
        if(!integrityOK){
            throw new SecurityException("The array object is corrupt");
        }
    }

    //throws exception if capacity goes over the maximum
    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY){
            throw new IllegalStateException("You have attempted to create a bag capacity greater than the maximum of " + MAX_CAPACITY);
        }

    }

    private void doubleCapacity(){
        int newLength = 2*bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }
}