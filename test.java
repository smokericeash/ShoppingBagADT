import java.util.Scanner;

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

        //create scanner 
        Scanner keyboard = new Scanner(System.in);

        boolean breakout = false;
        //while loop will keep asking user to entre choice until they decide to checkout and leave 
        while(!breakout){
            System.out.print("Please enter your choice: ");
            String input = keyboard.nextLine();
            if(input.equalsIgnoreCase("H")){
                System.out.println("gay");
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
