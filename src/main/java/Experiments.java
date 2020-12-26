public class Experiments {
    public static void main(String[] args) {
        BigDecimalCover bdc = new BigDecimalCover(20);
        String number1 = bdc.cotangentToSine("0.75");
        String number2 = "0.8";
        System.out.println(bdc.absolute(bdc.subtract(number1, number2)));
        System.out.println("N1|" + number1);
        System.out.println("N2|" + number2);
    }
}