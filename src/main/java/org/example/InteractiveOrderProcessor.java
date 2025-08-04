package org.example;
import java.util.Scanner;

public class InteractiveOrderProcessor {
    static Scanner sc = new Scanner(System.in);
    static double unitPrice;
    static int quantity;
    static boolean isMember;
    static String customerTier;
    static String shippingZone;
    static String discountCode;

    public static void main(String[] args) {
        //PART 1
        System.out.println("Welcome to the Interactive Order Processor!");

        System.out.println("--- Enter Order Details ---");
        System.out.println("Enter unit price: ");
        unitPrice = sc.nextDouble();
        System.out.println("Enter quantity: ");
        quantity = sc.nextInt();
        System.out.println("Is customer a member (true/false)?: ");
        isMember = sc.nextBoolean();
        System.out.println("Enter customer tier (Regular, Silver, Gold): ");
        customerTier = sc.nextLine();
        System.out.println("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown):");
        shippingZone = sc.nextLine();
        System.out.println("Enter discount code (SAVE10, FREESHIP, or \"\" for none):");
        discountCode = sc.nextLine();


        System.out.println("--- Order Details ---");
        System.out.println("Unit Price: " + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        System.out.println("--- Calculation Steps ---");
        System.out.println("Initial Subtotal: ");
        System.out.println("After Tier Discount (Gold - 15%): ");
        System.out.println("After Quantity Discount (5% for >=5 items): ");
        System.out.println("After Promotional Code (SAVE10 for >$75): ");
        System.out.println("After Small Order Surcharge (if applicable): ");

        System.out.println("Shipping Cost: ");

        System.out.println("Final Order Total: ");



    }
}