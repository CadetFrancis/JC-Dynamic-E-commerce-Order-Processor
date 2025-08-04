package org.example;
import java.util.HashMap;
import java.util.Map;
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
        InteractiveOrderProcessor newOrder = new InteractiveOrderProcessor();
        TierDiscount td = newOrder.tierBasedDiscount();
        double subtotal = newOrder.initialSubtotal();


        System.out.println("Welcome to the Interactive Order Processor!\n");
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
        System.out.println("Initial Subtotal: " + newOrder.initialSubtotal());
        System.out.println("After Tier Discount "+ td.description +": " + td.rate);
        System.out.println("After Quantity Discount "+  + ": ");
        System.out.println("After Promotional Code (SAVE10 for >$75): ");
        System.out.println("After Small Order Surcharge (if applicable): ");

        System.out.println("Shipping Cost: ");

        System.out.println("Final Order Total: ");

        sc.close();
    }





    public double initialSubtotal(){
        return unitPrice * quantity;
    }

    public record TierDiscount(String description, double rate) {}

    public TierDiscount tierBasedDiscount() {
        if (customerTier.equalsIgnoreCase("gold")) {
            return new TierDiscount("Gold - 15%", 0.15);
        } else if (customerTier.equalsIgnoreCase("silver")) {
            return new TierDiscount("Silver - 10%", 0.10);
        } else {
            return new TierDiscount("Regular - 0%", 0.0);
        }
    }


    public void quantityDiscount(){

    }

}