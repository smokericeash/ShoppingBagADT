import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class test {
    public static void main(String[] args){
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
        try{
            fileScanner = new Scanner(file);
            fileScanner.nextLine();
            int count = 0;
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                String color = parts[2];
                String size = parts[3];
                int rfid = Integer.parseInt(parts[4]);
                Item item = new Item(rfid, color, size, name, price);
                inventory[count] = item;
                count++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        finally{
            fileScanner.close();
        }

        //create shopping cart here using resizeable array
        BagInterface<Item> shoppingCart = new ArrayBag<>(30);

        //create scanner 
        Scanner keyboard = new Scanner(System.in);

        boolean breakout = false;
        //while loop will keep asking user to entre choice until they decide to checkout and leave 
        while(!breakout){
            System.out.print("Please enter your choice: ");
            String input = keyboard.nextLine();
            if(input.equalsIgnoreCase("H")){
                System.out.println(inventory[0]);
            }
            else if(input.equalsIgnoreCase("A")){
                System.out.println("");
            }
            else if(input.equalsIgnoreCase("R")){
                System.out.println("");
            }
            else if(input.equalsIgnoreCase("C")){
                System.out.println("");
            }
            else if(input.equalsIgnoreCase("D")){
                System.out.println("Here's your shopping cart: ");

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
