public abstract class FuelDispenser {
    int id;
    boolean isOccupied;
    double fuelBalance;

    public FuelDispenser(int id, boolean isOccupied) {
        this.id = id;
        this.isOccupied = isOccupied;
    }

    public abstract boolean isPumpOccupied() ;

    public abstract double approveFuel(double rate, double pay, FuelType fuelType) ;

    public abstract void dispenseFuel(double approvedFuel, double useFuel) ;

    public abstract double refundAmount(FuelType fuelType) ;
}
