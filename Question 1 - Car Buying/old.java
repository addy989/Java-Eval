import java.util.*;
import java.util.Scanner;
public class old {

    static  Map<String,Map<String,List<String>>> carPrices = new HashMap<>();
    static  Map<String,Map<String,String>> accessoriesPrices = new HashMap<>();

    static void insertdata(){
        carPrices.put("Alto K10",Map.of("variants",List.of("STD", "LXI", "VXI", "VXI+"), "price_range", List.of("3.99", "4.25", "4.75", "5.00", "5.96")));
        carPrices.put("Wagon R",Map.of("variants",List.of("LXI", "VXI", "ZXI", "ZXI+"),"price_range",List.of("5.54", "6.25", "6.75", "7.00", "7.38")));
        carPrices.put("Celerio", Map.of("variants", List.of("LXI", "VXI", "ZXI", "ZXI+"), "price_range", List.of("4.99", "6.00", "6.75", "7.04")));
        carPrices.put("Fronx",Map.of("variants",List.of("Sigma", "Delta", "Zeta", "Alpha", "Alpha Dual Tone"), "price_range", List.of("7.51", "10", "11", "12", "13.04")));
        carPrices.put("jimmy",Map.of("variants",List.of("Zeta", "Alpha"),"price_range",List.of("12.74", "14.95")));

        accessoriesPrices.put("Exterior Accessories", Map.of("Alloy Wheels", "₹15,000 - ₹40,000", "Body Side Moulding", "₹1,500 - ₹3,000", "Door Visors", "₹1,000 - ₹2,500", "Mud Flaps", "₹500 - ₹1,200", "Spoilers", "₹3,000 - ₹8,000", "Chrome Garnish", "₹1,000 - ₹5,000", "Roof Rails", "₹2,500 - ₹6,000", "Side Skirts", "₹4,000 - ₹10,000"));
        accessoriesPrices.put("Interior Accessories", Map.of("Seat Covers", "₹2,000 - ₹10,000", "Floor Mats", "₹1,000 - ₹3,000", "Steering Wheel Covers", "₹500 - ₹1,500", "Sunshades", "₹500 - ₹2,000", "Dashboard Kits", "₹2,000 - ₹5,000", "Ambient Lighting", "₹2,000 - ₹6,000", "Car Perfumes", "₹200 - ₹1,000"));
        accessoriesPrices.put("Infotainment and Electronics", Map.of("Touchscreen Infotainment Systems", "₹10,000 - ₹50,000", "Speakers and Amplifiers", "₹5,000 - ₹20,000", "Reverse Parking Sensors", "₹2,000 - ₹5,000", "Rear View Cameras", "₹3,000 - ₹7,000", "GPS Navigation Systems", "₹5,000 - ₹15,000", "Car Chargers", "₹300 - ₹1000"));
        accessoriesPrices.put("Safety and Security", Map.of("Car Alarms", "₹2,000 - ₹5,000", "Central Locking Systems", "₹3,000 - ₹8,000", "Fire Extinguishers", "₹500 - ₹2,000", "First Aid Kits", "₹300 - ₹1,000", "Child Safety Seats", "₹5,000 - ₹20,000"));
        accessoriesPrices.put("Car Care", Map.of("Car Covers", "₹1,000 - ₹5,000", "Cleaning Kits", "₹500 - ₹2,000", "Polish and Wax", "₹300 - ₹1,500", "Pressure Washers", "₹5,000 - ₹15,000"));
    }

    static List<String> getavailableCars(double maxbudget){
        List<String> availablecar = new ArrayList<>();
        for(Map.Entry<String,Map<String,List<String>>> entry : carPrices.entrySet()){
            List<String> pricerange = entry.getValue().get("price_range");
            double maxPrice = Double.parseDouble(pricerange.get(pricerange.size() - 1)); //convert string to double of the last element of list
            if(maxPrice<maxbudget){
                availablecar.add(entry.getKey());
            }
        }
        return availablecar;
    }

