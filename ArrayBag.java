import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T>{
    private T[] bag;
    int numberOfEntries;
    /*gets current number of items in this bag 
    @return the number integer of entries in current bag*/
    public int getCurrentSize(){
        return 0;
    }

    /* checks whether bag is empty or not
     @return true or false if it's empty or not*/
    public boolean isEmpty(){
        return true;
    }

    /* checks if you can add an entry to this bag, if not return false, if yes add it and return true
     @param newEntry The object to be added is a newEntry
     @return if adding was successful, false if failure*/
    public boolean add(T newEntry){
        return true;
    }

    /*removes any item from the bag
     @returns the removed entry or if the removal was successful or if empty, return null*/
    public T remove(){

    }

    /* removes a specific item from bag
     @param anEntry, the object to be removed is anEntry
     @returns true if successful, false if failure*/
    public boolean remove(T anEntry){

    }

    /* removes all entries in bag
     */
    public void clear(){

    }
    
    /* checks how much times an item reoccurs in the bag
     @param anEntry, the entry to be counted
     @returns the integer that the item appears*/
    public int getFrequencyOf(T anEntry){

    }

    /*  checks whether the item contains an item or not
     @param anEntry, the item that's checked to see if the bag contains it
     @returns true if the bag contains, false if not*/
    public boolean contains(T anEntry){

    }

    /* looks at all objects that are within the bag
     @returns a newly allocated array of all entires inside the bag, if array is empty then it will return another empty array*/
    public T[] toArray(){

    }
}