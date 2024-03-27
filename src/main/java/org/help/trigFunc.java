import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class trigFunc {
    public double recordResult(double x, double eps, Writer out) {
        double result = cos(x, eps);
        try (FileWriter fileWriter = new FileWriter("output_trig.csv")) {
            fileWriter.append(String.valueOf(x)).append(",").append(String.valueOf(result)).append("\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
        return result;
    }

    public static double sin(double x, double eps) {
        if (Double.POSITIVE_INFINITY == x || Double.NEGATIVE_INFINITY == x) {
            return Double.NaN;
        }
        double fact = 1, result_l = 1, result = 0, xx, pow;
        int sign = 1, cnt = 1;

        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < Math.PI * 2) {
                x += Math.PI * 2;
            }
        }
        xx = x * x;
        pow = x;

        while (Math.abs(result_l - result) > eps) {
            fact /= cnt;
            result_l = result;
            result += sign * pow * fact;
            sign = -sign;
            fact /= (cnt + 1);
            pow *= xx;
            cnt += 2;
        }
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) < eps) return 0;
        return result;
    }

    public static double cos(double x, double eps) {
        double x_init = x;
        x %= Math.PI * 2;
        if (Double.POSITIVE_INFINITY == x || Double.NEGATIVE_INFINITY == x) {
            return Double.NaN;
        }
        if (x < -Math.PI) {
            while (x < -Math.PI) x += 2 * Math.PI;
        }
        if (x > Math.PI) {
            while (x > Math.PI) x -= 2 * Math.PI;
        }
        double result;
        if (x > Math.PI / 2 || x < -Math.PI / 2) {
            result = -1 * Math.sqrt(1 - sin(x_init, eps) * sin(x_init, eps));
        } else result = Math.sqrt(1 - sin(x_init, eps) * sin(x_init, eps));
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) <= eps) return 0;
        return result;
    }

    public static double sec(double x, double eps) {
        double cosVal = cos(x, eps);
        if (Double.isNaN(cosVal) || cosVal == 0) return Double.NaN;
        return 1 / cosVal;
    }

    public static double csc(double x, double eps) {
        double cosVal = sin(x, eps);
        if (Double.isNaN(cosVal) || cosVal == 0) return Double.NaN;
        return 1 / cosVal;
    }

    public static double tan(double x, double eps) {
        double sinVal = sin(x, eps);
        double cosVal = sin(x, eps);
        if (Double.isNaN(sinVal) || Double.isNaN(cosVal) || cosVal == 0) return Double.NaN;
        return  sinVal / cosVal;
    }

    public static double cot(double x, double eps) {
        double sinVal = sin(x, eps);
        double cosVal = sin(x, eps);
        if (Double.isNaN(sinVal) || Double.isNaN(cosVal) || sinVal == 0) return Double.NaN;
        return cosVal/sinVal;
    }


}