    static void selectaccessories(double availbudget,List<String> selectedaccessories ){
        Scanner scan = new Scanner(System.in);
        for(Map.Entry<String,Map<String,String>> category : accessoriesPrices.entrySet()){
            System.out.println("Select accessories from " + category.getKey() + ": ");
            for (Map.Entry<String, String> accessory : category.getValue().entrySet()) {
                System.out.println(accessory.getKey() + " - " + accessory.getValue());
            }
        }
        System.out.println("Enter the name of accessory you want to add in your selected car(or type done to skip adding accesories)");
        while(true){
            String selectedaccesory = scan.nextLine();
            if (selectedaccesory.equals("done")) {
                break;
            }
            else{
                selectedaccessories.add(selectedaccesory);
                System.out.println("Added "+ selectedaccesory +" to your accessory for your car ");
                System.out.println(selectedaccessories);
            }
        }
    }
    // Calculate the total accessories cost
    static double calculateaccessoriescost(List<String> selectedAccessories) {
        double totalCost = 0;
        for (String accessory : selectedAccessories) {
            // Gets range for each accessories
            String priceRange = getPriceRangeForAccessory(accessory);
            totalCost += getAccessoryCost(priceRange);
        }
        return totalCost;
    }

    static String getPriceRangeForAccessory(String accessory) {
        for (Map<String, String> c : accessoriesPrices.values()) { //iterating in accessories price map<string,map<string,string> value ie Map<string,string>
            if (c.containsKey(accessory)) {
                return c.get(accessory);
            }
        }
        return "₹0-₹0"; // Default to no price if not found
    }

    // Extract a reasonable cost from the price range
    static double getAccessoryCost(String priceRange) {
        String[] prices = priceRange.split("-");
        prices[0] = prices[0].replace(",","");
        prices[0] = prices[0].replace("₹","");
        prices[1] = prices[1].replace(",","");
        prices[1] = prices[1].replace("₹","");
        double minPrice = Double.parseDouble(prices[0]);
        double maxPrice = Double.parseDouble(prices[1]);
        return (minPrice + maxPrice) / 2; // Average cost
    }

    public static void main(String[] args) {
        Scanner readinput = new Scanner(System.in);
        System.out.println("Enter Your Maximum Budget(in lakhs ef 6.00 or 4.56 etc");
        double data = readinput.nextDouble();
        System.out.println("You entered "+data+" as your maximum budget");

        insertdata();
        List<String> shortlistedCars = getavailableCars(data);
        if(shortlistedCars.isEmpty()){
            System.out.println("Sorry, NO CAR IS AVAIALBLE IN YOUR BUDGET");
            return;
        }

        System.out.println("These all are the cars that are available in your "+data+" budget");
        for(int i=0;i<shortlistedCars.size();i++){
            System.out.println((i+1)+". "+shortlistedCars.get(i));
        }

        System.out.println("Select a car by number from above list provided: ");
        int choice = readinput.nextInt();
        choice = choice - 1;
        String selectedcar = shortlistedCars.get(choice); //picked the car now next step is to display its variant

        System.out.println("Variant available for "+ selectedcar+ ": ");
        Map<String, List<String>> carData = carPrices.get(selectedcar);
        List<String> variants = carData.get("variants");
        List<String> priceRange = carData.get("price_range");

        for (int i = 0; i < variants.size(); i++) {
            System.out.println((i + 1) + ". " + variants.get(i) + " - Price: ₹" + priceRange.get(i));
        }

        System.out.println("Select a variant by number: ");
        int variantChoice = readinput.nextInt();
        variantChoice = variantChoice-1;
        double variantPrice = Double.parseDouble(priceRange.get(variantChoice)); //taking variant price

        //now show avaialble accessories
        System.out.println("available accesories within your budget ");
        double availbudgetforaccessories = data - variantPrice ;

        List<String> selectedAccessories = new ArrayList<>();
        selectaccessories(availbudgetforaccessories,selectedAccessories);

        double totalAccessoriesCost = calculateaccessoriescost(selectedAccessories);
        double carPrice = variantPrice * 100000; // Convert to Rupees
        double totalPriceBeforeFees = carPrice + totalAccessoriesCost;
        System.out.println("total price before additional fees : - ₹"+totalPriceBeforeFees);

        double registrationFee = totalPriceBeforeFees * 0.07;
        double insuranceFee = totalPriceBeforeFees * 0.12;
        double handlingFee = totalPriceBeforeFees * 0.02;

        double finalOnRoadPrice = totalPriceBeforeFees + registrationFee + insuranceFee + handlingFee;

        // Display the final result
        System.out.println("Final On-Road Price after addition of other additional fees and accessories charges are : ₹" + finalOnRoadPrice);
        if(finalOnRoadPrice>data*100000){
            System.out.println("please try another combination of accessory or variant of car as it exceeds your budget");
        }

    }
}