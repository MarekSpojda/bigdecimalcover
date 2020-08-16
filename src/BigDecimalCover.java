import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class BigDecimalCover {
    public static final String PI =
            "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196442881097566593344612847564823378678316527120190914564856692346034861045432664821339360726024914127372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160943305727036575959195309218611738193261179310511854807446237996274956735188575272489122793818301194912983367336244065664308602139494639522473719070217986094370277053921717629317675238467481846766940513200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892354201995611212902196086403441815981362977477130996051870721134999999837297804995105973173281609631859502445945534690830264252230825334468503526193118817101000313783875288658753320838142061717766914730359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019893809525720106548586327886593615338182796823030195203530185296899577362259941389124972177528347913151557485724245415069595082953311686172785588907509838175463746493931925506040092770167113900984882401285836160356370766010471018194295559619894676783744944825537977472684710404753464620804668425906949129331367702898915210475216205696602405803815019351125338243003558764024749647326391419927260426992279678235478163600934172164121992458631503028618297455570674983850549458858692699569092721079750930295532116534498720275596023648066549911988183479775356636980742654252786255181841757467289097777279380008164706001614524919217321721477235014144197356854816136115735255213347574184946843852332390739414333454776241686251898356948556209921922218427255025425688767179049460165346680498862723279178608578438382796797668145410095388378636095068006422512520511739298489608412848862694560424196528502221066118630674427862203919494504712371378696095636437191728746776465757396241389086583264599581339047802759009";
    private int precision;

    public BigDecimalCover(int precision) {
        this.precision = precision;
    }

    public String absolute(String number) {
        return new BigDecimal(number).abs(new MathContext(precision))
                .setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String add(String numberA, String numberB) {
        return new BigDecimal(numberA).add(new BigDecimal(numberB))
                .setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String arcCos(String cosine) {
        String piBy2 = divide(BigDecimalCover.PI, "2");
        return subtract(piBy2, arcSin(cosineToSine(cosine)));
    }

    public String arcCtg(String cotangent) {
        String piBy2 = divide(BigDecimalCover.PI, "2");
        return subtract(piBy2, arcTg(cotangent));
    }

    public String arcSin(String sine) {
        BigDecimal bigDecimalSine = new BigDecimal(sine);
        BigDecimal bigDecimal1 = new BigDecimal("1");
        BigDecimal bigDecimalMinus1 = new BigDecimal("-1");
        if (bigDecimalSine.compareTo(bigDecimal1) == 0) {
            return divide(PI, "2");
        } else if (bigDecimalSine.compareTo(bigDecimalMinus1) == 0) {
            return "-" + divide(PI, "2");
        } else if (bigDecimalSine.compareTo(bigDecimalMinus1) < 0 ||
                bigDecimalSine.compareTo(bigDecimal1) > 0) {
            throw new ArithmeticException("Trying to calculate arcSin or arcCos from |x| > 1");
        }

        return arcTg(sineToTangent(sine));
    }

    public String arcTg(String tangent) {
        BigDecimal bigX = new BigDecimal(tangent);
        String piBy4 = divide(BigDecimalCover.PI, "4");

        int inputComparedTo1 = bigX.compareTo(new BigDecimal("1"));
        int inputComparedToMinus1 = bigX.compareTo(new BigDecimal("-1"));
        if (inputComparedTo1 == 0) {
            return piBy4;
        } else if (inputComparedToMinus1 == 0) {
            return "-" + piBy4;
        }

        String result;
        if (inputComparedTo1 < 0 && inputComparedToMinus1 > 0) {
            result = "0";
            for (int i = 0; i < (this.precision + 10); i++) {
                int factor = 2 * i + 1;
                String numerator = power("-1", i);
                String denominator = "" + factor;
                String fraction = divide(numerator, denominator);
                String xPowered = power(tangent, factor);
                result = add(result, multiply(fraction, xPowered));
            }
            return result;
        } else {
            String modifier = "1";
            if (inputComparedToMinus1 < 0) {
                modifier = "-1";
            }
            result = multiply(modifier, divide(BigDecimalCover.PI, "2"));
            for (int i = 0; i < (this.precision + 10); i++) {
                String numerator = power("-1", i + 1);
                int factor = 2 * i + 1;
                String xPowered = power(tangent, factor);
                String denominator = multiply("" + factor, xPowered);
                String fraction = divide(numerator, denominator);
                result = add(result, fraction);
            }
        }

        return result;
    }

    public String cosineToCotangent(String cosine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cosine2 = power(cosine, 2);
        String sine2 = subtract("1", cosine2);
        String sine = squareRootOf(sine2);
        String result = divide(cosine, sine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String cosineToSine(String cosine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cosine2 = power(cosine, 2);
        String sine2 = subtract("1", cosine2);
        String sine = squareRootOf(sine2);
        this.precision = tempPrecision;

        return new BigDecimal(sine).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String cosineToTangent(String cosine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cosine2 = power(cosine, 2);
        String sine2 = subtract("1", cosine2);
        String sine = squareRootOf(sine2);
        String result = divide(sine, cosine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String divide(String numberA, String numberB) {
        return new BigDecimal(numberA)
                .divide(new BigDecimal(numberB), precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String factorial(String number) {
        if (number.contains(".")) {
            throw new ArithmeticException("Trying to calculate factorial from decimal number");
        } else if (new BigDecimal(number).signum() == -1) {
            throw new ArithmeticException("Trying to calculate factorial from negative number");
        }

        BigDecimal result = new BigDecimal("1");
        BigDecimal assistant = new BigDecimal("1");
        while (!assistant.toString().equals(number)) {
            assistant = assistant.add(new BigDecimal("1"));
            result = result.multiply(assistant);
        }
        return result.toPlainString();
    }

    public int getPrecision() {
        return precision;
    }

    public String multiply(String numberA, String numberB) {
        return new BigDecimal(numberA).multiply(new BigDecimal(numberB))
                .setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String power(String number, int power) {
        if (power == 0) {
            return new BigDecimal("1").setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
        } else if (power < 0) {
            power = power * -1;
            BigDecimal temporaryPowered = new BigDecimal(number).pow(power)
                    .setScale(precision + 10, RoundingMode.HALF_EVEN);
            return new BigDecimal("1")
                    .divide(temporaryPowered, precision, RoundingMode.HALF_EVEN).toPlainString();
        }
        return new BigDecimal(number).pow(power).setScale(precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String rootOfDegree(String number, int rootDegree) {
        BigDecimal numberAsBigDecimal = new BigDecimal(number);
        boolean isInputNegative = numberAsBigDecimal.signum() == -1;
        boolean isRootDegreeNegative = rootDegree < 0;
        if (isInputNegative) {
            numberAsBigDecimal = numberAsBigDecimal.abs();
        }
        if (isRootDegreeNegative) {
            rootDegree = rootDegree * -1;
        }

        BigDecimal result = numberAsBigDecimal;
        while (result.pow(rootDegree).compareTo(numberAsBigDecimal) > 0) {
            result = result.divide(new BigDecimal("2"), precision + 10, RoundingMode.HALF_EVEN);
        }

        BigDecimal lowerBound = result,
                upperBound = result.multiply(new BigDecimal("2")),
                center, leftCenter, rightCenter;
        while (upperBound.subtract(lowerBound).compareTo(new BigDecimal("1")) > 0) {
            center = upperBound.add(lowerBound)
                    .divide(new BigDecimal("2"), precision + 10, RoundingMode.HALF_EVEN);
            leftCenter = lowerBound.add(center)
                    .divide(new BigDecimal("2"), precision + 10, RoundingMode.HALF_EVEN);
            rightCenter = upperBound.add(center)
                    .divide(new BigDecimal("2"), precision + 10, RoundingMode.HALF_EVEN);
            if (leftCenter.pow(rootDegree).subtract(numberAsBigDecimal).abs()
                    .compareTo(rightCenter.pow(rootDegree).subtract(numberAsBigDecimal).abs()) <
                    1) {
                upperBound = center;
            } else {
                lowerBound = center;
            }
        }
        result = lowerBound.subtract(lowerBound.remainder(new BigDecimal("1")));

        BigDecimal modifier;
        int modifierFiller = 0;
        BigDecimal delta = new BigDecimal("0." + "0".repeat(precision + 9) + "1");

        while (true) {
            modifier = new BigDecimal("0." + "0".repeat(modifierFiller) + "1");
            while (result.add(modifier).pow(rootDegree).compareTo(numberAsBigDecimal) < 1) {
                result = result.add(modifier);
            }
            if (result.pow(rootDegree).compareTo(numberAsBigDecimal) == 0 ||
                    result.pow(rootDegree).subtract(result.add(modifier).pow(rootDegree)).abs()
                            .compareTo(delta)
                            < 1) {
                break;
            }
            modifierFiller++;
        }

        if (isRootDegreeNegative) {
            return new BigDecimal("1").divide(result, precision, RoundingMode.HALF_EVEN)
                    .toPlainString();
        }
        return result.setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String sineToCosine(String sine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String sine2 = power(sine, 2);
        String cosine2 = subtract("1", sine2);
        String cosine = squareRootOf(cosine2);
        this.precision = tempPrecision;

        return new BigDecimal(cosine).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String sineToCotangent(String sine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String sine2 = power(sine, 2);
        String cosine2 = subtract("1", sine2);
        String cosine = squareRootOf(cosine2);
        String result = divide(cosine, sine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String sineToTangent(String sine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String sine2 = power(sine, 2);
        String cosine2 = subtract("1", sine2);
        String cosine = squareRootOf(cosine2);
        String result = divide(sine, cosine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    public String squareRootOf(String number) {
        return new BigDecimal(number).sqrt(new MathContext(precision))
                .setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String subtract(String numberA, String numberB) {
        return new BigDecimal(numberA).subtract(new BigDecimal(numberB))
                .setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    public String roundNumberToGivenPrecision(String number, int precision) {
        return new BigDecimal(number).setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
    }
}