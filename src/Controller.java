import java.util.*;

public class Controller {
    Scanner scanner;
    FuelInventory regularpetrolInventory;
    FuelInventory regulardieselInventory;
    FuelInventory vpowerpetrolInventory;
    FuelInventory vpowerdieselInventory;
    FuelType fuelType;
    List<FuelDispenser> fuelDispenser;



    public Controller() {

        scanner = new Scanner(System.in);
        regularpetrolInventory = new FuelInventory(20211, FuelInventory.InventoryType.PETROL, FuelType.Grade.regular);
        regulardieselInventory = new FuelInventory(20212, FuelInventory.InventoryType.DIESEL, FuelType.Grade.regular);
        vpowerpetrolInventory = new FuelInventory(20213, FuelInventory.InventoryType.PETROL, FuelType.Grade.vpower);
        vpowerdieselInventory = new FuelInventory(20214, FuelInventory.InventoryType.DIESEL, FuelType.Grade.vpower);
        fuelDispenser=new ArrayList<>() ;
        fuelDispenser.add(new PetrolDispenser(1,false));
        fuelDispenser.add(new PetrolDispenser(2,false));
        fuelDispenser.add(new PetrolDispenser(3,false));
        fuelDispenser.add(new PetrolDispenser(4,false));
        fuelDispenser.add(new DieselDispenser(5,false));
        fuelDispenser.add(new DieselDispenser(6,false));

    }

    public void fuelDelivery() {

        System.out.println("Fuel Delivery at the Gas Station");
        int choice;
        do {
            // Display the main menu to the user.
            System.out.println("------Adding Fuel to inventory in litres  -------");
            System.out.println("1. Regular Petrol");
            System.out.println("2. Regular Diesel");
            System.out.println("3. vpower Petrol");
            System.out.println("4. vpower Diesel");
            System.out.println("5. Exit");
            choice = Integer.parseInt(scanner.next());
            switch (choice) {
                case 1: {
                    System.out.println("Please enter the petrol to be added to Regular Petrol Fuel Inventory ");
                    regularpetrolInventory.addFuelToInventory(scanner.nextDouble());
                    regularpetrolInventory.getFuelInventoryDetails("regular petrol");
                    break;
                }
                case 2: {
                    System.out.println("Please enter the diesel to be added to Regular Diesel Fuel Inventory ");
                    regulardieselInventory.addFuelToInventory(scanner.nextDouble());
                    regulardieselInventory.getFuelInventoryDetails("Regular Diesel ");
                    break;
                }

                case 3: {
                    System.out.println("Please enter the petrol to be added to Vpower Petrol Fuel Inventory ");
                    vpowerpetrolInventory.addFuelToInventory(scanner.nextDouble());
                    vpowerpetrolInventory.getFuelInventoryDetails("Vpower petrol");
                    break;
                }
                case 4: {
                    System.out.println("Please enter the diesel to be added to Vpower Diesel Fuel Inventory ");
                    vpowerdieselInventory.addFuelToInventory(scanner.nextDouble());
                    vpowerdieselInventory.getFuelInventoryDetails("Vpower Diesel ");
                    break;
                }
                case 5:
                    System.out.println("Exiting the Fuel Delivery");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");



            }
        }while(choice != 5);



    }
    public String selectGrade(String type){

        int fuelGradeUserChoice;
        String fuelGradeChoice="NAN";
        // Display the main menu to the user.
        System.out.println("------Select the Grade-------");
        System.out.println("1. Regular");
        System.out.println("2. Vpower");
        System.out.println("3. Exit");
        fuelGradeUserChoice =Integer.parseInt(scanner.next());
        switch (fuelGradeUserChoice) {
            case 1:

                if (type.equalsIgnoreCase("petrol")) {
                    fuelGradeChoice = "regular";
                    fuelType = new Petrol(Constants.petrolRegularPrice, FuelType.Grade.regular);
                }
                else if (type.equalsIgnoreCase("diesel")){
                    fuelGradeChoice = "regular";

                    fuelType = new Diesel(Constants.dieselRegularPrice, FuelType.Grade.regular);
                }
                break;
            case 2:

                if (type.equalsIgnoreCase("diesel")) {
                    fuelGradeChoice = "vpower";
                    fuelType = new Petrol(Constants.petrolvpowerPrice, FuelType.Grade.vpower);
                }
                else{
                    fuelGradeChoice = "vpower";
                    fuelType = new Diesel(Constants.dieselvpowerPrice, FuelType.Grade.vpower);
                }
                break;

            case 3 :
                System.out.println("Exiting the Fuel Approval option");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");

        }
        return fuelGradeChoice.toLowerCase();
    }

