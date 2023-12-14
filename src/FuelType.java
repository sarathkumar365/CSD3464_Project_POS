public class FuelType {

    double price;
    enum Grade{
        regular,vpower
    }
    Grade grade;
   public FuelType(double price, Grade grade ) {

       this.price = price;
       this.grade=grade;
   }



}
