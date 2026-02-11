Project 1 – Real-World Shopping Bag (RFID Checkout System)

This Java console application simulates a real-world RFID-based retail self-checkout system. The program processes merchandise data from store.csv and allows users to manage a virtual shopping bag through an interactive interface.

Program Functionality

The system:

Reads and processes merchandise data from store.csv

Stores product information (item name, price, color, size, RFID)

Allows users to:

H – Display help menu

A – Add item(s) by RFID and quantity

R – Remove item(s) by RFID and quantity

C – Combine the current bag with another bag

D – Display shopping cart contents and total price

Prompts the user to complete checkout

Handles invalid input and missing items appropriately

The final output displays a formatted shopping cart including item number, name, price, quantity, and total price.

Technical Implementation

Built upon the Bag ADT

Implemented using generic programming

Two versions provided:

ResizableArrayBag

LinkedBag

Inventory is dynamically loaded from store.csv

Each product is represented as an Item object

How to Run
javac *.java
java Test


Ensure store.csv is in the same directory as the source files.

Author: Aidan Chen
