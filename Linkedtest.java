import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Linkedtest {
    public static void main(String[] args){
        // ANSI escape code for bold text
        String ANSI_BOLD = "\u001B[1m";
        // ANSI escape code to reset formatting
        String ANSI_RESET = "\u001B[0m";


        //introduce user to store, list the options
        System.out.println("Welcome to my store! Here are the options: "); 
        System.out.println("H: Print out Help Message");
        System.out.println("A: Add item(s) with a given RFID # followed by the quantity");
        System.out.println("R: Remove item(s) with a given RFID # followed by the quantity");
        System.out.println("C: Combine the current bag with another shopping bag");
        System.out.println("D: Show all the items in the shopping bag with a total price and ask if user wants to check out");

        //initialize the items here indivudally through the item class, each tied to RFID 
        File file = new File("store.csv");
        Item[] inventory = new Item[30];
        Scanner fileScanner = null;
        int inventoryCount = 0;
        try{
            fileScanner = new Scanner(file);
            fileScanner.nextLine();
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                String color = parts[2];
                String size = parts[3];
                int rfid = Integer.parseInt(parts[4]);
                Item item = new Item(rfid, color, size, name, price);
                inventory[inventoryCount] = item;
                inventoryCount++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        finally{
            if(fileScanner != null){
            fileScanner.close();
            }
        }

        //create shopping cart here using resizeable array
        BagInterface<Item> shoppingCart = new LinkedBag<>();

        //create scanner 
        Scanner keyboard = new Scanner(System.in);

        boolean breakout = false;
        //delcare rfid and quantity variables here
        int rfid;
        int quantity;
        Item foundItem = null;
        boolean combined = false;
        //while loop will keep asking user to entre choice until they decide to checkout and leave 
        while(!breakout){
            System.out.print("Please enter your choice: ");
            String input = keyboard.nextLine();
            String[] parts = input.split("\\s+");
            if(input.equalsIgnoreCase("H")){
                System.out.println("Option A adds in an item of your choice. The format should be \"A (rfid) (quantity)\"");
                System.out.println("Option R removes an item of your choice. The format should be \"R (rfid) (quantity)\" ");
                System.out.println("Option C consolidates two bags, adding in items you want. The format should be \"(rfid) (quantity), (rfid) (quantity), etc\"");
                System.out.println("Option D displays all your items in your shopping cart and the total price.");

            }
            else if(parts.length==3 && parts[0].equalsIgnoreCase("A")){
                try{
                    rfid = Integer.parseInt(parts[1]);
                    quantity = Integer.parseInt(parts[2]);
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid. RFID and quantity must be numbers after your option."); //make sure user inputs the correct format when inputting RFID and quantity
                    return;
                }

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
            else if(parts.length==3 && parts[0].equalsIgnoreCase("R")){
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
                
                
                if(foundItem==null){
                    System.out.println("That is not a valid RFID number.");
                }
                else{
                    for(int i = 0; i < quantity-1; i++){ //looping through the quantity the user wants to remove from the bag
                        shoppingCart.remove(foundItem); //removing item if foundItem is not null, which means the method found a valid RFID number
                    }
                    if(!shoppingCart.remove(foundItem)){
                        System.out.println("You cannot remove more of this item, because it doesn't exist or you set too high of a quantity to remove.");
                    }
                    else{
                        System.out.println(foundItem.toString()+ " (" + quantity + ")" + " removed from your bag.");
                    }
                }

            }
            //option C, combining both bags for the users 
            else if(input.equalsIgnoreCase("C")){
                rfid = 0;
                quantity = 0;
                System.out.print("What's in the other bag?: "); 
                String nextInput = keyboard.nextLine();
                String[] group = nextInput.split(",");
                for(String groups: group){
                    String[] piece = groups.trim().split("\\s+");

                    if(piece.length!=2){
                        System.out.println("Incorrect format, use: rfid quantity, rfid quantity");
                        continue;
                    }

                    try{
                    rfid = Integer.parseInt(piece[0]);
                    quantity = Integer.parseInt(piece[1]);

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
                    

                        combined = true;
                    }

                    }
                    //end try 
                    catch(NumberFormatException e){
                        System.out.println("RFID and quantity must be numbers.");
                    }
                    //end catch

                }
            
            if(combined){
                System.out.println("Bags combined");
            }

            


            }

            else if(input.equalsIgnoreCase("D")){
                //converting the items into an array from shopping cart
                Object[] cartItems = shoppingCart.toArray();
                System.out.println("Here's your shopping cart: ");
                System.out.println(ANSI_BOLD + "Item" + "\t" + "Name" + "\t\t" + "Price" + "\t" + "Quantity" + ANSI_RESET); //bold the section headers

                //reset price to 0
                double total = 0.0;
                
                //checking through all of the carts to make sure program hasn't printed the same item twice
                for(int i = 0; i < cartItems.length; i++){ 
                    Item currentItem = (Item) cartItems[i];

                    boolean alreadyPrinted = false;
                    for(int j = 0; j < i; j++){
                        if(cartItems[j].equals(currentItem)){
                            alreadyPrinted = true;
                            break;
                        }
                    }


                    //prints the rest of the price, name, and rfid when the loop confirms the program hasn't yet printed out these values yet 
                    if(!alreadyPrinted){
                        
                        int amount = shoppingCart.getFrequencyOf(currentItem);
                        
                        //calculating total price with currentItem multiplied by amount
                        total += currentItem.getPrice()*amount;

                        System.out.println(
                            currentItem.getRFID() + "\t" + 
                            currentItem.getName() + "\t" + 
                            currentItem.getPrice() + "\t" +
                            amount
                        );
                    }

                }

                System.out.printf("Total price: $%.2f%n", total);
                
                System.out.print("Are you ready to check out? ");
                String choice = keyboard.nextLine();
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")){
                    breakout=true;

                }
                else if(!(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No"))){
                    System.out.println("That is not a valid input, please try again.");
                }
            }
            else{
                System.out.println("Sorry that is not a valid entry, please try again.");
            }

        }

        System.out.println("Thank you for shopping at my store, see you next time!");

        keyboard.close();
    }
}
