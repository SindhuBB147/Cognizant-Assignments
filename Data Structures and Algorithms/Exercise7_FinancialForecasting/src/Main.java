public class Main {

    public static void main(String[] args) {

        double presentValue = 10000;
        double growthRate = 0.10;
        int years = 5;

        double result = FinancialForecast.futureValue(presentValue, growthRate, years);

        System.out.printf("Future Value after %d years = %.2f%n", years, result);
    }
}