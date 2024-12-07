import org.jetbrains.annotations.NotNull;
import java.util.*;

public class CarDataHandler {
    static Map<String, Map<String, List<String>>> carprices = new HashMap<>();
    static Map<String, Map<String, String>> accessoriesprices = new HashMap<>();


    public static void insertData(){
        carprices.put("Alto K10", Map.of("variants", List.of("STD", "LXI", "VXI", "VXI+"), "price_range", List.of("3.99", "4.25", "4.75", "5.00", "5.96")));
        carprices.put("Wagon R", Map.of("variants", List.of("LXI", "VXI", "ZXI", "ZXI+"), "price_range", List.of("5.54", "6.25", "6.75", "7.00", "7.38")));
        carprices.put("Celerio", Map.of("variants", List.of("LXI", "VXI", "ZXI", "ZXI+"), "price_range", List.of("4.99", "6.00", "6.75", "7.04")));
        carprices.put("Fronx", Map.of("variants", List.of("Sigma", "Delta", "Zeta", "Alpha", "Alpha Dual Tone"), "price_range", List.of("7.51", "10", "11", "12", "13.04")));
        carprices.put("jimmy", Map.of("variants", List.of("Zeta", "Alpha"), "price_range", List.of("12.74", "14.95")));

        accessoriesprices.put("Exterior Accessories", Map.of("Alloy Wheels", "₹15,000 - ₹40,000", "Body Side Moulding", "₹1,500 - ₹3,000", "Door Visors", "₹1,000 - ₹2,500", "Mud Flaps", "₹500 - ₹1,200", "Spoilers", "₹3,000 - ₹8,000", "Chrome Garnish", "₹1,000 - ₹5,000", "Roof Rails", "₹2,500 - ₹6,000", "Side Skirts", "₹4,000 - ₹10,000"));
        accessoriesprices.put("Interior Accessories", Map.of("Seat Covers", "₹2,000 - ₹10,000", "Floor Mats", "₹1,000 - ₹3,000", "Steering Wheel Covers", "₹500 - ₹1,500", "Sunshades", "₹500 - ₹2,000", "Dashboard Kits", "₹2,000 - ₹5,000", "Ambient Lighting", "₹2,000 - ₹6,000", "Car Perfumes", "₹200 - ₹1,000"));
        accessoriesprices.put("Infotainment and Electronics", Map.of("Touchscreen Infotainment Systems", "₹10,000 - ₹50,000", "Speakers and Amplifiers", "₹5,000 - ₹20,000", "Reverse Parking Sensors", "₹2,000 - ₹5,000", "Rear View Cameras", "₹3,000 - ₹7,000", "GPS Navigation Systems", "₹5,000 - ₹15,000", "Car Chargers", "₹300 - ₹1000"));
        accessoriesprices.put("Safety and Security", Map.of("Car Alarms", "₹2,000 - ₹5,000", "Central Locking Systems", "₹3,000 - ₹8,000", "Fire Extinguishers", "₹500 - ₹2,000", "First Aid Kits", "₹300 - ₹1,000", "Child Safety Seats", "₹5,000 - ₹20,000"));
        accessoriesprices.put("Car Care", Map.of("Car Covers", "₹1,000 - ₹5,000", "Cleaning Kits", "₹500 - ₹2,000", "Polish and Wax", "₹300 - ₹1,500", "Pressure Washers", "₹5,000 - ₹15,000"));
    }

    @NotNull
    //lst of cars avaialble ee
    public static List<String> getAvailableCars(double maxbudget){
        List<String> availablecars = new ArrayList<>();
        for (Map.Entry<String, Map<String, List<String>>> entry : carprices.entrySet()) {
            List<String> priceRange = entry.getValue().get("price_range");
            double minprice = Double.parseDouble(priceRange.get(0));
            //double maxprice = Double.parseDouble(priceRange.get(priceRange.size() - 1)); //lst price of car
            if (   minprice< maxbudget) { //maxprice < maxbudget
                availablecars.add(entry.getKey());
            }
        }
        return availablecars;
    }

    // Calculate the total cost of selected accessories
    public static double calculateAccessoriesCost(List<String> selectedAccessories) {
        double totalCost = 0;
        for (String accessory : selectedAccessories) {
            String priceRange = getPriceRangeForAccessory(accessory);
            totalCost += getAccessoryCost(priceRange);
        }
        System.out.println("accessories cost : "+totalCost);
        return totalCost;
    }


    public static String getPriceRangeForAccessory(String accessory) {
        for (Map<String, String> category : accessoriesprices.values()) {
            if (category.containsKey(accessory)) {
                return category.get(accessory); //price range string
            }
        }
        return "₹0-₹0"; // Return default value if not found
    }


    public static double getAccessoryCost(String priceRange) {
        String[] prices = priceRange.split("-");
        prices[0] = prices[0].replace(",", "").replace("₹", "");
        prices[1] = prices[1].replace(",", "").replace("₹", "");
        double minPrice = Double.parseDouble(prices[0]);
        double maxPrice = Double.parseDouble(prices[1]);
        return (minPrice + maxPrice) / 2;
    }

    //accessory selec
    public static void selectAccessories(double availBudget, List<String> selectedAccessories) {
        Scanner scan = new Scanner(System.in);
        for (Map.Entry<String, Map<String, String>> category : accessoriesprices.entrySet()) {
            System.out.println("Select accessories from " + category.getKey() + ": ");
            for (Map.Entry<String, String> accessory : category.getValue().entrySet()) {
                System.out.println(accessory.getKey() + "- " + accessory.getValue());
            }
        }

        System.out.println("Enter the name of accessory you want to add (or type 'done' to skip):");
        while (true) {
            try {
                String selectedAccessory = scan.nextLine();
                if (selectedAccessory.equals("done")) {
                    break;
                } else {
                    // Add selected accessory to the list
                    selectedAccessories.add(selectedAccessory);
                    System.out.println("Added " + selectedAccessory + " to your accessories.");
                    System.out.println("Your selected accessories: " + selectedAccessories);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while adding the accessory. Please try again.");
            }
        }

    }
}
