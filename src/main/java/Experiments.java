import java.math.BigDecimal;
import java.math.RoundingMode;

public class Experiments {
    public static void main(String[] args) {
        String value = "12.34567";
        BigDecimal bigDecimal = new BigDecimal(value);
        System.out.println(bigDecimal.abs().setScale(5, RoundingMode.HALF_EVEN).toPlainString());
    }
}