    public void approveFuel(String fuelGrade) {
        int pumpno;

        List<Integer> pumps = showPumpStatus();
        for (Integer pump : pumps) {
            System.out.println("Pump " + pump);
        }
        System.out.println("------Select the pump-------");

        try {
            pumpno = Integer.parseInt(scanner.next());

            if (pumpno < 0 ) {
                throw new NoSuchElementException("Invalid pump number");
            }

            if (!fuelDispenser.get(pumpno-1).isPumpOccupied()) {
                System.out.println("Enter the amount(currency) to fill up ");
                double rate = scanner.nextDouble();
                System.out.println("Enter the amount(currency) paid by user ");
                double paidAmount = scanner.nextDouble();
                if(paidAmount>=rate) {
                    double approvedFuel = fuelDispenser.get(pumpno - 1).approveFuel(rate, paidAmount, fuelType);
                    System.out.println("Enter the amount of fuel used in litres");
                    double useFuel = scanner.nextDouble();
                    fuelDispenser.get(pumpno - 1).dispenseFuel(approvedFuel, useFuel);

                    switch (fuelGrade) {
                        case "regularpetrol":
                            regularpetrolInventory.updateDispensedQuantity(useFuel);
                            break;
                        case "vpowerpetrol":
                            vpowerpetrolInventory.updateDispensedQuantity(useFuel);
                            break;
                        case "regulardiesel":
                            regulardieselInventory.updateDispensedQuantity(useFuel);
                            break;
                        case "vpowerdiesel":
                            vpowerdieselInventory.updateDispensedQuantity(useFuel);
                            break;
                    }

                    TranscationLogger.dologFuelTransaction(approvedFuel, useFuel);
                }
                else {
                    System.out.println("Amount insufficient ");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (NoSuchElementException e) {
            System.out.println("Invalid pump number. Please select a valid pump.");
        } catch (NullPointerException e) {
            System.out.println("Fuel dispenser not available for the selected pump.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }
    }


    public ArrayList<Integer> showPumpStatus(){
        ArrayList<Integer> pumpNumber=new ArrayList<>();
        for(int i=1;i<=fuelDispenser.size();i++){
            if(!fuelDispenser.get(i-1).isPumpOccupied())
            {
                pumpNumber.add(i) ;
            }
        }

        return pumpNumber;
    }
    public void refundMoney() {
        ArrayList<Integer> pumpNumber = new ArrayList<>();
        for (int i = 1; i <= fuelDispenser.size(); i++) {
            if (fuelDispenser.get(i - 1).isPumpOccupied()) {
                pumpNumber.add(i);
                System.out.println("Pump " + i);
            }
        }
        try {
            if (pumpNumber.size() > 0) {
                System.out.println("------Select the pump for Refund-------");

                int pumpno = Integer.parseInt(scanner.next());

                if (pumpno < 0) {
                    throw new NoSuchElementException("Invalid pump number");
                }

                double refundMoney = fuelDispenser.get(pumpno - 1).refundAmount(fuelType);
                System.out.println("Refund Issued (currency) " + refundMoney);
            }

            else {
                System.out.println("All pumps are available.No refund needed");
            }
        } catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid number for pump selection.");
        } catch(NoSuchElementException e){
            System.out.println("Invalid pump number. Please select a pump that is currently occupied.");
        } catch(NullPointerException e){
            System.out.println("Fuel dispenser not available for the selected pump.");
        } catch(Exception e){
            System.out.println("An unexpected error occurred.");
        }

    }

}






