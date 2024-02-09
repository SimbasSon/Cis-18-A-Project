import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrderAndAppointment {

    public static void main(String[] args) {
        // Restaurant menu (array of strings)
        String[] menuItems = {" (10.99)", "Pasta (8.50)", "Salad (7.99)", "Drink (2.50)"};

        // Business operation hours
        String openingTime = "11:00 AM";
        String closingTime = "10:00 PM";

        // User interaction
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Kairu & Family Restaurant Ordering System!");

        // Order details
        String order = "";
        double totalPrice = 0.0;
        while (true) {
            System.out.println("\nMenu:");
            for (int i = 0; i < menuItems.length; i++) {
                System.out.println((i + 1) + ". " + menuItems[i]);
            }
            System.out.println("0. Exit");
            System.out.print("Enter item number (or 0 to exit): ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            if (choice > 0 && choice <= menuItems.length) {
                order += menuItems[choice - 1] + "\n";
                String[] itemPrice = menuItems[choice - 1].split(" ");
                totalPrice += Double.parseDouble(itemPrice[1].substring(1));
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Appointment details
        String date, time;
        while (true) {
            System.out.println("\nDelivery date (YYYY-MM-DD): ");
            date = scanner.next();
            if (isValidDate(date)) {
                break;
            } else {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        while (true) {
            System.out.println("Delivery time (HH:MM): ");
            time = scanner.next();
            if (isValidTime(time, openingTime, closingTime)) {
                break;
            } else {
                System.out.println("Invalid time format or outside business hours. Please try again.");
            }
        }

        // Display Order summary and Appointment
        System.out.println("\nOrder Summary:");
        System.out.println(order);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("\nAppointment:");
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        System.out.println("\nYour order has been placed and appointment confirmed. Thank you!");
    }

    private static boolean isValidDate(String date) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidTime(String time, String openingTime, String closingTime) {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date currentTime = timeFormat.parse(time);
            Date opening = timeFormat.parse(openingTime);
            Date closing = timeFormat.parse(closingTime);
            return currentTime.after(opening) && currentTime.before(closing);
        } catch (Exception e) {
            return false;
        }
    }
}