import java.io.FileWriter;
import java.io.IOException;

public class LogFunction {

    public static double log(int n, double x, double eps){
        return ln(x, eps) / ln(n, eps);
    }

    public double Log(int n, double x){
        double eps = 0.001;
        double result = log(n, x, eps);
        try (FileWriter fileWriter = new FileWriter("output_ln.csv")) {
            fileWriter.append(String.valueOf(x)).append(",").append(String.valueOf(result)).append("\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
        return result;
    }

    public static double ln(double x, double eps){
        if (Double.isNaN(x) || x < (double) 0) {
            return Double.NaN;
        } else if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }

        double constant = ((x - 1) * (x - 1)) / ((x + 1) * (x + 1));

        double sum = 0;
        double currentValue = (x - 1) / (x + 1);
        int step = 1;
        while (Math.abs(currentValue) > eps / 2) {
            sum += currentValue;
            currentValue = (2 * step - 1) * currentValue * constant / (2 * step + 1);
            step++;
        }
        sum *= 2;
        return sum;
    }

    public double Ln(double x){
        double eps = 0.001;
        double result = ln(x, eps);
        try (FileWriter fileWriter = new FileWriter("output_ln.csv")) {
            fileWriter.append(String.valueOf(x)).append(",").append(String.valueOf(result)).append("\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
        return result;
    }
}
