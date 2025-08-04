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
    static double shippingFee;

    public static void main(String[] args) {
        //PART 1
        InteractiveOrderProcessor newOrder = new InteractiveOrderProcessor();

        System.out.println("Welcome to the Interactive Order Processor!\n");
        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        unitPrice = sc.nextDouble();
        System.out.print("Enter quantity: ");
        quantity = sc.nextInt();
        System.out.print("Is customer a member (true/false)?: ");
        isMember = sc.nextBoolean();
        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        customerTier = sc.next();
        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown):");
        shippingZone = sc.next();
        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none):");
        discountCode = sc.next();

        System.out.println();
        System.out.println("--- Order Details ---");
        System.out.println("Unit Price: $" + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        System.out.println();
        System.out.println("--- Calculation Steps ---");
        double initialSubtotal = newOrder.initialSubtotal();
        System.out.printf("Initial Subtotal: $%.2f\n", initialSubtotal);
        double afterTierDiscount = newOrder.tierDiscount();
        System.out.printf("After Tier Discount %s: $%.2f\n" ,newOrder.tierDiscountDescription(), afterTierDiscount);
        double afterQuantityDiscount = newOrder.quantityDiscount(afterTierDiscount);
        System.out.printf("After Quantity Discount %s: $%.2f\n",newOrder.quantityDiscountDescription(), afterQuantityDiscount);
        double afterPromotionalDiscount = newOrder.promotionalDiscount(afterQuantityDiscount);
        System.out.printf("After Promotional Code %s: $%.2f\n",newOrder.promotionalDiscountDescription(afterQuantityDiscount), afterPromotionalDiscount);
        double afterSmallOrderSurcharge = newOrder.smallOrderSurcharge(afterPromotionalDiscount);
        System.out.printf("After Small Order Surcharge (if applicable): $%.2f\n", afterSmallOrderSurcharge);

        newOrder.shippingCost();
        System.out.printf("Shipping Cost: $%.2f (%s)\n", shippingFee, shippingZone);

        double finalOrderTotal = newOrder.finalTotal(afterSmallOrderSurcharge,shippingFee);
        System.out.printf("Final Order Total: $%.2f\n", finalOrderTotal);

        sc.close();
    }




    //-----------------------------------------------------------------------------------------------------------

    public double initialSubtotal(){
        return unitPrice * quantity;
    }

    public String tierDiscountDescription(){
        if (customerTier.equalsIgnoreCase("gold")){
            return "(Gold - 15%)";
        } else if (customerTier.equalsIgnoreCase("silver")) {
            return "(Silver - 10%)";
        }else{
            return null;
        }
    }
    public double tierDiscount(){
        if (customerTier.equalsIgnoreCase("gold")){
            return initialSubtotal()*(1-0.15);
        } else if (customerTier.equalsIgnoreCase("silver")) {
            return initialSubtotal()*(1-0.10);
        }else{
            return initialSubtotal();
        }
    }

    public String quantityDiscountDescription(){
        if (quantity >= 5){
            return "(5% for >=5 items)";
        }
        return null;
    }
    public double quantityDiscount(double cumulativeDiscount){
        if (quantity >= 5){
            return cumulativeDiscount*(1 - 0.05);
        }
        return cumulativeDiscount;
    }

    public String promotionalDiscountDescription(double afterQuantityDiscount){
        if (discountCode.equals("SAVE10") && afterQuantityDiscount > 75){
            return "(SAVE10 for >$75)";
        } else if (discountCode.equals("FREESHIP")) {
            shippingFee = 0.0;
            return "(Free Shipping)";
        }else{
            return null;
        }
    }
    public double promotionalDiscount(double afterQuantityDiscount) {
        if (discountCode.equals("SAVE10") && afterQuantityDiscount > 75){
            return afterQuantityDiscount-10;
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            shippingFee = 0.0;
            return afterQuantityDiscount;
        }else{
            return afterQuantityDiscount;
        }
    }

    public double smallOrderSurcharge(double afterPromotionalDiscount) {
        double result = (afterPromotionalDiscount < 25)? afterPromotionalDiscount + 3 : afterPromotionalDiscount;
        return result;
    }

    public void shippingCost(){
        if (discountCode.equals("FREESHIP")){
            shippingFee = 0.0;
        }else{
            switch(shippingZone.toLowerCase()){
                case "zonea":
                    shippingFee = 5;
                    break;
                case "zoneb":
                    shippingFee = 12.50;
                    break;
                case "zonec":
                    shippingFee = 20.00;
                    break;
                default:
                    shippingFee = 25;
                    break;
            }
        }

    }

    public double finalTotal(double cumulativeTotal, double shippingFee){
        return cumulativeTotal + shippingFee;
    }
}