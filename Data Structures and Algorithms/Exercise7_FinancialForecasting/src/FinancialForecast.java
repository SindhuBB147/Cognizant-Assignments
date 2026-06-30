public class FinancialForecast {

    public static double futureValue(double presentValue, double growthRate, int years) {

        if (years == 0) {
            return presentValue;
        }

        return futureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }
}