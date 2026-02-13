import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class test {
    public static void main(String[] args){
        // ANSI escape code for bold text
        String ANSI_BOLD = "\u001B[1m";
        // ANSI escape code to reset formatting
        String ANSI_RESET = "\u001B[0m";

        double grandTotal;

        //introduce user to store, list the options
        System.out.println("Welcome to my store! Here are the options: "); 
        System.out.println("H: Print out Help Message");
        System.out.println("A: Add item(s) with a given RFID # followed by the quantity");
        System.out.println("R: Remove item(s) with a given RFID # followed by the quantity");
        System.out.println("C: Combine the current bag with another shopping bag");
        System.out.println("D: Show all the items in the shopping bag with a total price and ask if user wants to check out");

        //initialize the items here indivudally through the item class, each tied to RFID 
        File file = new File("store.csv");

        //create a tracker variable that holds item contents
        Item[] inventory = new Item[30];
        Scanner fileScanner = null;
        int inventoryCount = 0;
        try{
            fileScanner = new Scanner(file);
            fileScanner.nextLine();
            while(fileScanner.hasNextLine()){
                //keep running while there's more content to read
                String line = fileScanner.nextLine();
                String[] parts = line.split(","); // split the CSV row into individual values (name, price, color, size, RFID)

                /* Assigns array element values from 0 to 3, based on where we split the CSV row previously
                 */
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                String color = parts[2];
                String size = parts[3];
                int rfid = Integer.parseInt(parts[4]);
                //create an item object to store all of the values inside 
                Item item = new Item(rfid, color, size, name, price);
                //store item content inside inventory
                inventory[inventoryCount] = item;
                inventoryCount++; //increment
            }
        }
        catch(FileNotFoundException e){ //catches exception if CSV file isn't in folder
            System.out.println("File not found");
        }
        finally{ //closes scanner 
            if(fileScanner != null){
            fileScanner.close();
            }
        }

        //create shopping cart here using resizeable array
        BagInterface<Item> shoppingCart = new ArrayBag<>(30);

        //create scanner 
        Scanner keyboard = new Scanner(System.in);

        boolean breakout = false;
        //delcare rfid and quantity variables here
        int rfid;
        int quantity;
        Item foundItem = null;
        //while loop will keep asking user to entre choice until they decide to checkout and leave 
        while(!breakout){
            System.out.print("Please enter your choice: ");
            String input = keyboard.nextLine();
            String[] parts = input.split("\\s+");
            //prompt user with options
            if(input.equalsIgnoreCase("H")){ 
                System.out.println("H: Print out Help Message");
                System.out.println("A: Add item(s) with a given RFID # followed by the quantity");
                System.out.println("R: Remove item(s) with a given RFID # followed by the quantity");
                System.out.println("C: Combine the current bag with another shopping bag");
                System.out.println("D: Show all the items in the shopping bag with a total price and ask if user wants to check out");

            }
            else if(parts.length==3 && parts[0].equalsIgnoreCase("A")){
                try{
                    rfid = Integer.parseInt(parts[1]);
                    quantity = Integer.parseInt(parts[2]);
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid. RFID and quantity must be numbers after your option.");
                    return;
                }

                //set foundItem to null before calling each method 
                foundItem = null;
                for(int i = 0; i < inventoryCount; i++){
                    if(inventory[i].getRFID()==rfid){
                        foundItem = inventory[i];
                        break;
                    }
                }
                
                
                if(foundItem==null){
                    System.out.println("That is not a valid RFID number.");
                }
                else{
                    for(int i = 0; i < quantity; i++){ //looping through the quantity on how much items this user wants to add
                        shoppingCart.add(foundItem); //adding item if foundItem is not null, which means the method found a valid RFID number
                    }
                    System.out.println(foundItem.toString()+ " (" + quantity + ")" + " placed in your bag.");
                }
                
                
            }
            else if(parts.length==3 && parts[0].equalsIgnoreCase("R")){ //checking if user correctly puts in right format, 3 numbers seperated by spaces 
                try{
                    rfid = Integer.parseInt(parts[1]);
                    quantity = Integer.parseInt(parts[2]);
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid. RFID and quantity must be numbers after your option.");
                    return;
                }

                foundItem = null;
                for(int i = 0; i < inventoryCount; i++){
                    if(inventory[i].getRFID()==rfid){
                        foundItem = inventory[i];
                        break;
                    }
                }
                
                
                if(foundItem==null){ //checking if the method found the item, if not return an error
                    System.out.println("That is not a valid RFID number.");
                }
                else{
                    for(int i = 0; i < quantity; i++){ //looping through the quantity the user wants to remove from the bag
                        shoppingCart.remove(foundItem); //removing item if foundItem is not null, which means the method found a valid RFID number
                    }
                    System.out.println(foundItem.toString()+ " (" + quantity + ")" + " removed from your bag.");
                }

            }
            else if(input.equalsIgnoreCase("C")){
                System.out.print("What's in the other bag?: "); //ask user to input contents of bag they want to consolidate
                String bagContent = keyboard.nextLine();    
                String pieces[] = bagContent.split("\\s+");
                System.out.println(pieces[0]);
            }

            

            else if(input.equalsIgnoreCase("D")){
                Object[] cartItems = shoppingCart.toArray();
                System.out.println("Here's your shopping cart: ");
                System.out.println(ANSI_BOLD + "Item" + "\t" + "Name" + "\t\t" + "Price" + "\t" + "Quantity" + ANSI_RESET); //bold the section headers

                double total = 0.0;
                
                for(int i = 0; i < cartItems.length; i++){
                    Item currentItem = (Item) cartItems[i];

                    boolean alreadyPrinted = false;
                    for(int j = 0; j < i; j++){
                        if(cartItems[j].equals(currentItem)){
                            alreadyPrinted = true;
                            break;
                        }
                    }

                    int amount = shoppingCart.getFrequencyOf(currentItem);

                    total = currentItem.getPrice()*amount;


                    if(!alreadyPrinted){

                        System.out.println(
                            currentItem.getRFID() + "\t" + 
                            currentItem.getName() + "\t" + 
                            currentItem.getPrice() + "\t" +
                            amount
                        );
                    }

                }

                System.out.println("Total price: $" + total);
                
                System.out.print("Are you ready to check out? ");
                String choice = keyboard.nextLine();
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")){ //asking user if they want to leave, if so, it breaks out of loop
                    breakout=true;

                }
                else if(!(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No"))){ //checks if user types in no, then will restart while loop
                    System.out.println("That is not a valid input, please try again.");
                }
            }
            else{
                System.out.println("Sorry that is not a valid entry, please try again.");
            }

        }

        System.out.println("Thank you for shopping at my store, see you next time!");

        //close keyboard
        keyboard.close();
    }
}
