package part2;

public class Device {
    String name;
    int year;
    int price;
    String color;
    String type;

    public Device(String name, int year, int price, String color, String type) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.color = color;
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format("%s Details: \n" +
                        "Year: %d \n" +
                        "Price: %d \n" +
                        "Color: %s \n" +
                        "Type: %s\n",
                name, year, price, color, type);
    }
}
