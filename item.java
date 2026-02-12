
public class Item {
    int rfid;
    String color;
    String size;
    String name;
    double price;

    Item(int rfid, String color, String size , String name, double price){
        this.rfid = rfid;
        this.color = color;
        this.size = size;
        this.price = price;
        this.name = name;
    }


    public double getPrice(){
        return price;
    }

    public int getRFID(){
        return rfid;
    }

    public String getColor(){
        return color;
    }

    public String getSize(){
        return size;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return color + " " + size + " " + name + ", " + price;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }

        if(obj==null || getClass() !=obj.getClass()){
            return false;
        }

        Item other = (Item) obj;
        return this.rfid ==other.rfid;
    }
}
