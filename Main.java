// Online Reservation System

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User 
{
        private String username;
        private String password;
        public User(String username, String password) 
        {
                this.username = username;
                this.password = password;
        }
        public String get_Username() 
        {
                return username;
        }
        public String get_Password() 
        {
                return password;
        }
}

class Reservation 
{
        private static int reservation_Id_Counter = 1;
        private int reservation_Id;
        private String train_Number;
        private String train_Name;
        private String class_Type;
        private String date_of_Journey;
        private String from_Place;
        private String Destination;
        public Reservation(String train_Number, String train_Name, String class_Type,
        String date_of_Journey, String from_Place, String Destination) 
        {
                this.reservation_Id = reservation_Id_Counter++;
                this.train_Number = train_Number;
                this.train_Name = train_Name;
                this.class_Type = class_Type;
                this.date_of_Journey = date_of_Journey;
                this.from_Place = from_Place;
                this.Destination = Destination;
        }
        public int get_Reservation_Id() 
        {
                return reservation_Id;
        }
        public String get_Train_Number() 
        {
                return train_Number;
        }
        public String get_Train_Name() 
        {
                return train_Name;
        }
        public String get_Class_Type() 
        {
                return class_Type;
        }
        public String get_Date_of_Journey() 
        {
                return date_of_Journey;
        }
        public String get_From_Place() 
        {
                return from_Place;
        }
        public String get_To_Destination() 
        {
                return Destination;
        }
}

class Reservation_System 
{
        private List<User> users;
        private List<Reservation> reservations;
        public Reservation_System() 
        {
                this.users = new ArrayList<>();
                this.reservations = new ArrayList<>();
        }
        public void addUser(User user) 
        {
                users.add(user);
        }
        public User authenticateUser(String username, String password) 
        {
                for (User user : users) 
                {
                        if (user.get_Username().equals(username) && user.get_Password().equals(password)) 
                        {
                                return user;
                        }
                }
                return null;
        }
                public void makeReservation(Reservation reservation) 
        {
                reservations.add(reservation);
                System.out.println("Reservation successful. Your reservation ID is: " + reservation.get_Reservation_Id());
        }
        public void cancelReservation(int reservation_Id) 
        {
                Reservation reservationToRemove = reservations.stream()
                .filter(r -> r.get_Reservation_Id() == reservation_Id)
                .findFirst()
                .orElse(null);
                if (reservationToRemove != null) 
                {
                        reservations.remove(reservationToRemove);
                        System.out.println("Reservation canceled successfully.");
                } 
                else 
                {
                        System.out.println("Reservation not found with ID: " + reservation_Id);
                }
        }
        public Reservation get_Reservation_Details(int reservation_Id) 
        {
                return reservations.stream()
                .filter(r -> r.get_Reservation_Id() == reservation_Id)
                .findFirst()
                .orElse(null);
        }
}

public class Main 
{
        public static void main(String[] args) 
        {
                Scanner scanner = new Scanner(System.in);
                Reservation_System reservationSystem = new Reservation_System();
                // Create default users
                User user1 = new User("rochak", "123");
                User user2 = new User("mohit", "234");
                reservationSystem.addUser(user1);
                reservationSystem.addUser(user2);
                User authenticatedUser = null;

                while (authenticatedUser == null)
                {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        authenticatedUser = reservationSystem.authenticateUser(username, password);
                        if (authenticatedUser == null) 
                        {
                                System.out.println("Invalid credentials. Please try again.");
                        }
                }
                int choice;
                do 
                {
                        System.out.println("1. To Make Reservation");
                        System.out.println("2. To Cancel Reservation");
                        System.out.println("3. To View Reservation Details");
                        System.out.println("0. To Logout");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) 
                        {
                                case 1:
                                System.out.print("Enter train number: ");
                                String train_Number = scanner.nextLine();
                                System.out.print("Enter train name: ");
                                String train_Name = scanner.nextLine();
                                System.out.print("Enter class type: ");
                                String class_Type = scanner.nextLine();
                                System.out.print("Enter date of journey: ");
                                String date_of_Journey = scanner.nextLine();
                                System.out.print("Enter from place: ");
                                String from_Place = scanner.nextLine();
                                System.out.print("Enter to destination: ");
                                String Destination = scanner.nextLine();
                                Reservation newReservation = new Reservation(train_Number, train_Name, class_Type,
                                date_of_Journey, from_Place, Destination);
                                reservationSystem.makeReservation(newReservation);
                                break;
                                case 2:
                                System.out.print("Enter reservation ID to cancel: ");
                                int reservation_Id_To_Cancel = scanner.nextInt();
                                scanner.nextLine();
                                Reservation reservationToCancel = reservationSystem.get_Reservation_Details(reservation_Id_To_Cancel);
                                if (reservationToCancel != null) 
                                {
                                        System.out.println("Reservation Details:");
                                        System.out.println("Train Number: " + reservationToCancel.get_Train_Number());
                                        System.out.println("Train Name: " + reservationToCancel.get_Train_Name());
                                        System.out.println("Class Type: " + reservationToCancel.get_Class_Type());
                                        System.out.println("Date of Journey: " + reservationToCancel.get_Date_of_Journey());
                                        System.out.println("From Place: " + reservationToCancel.get_From_Place());
                                        System.out.println("To Destination: " + reservationToCancel.get_To_Destination());
                                        System.out.print("Do you want to cancel this reservation? (yes/no): ");
                                        String cancelChoice = scanner.nextLine().toLowerCase();
                                        if (cancelChoice.equals("yes")) 
                                        {
                                                reservationSystem.cancelReservation(reservation_Id_To_Cancel);
                                                System.out.println("Reservation canceled successfully.");
                                        } 
                                        else 
                                        {
                                                System.out.println("Reservation not canceled.");
                                        }
                                } 
                                else
                                {
                                        System.out.println("Reservation not found with ID: " + reservation_Id_To_Cancel);
                                }
                                break;
                                case 3:
                                System.out.print("Enter reservation ID to view details: ");
                                int reservation_Id_To_View_Details = scanner.nextInt();
                                scanner.nextLine();

                                Reservation details = reservationSystem.get_Reservation_Details(reservation_Id_To_View_Details);
                                if (details != null) 
                                {
                                        System.out.println("Reservation Details:");
                                        System.out.println("Train Number: " + details.get_Train_Number());
                                        System.out.println("Train Name: " + details.get_Train_Name());
                                        System.out.println("Class Type: " + details.get_Class_Type());
                                        System.out.println("Date of Journey: " + details.get_Date_of_Journey());
                                        System.out.println("From Place: " + details.get_From_Place());
                                        System.out.println("To Destination: " + details.get_To_Destination());
                                } 
                                else 
                                {
                                        System.out.println("Reservation not found with ID: " + reservation_Id_To_View_Details);
                                }
                                break;
                                case 0:
                                        System.out.println("Logging out. thankyou!");
                                break;

                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }

                } while (choice != 0);
                scanner.close();
        }

}
