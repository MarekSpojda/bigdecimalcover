public class BigDecimalCoverTester {
    public static void main(String[] args) {
        BigDecimalCover bdc = new BigDecimalCover(5);
        System.out.println(bdc.roundNumberToGivenPrecision("-54.215", 20));
        System.out.println(BigDecimalCover.PI);
    }
}