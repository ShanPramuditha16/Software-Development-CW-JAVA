import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    //getters and setters
    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }
    public void setRow(String row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    public void save() {


        String fileName = row + seat + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {


            writer.write("Row: " + row + "\n");
            writer.write("Seat Number: " + seat + "\n");
            writer.write("Price: " + price + "\n");
            writer.write("Person Information:\n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            System.out.println("Ticket information saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information to file.");
        }
    }
}
