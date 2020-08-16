import java.math.BigDecimal;

public class BigDecimalCoverTester {
    public static void main(String[] args) {
        BigDecimalCover bdc = new BigDecimalCover(50);
        System.out.println(bdc.roundNumberToGivenPrecision("0.000", 5));
    }
}