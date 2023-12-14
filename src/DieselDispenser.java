public class DieselDispenser extends FuelDispenser{
    public DieselDispenser(int id, boolean isOccupied) {
        super(id, isOccupied);
    }

    public boolean isPumpOccupied() {
        return fuelBalance > 0.0;
    }

    public double approveFuel(double rate, double pay, FuelType fuelType) {
        try {
            double fuelApproved = rate / fuelType.price;

            if (fuelApproved <= 0.0) {
                throw new ArithmeticException("Invalid Diesel approval calculation. Check fuel rate and type.");
            }

            isOccupied = true;
            System.out.println("Approved Diesel  in Litres: " + fuelApproved);
            double balance = pay - rate;
            System.out.println("Balance Amount: " + balance);
            return fuelApproved;
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0;
        }
    }

    public void dispenseFuel(double approvedFuel, double useFuel) {
        System.out.println("Dispensing Diesel in Litres: " + useFuel);
        fuelBalance = approvedFuel - useFuel;
        System.out.println("Diesel Balance in Litres: " + fuelBalance);
    }

    public double refundAmount(FuelType fuelType) {
        isOccupied = false;
        try {
            if (fuelBalance >= 0.0) {
                double fuelBalanceAmount = fuelType.price / fuelBalance;

                if (Double.isInfinite(fuelBalanceAmount)) {
                    throw new ArithmeticException("Invalid Diesel balance calculation. Check fuel balance and type.");
                }

                System.out.println("Refund Amount: " + fuelBalanceAmount);
                fuelBalance = 0;
                return fuelBalanceAmount;
            }
            return 0;
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0;
        }
    }

}
