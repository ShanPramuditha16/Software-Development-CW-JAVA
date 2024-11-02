import javax.xml.namespace.QName;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;



public class w2052062_PlaneManagement {

/*The code below is used for make arrays for the seats and tickets*/
    static int[][] seats = new int[5][];
    static Ticket[][] tickets = new Ticket[5][];


    public static void main(String[] args) {


        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[0];
        seats[3] = new int[12];
        seats[4] = new int[14];

        tickets[0] = new Ticket[14];
        tickets[1] = new Ticket[12];
        tickets[2] = new Ticket[0];
        tickets[3] = new Ticket[12];
        tickets[4] = new Ticket[14];

/*The code below will print the Plane management menu options screen and I used a switch statement for the options in the menu */
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Plane Management application");

            System.out.println("***********************************");
            System.out.println(" *           MENU OPTIONS        * ");
            System.out.println("***********************************");
            System.out.println("      1)Buy a seat        ");
            System.out.println("      2)Cancel a seat     ");
            System.out.println("      3)Find first available seat");
            System.out.println("      4)Show seating plan ");
            System.out.println("      5)Print tickets information and total sales ");
            System.out.println("      6)Search ticket     ");
            System.out.println("      0)Quit              ");

            System.out.print("Please select an option: ");
            try {
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for option.");
                input.nextLine();
                continue;
            }
        }
    }
/*buy seat method. this code is used for buying a seat on the plane, checking if the entered seat is valid or booked and saving the ticket  */
    public static void buy_seat() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = input.next();
        System.out.println("Please enter your surname");
        String surname = input.next();
        System.out.println("Please enter your email");
        String email = input.next();
        int row_int = 0;
        System.out.println("Input the row letter: ");
        String rownum = input.next().toUpperCase();
        System.out.println("Input the seat number: ");
        int seatnum = input.nextInt();
        if (!Arrays.asList("A", "B", "C", "D").contains(rownum)) {
            System.out.println("Invalid row entered.");
            return;
        }
        if (rownum.equals("A")) {
            row_int = 0;
        } else if (rownum.equals("B")) {
            row_int = 1;
        } else if (rownum.equals("C")) {
            row_int = 3;
        } else if (rownum.equals("D")) {
            row_int = 4;
        }
        if (seats[row_int][seatnum - 1] != 1) {
            seats[row_int][seatnum - 1] = 1;
            if (seatnum <= 5) {
                Person newperson = new Person(name, surname, email);

                Ticket newticket = new Ticket(rownum, seatnum, 200, newperson);
                tickets[row_int][seatnum - 1] = newticket;
                newticket.save();

            } else if (seatnum >= 10) {
                Person newperson = new Person(name, surname, email);

                Ticket newticket = new Ticket(rownum, seatnum, 180, newperson);
                tickets[row_int][seatnum - 1] = newticket;
                newticket.save();

            } else {
                Person newperson = new Person(name, surname, email);

                Ticket newticket = new Ticket(rownum, seatnum, 150, newperson);
                tickets[row_int][seatnum - 1] = newticket;
                newticket.save();

            }
            System.out.println("Ticket purchased successfully");
        } else {
            System.out.println("This seat is sold");
        }
    }
//This code is for cancelling a seat
    public static void cancel_seat() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input the row letter: ");
        String cancelrow = input.next().toUpperCase();
        System.out.print("Input the seat number: ");
        int cancelnum = input.nextInt();
        int row_int = 0;
        if (cancelrow.equals("A")) {
            row_int = 0;
        } else if (cancelrow.equals("B")) {
            row_int = 1;
        } else if (cancelrow.equals("C")) {
            row_int = 3;
        } else if (cancelrow.equals("D")) {
            row_int = 4;
        }
        if (!Arrays.asList("A", "B", "C", "D").contains(cancelrow)) {
            System.out.println("Invalid row entered.");
            return;
        }
        if (seats[row_int][cancelnum - 1] == 1) {
            seats[row_int][cancelnum - 1] = 0;
            tickets[row_int][cancelnum - 1] = null;
        }else {
            System.out.println("Seat is cancelled");

        }

    }
/*This method is used to find the first available seat on the plane */
    public static void find_first_available() {
        int row = 0;
        int col = 0;
        while (row < seats.length) {
            if (seats[row][col] == 0) {
                if (row == 0) {
                    System.out.println("This seat is still available: " + "A" + (col + 1));
                } else if (row == 1) {
                    System.out.println("This seat is still available: " + "B" + (col + 1));
                } else if (row == 2) {
                    System.out.println("This seat is still available: " + "C" + (col + 1));
                } else {
                    System.out.println("This seat is still available: " + "D" + (col + 1));
                }
                break;
            }
            col++;
            if (col >= seats[row].length) {
                row++;
                col = 0;
            }
        }

    }
/*This method displays a seating plan where available seats are marked with 0 and booked seats are marked with x.*/
    public static void show_seating_plan() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }

    }
/*The below code will print all the ticket information. row seat number, price, person info, name, surname, and email. after that, it will show the total amount*/
    public static void print_tickets_info(){
        System.out.println("************* Ticket Information ************");
        int total = 0;
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0;j <tickets[i].length;j++){
                Ticket ticket = tickets[i][j];
                if (ticket!=null) {
                    System.out.println("Row: "+ticket.getRow());
                    System.out.println("Seat Number: " + ticket.getSeat());
                    System.out.println("Price: " + ticket.getPrice());
                    System.out.println("Person Information:");
                    System.out.println("Name: " + ticket.getPerson().getName());
                    System.out.println("Surname: " + ticket.getPerson().getSurname());
                    System.out.println("Email: " + ticket.getPerson().getEmail());
                    total += ticket.getPrice();
                }
            }
        }
        System.out.println("Total amount : £"+total);
    }
/*The below code helps to search for a ticket, and it shows if it is still available or not */
    public static void search_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input the row letter: ");
        String searchrow = input.next();
        System.out.print("Input the seat number: ");
        int searchseat = input.nextInt();
        int row_int = 0;
        if (searchrow.equals("A")){
            row_int = 0;
        }else if (searchrow.equals("B")){
            row_int = 1;
        }else if (searchrow.equals("C")) {
            row_int = 3;
        }else if (searchrow.equals("D")) {
            row_int = 4;
        }
        Ticket ticket = tickets[row_int][searchseat-1];
        if (tickets[row_int][searchseat-1] != null){
            System.out.println("Row: " + ticket.getRow());
            System.out.println("Seat Number: " + ticket.getSeat());
            System.out.println("Price: " + ticket.getPrice());
            System.out.println("Person Information:");
            System.out.println("Name: " + ticket.getPerson().getName());
            System.out.println("Surname: " + ticket.getPerson().getSurname());
            System.out.println("Email: " + ticket.getPerson().getEmail());
        }else {
            System.out.println("This seat is available");
        }
    }
}
























