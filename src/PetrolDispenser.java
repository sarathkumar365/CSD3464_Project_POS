public class PetrolDispenser extends FuelDispenser{
    double fuelBalance;

    public PetrolDispenser(int id, boolean isOccupied) {
        super(id, isOccupied);
    }


    public boolean isPumpOccupied() {
        return fuelBalance > 0.0;
    }

    public double approveFuel(double rate, double pay, FuelType fuelType) {
        try {
            double fuelApproved = rate / fuelType.price;

            if (fuelApproved <= 0.0) {
                throw new ArithmeticException("Invalid Petrol approval calculation. Check fuel rate and type.");
            }

            isOccupied = true;
            System.out.println("Approved Petrol in Litres: " + fuelApproved);
            double balance = pay - rate;
            System.out.println("Balance Amount: " + balance);
            return fuelApproved;
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0; // Return 0.0 or handle accordingly based on your application logic
        }
    }

    public void dispenseFuel(double approvedFuel, double useFuel) {
        System.out.println("Dispensing Petrol in Litres: " + useFuel);
        fuelBalance = approvedFuel - useFuel;
        System.out.println("Fuel Balance in Litres: " + fuelBalance);
    }

    public double refundAmount(FuelType fuelType) {
        isOccupied = false;
        try {
            if (fuelBalance >= 0.0) {
                double fuelBalanceAmount = fuelType.price / fuelBalance;

                if (Double.isInfinite(fuelBalanceAmount)) {
                    throw new ArithmeticException("Invalid Petrol balance calculation. Check fuel balance and type.");
                }

                System.out.println("Refund Amount: " + fuelBalanceAmount);
                fuelBalance = 0;
                return fuelBalanceAmount;
            }
            return 0;
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0; // Return 0.0 or handle accordingly based on your application logic
        }
    }
}
