import java.util.*;


public class CarBuying {
    public static void main(String[] args) {
        Scanner readinput = new Scanner(System.in);
        System.out.println("Enter Your Maximum Budget(inlakhs eg 6.00 etc):");
        double userbudget = readinput.nextDouble();
        System.out.println("You entered " + userbudget + " as your maximum budget");

        //data for cars and its variant and prices range
        CarDataHandler.insertData();
        // avail car
        List<String> shortlistedCars = CarDataHandler.getAvailableCars(userbudget);
        if (shortlistedCars.isEmpty()){
            System.out.println("Sorry, NO CAR IS AVAILABLE IN YOUR BUDGET");
            return;
        }


        System.out.println("These are the cars available within  your budget of ₹" + userbudget + " lakhs:");
        for (int i = 0; i < shortlistedCars.size(); i++) {
            System.out.println((i + 1) + ". " + shortlistedCars.get(i));
        }

        System.out.println("Select a car by number : ");
        int carChoice = readinput.nextInt() - 1;
        String selectedcar = shortlistedCars.get(carChoice);

        //all var of car selected
        System.out.println("Variants available for " + selectedcar + ": ");
        Map<String, List<String>> carData = CarDataHandler.carprices.get(selectedcar);
        List<String> variants = carData.get("variants");
        List<String> pricerange = carData.get("price_range");
        for (int i = 0; i < variants.size(); i++) {
           // System.out.println((i + 1) + ". " + variants.get(i) + " - Price: ₹" + priceRange.get(i));
            if(Double.parseDouble(pricerange.get(i)) < userbudget){
                System.out.println(((i+1) + "." + variants.get(i) +" - price: ₹" + pricerange.get(i)));
            }
        }

        // Select variant
        System.out.println("Select a variant by number: ");
        int variantChoice = readinput.nextInt() - 1;
        double variantPrice = Double.parseDouble(pricerange.get(variantChoice));

        double remainingbudgetforAccessories = userbudget - variantPrice;

        //Collect selected accessories
        List<String> selectedAccessories = new ArrayList<>();
        CarDataHandler.selectAccessories(remainingbudgetforAccessories, selectedAccessories);

        double totalAccessoriesCost = CarDataHandler.calculateAccessoriesCost(selectedAccessories);

        //Calculate total price of the car with accessories
        variantPrice = variantPrice*100000;
        double totalPriceBeforeFees = variantPrice + totalAccessoriesCost;
        System.out.println("Total price of car + accessories : ₹" + totalPriceBeforeFees);


        double registrationFee = totalPriceBeforeFees*0.07;//redisteration
        double insuranceFee = totalPriceBeforeFees*0.12;//insurange
        double handlingFee = totalPriceBeforeFees*0.02;//handling fee

        // Calculate the final on-road price
        double finalonroadprice = totalPriceBeforeFees + registrationFee + insuranceFee + handlingFee;
        System.out.println("Final on-road price (after diff taxess): ₹" + finalonroadprice);

        // Check if the final price exceeds the budget
        if (finalonroadprice > userbudget*100000) {
            System.out.println("Please try another combination of accessories or variants  or car as it exceeds your budget.");
        }
        System.out.println("Thankyou for your purchase !! :) ");
    }
}