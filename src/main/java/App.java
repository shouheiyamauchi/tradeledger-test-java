public class App {
    public static void main(String[] args) {
      FeeCalculator rule1 = new FeeCalculator(28, 0.04, 0.01, "invoiceAmount", 0.02);

      System.out.println(rule1.calcFee(80000, 26));
      System.out.println(rule1.calcFee(80000, 45));

      FeeCalculator rule2 = new FeeCalculator(20, 0.03, 0.01, "discountRate", 0.30);

      System.out.println(rule2.calcFee(80000, 10));
      System.out.println(rule2.calcFee(80000, 35));
    }
}
