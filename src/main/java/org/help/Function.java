import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Function {

//    x <= 0 : (((((sec(x) ^ 2) / (sin(x) * cos(x))) - tan(x)) ^ 2) / (csc(x) + (cot(x) * csc(x))))
//    x > 0 : (((((log_10(x) * log_3(x)) + log_3(x)) / (ln(x) + log_10(x))) / log_2(x)) * ((log_5(x) / log_2(x)) - (ln(x) - log_10(x))))
    public double System(double x){
        double eps = 0.001;
        if (x <= 0) return Math.pow(Math.pow(trigFunc.sec(x, eps),2) / (trigFunc.sin(x, eps) * trigFunc.cos(x, eps) -
                trigFunc.tan(x, eps))/ (trigFunc.csc(x, eps) + (trigFunc.cot(x, eps)* trigFunc.csc(x, eps))),2);
        else return (((((LogFunction.log(10, x, eps) * LogFunction.log(3, x, eps)) + LogFunction.log(3, x, eps)) / (LogFunction.ln(x, eps) + LogFunction.log(10, x, eps))) / LogFunction.log(2, x, eps)) * ((LogFunction.log(5, x, eps) / LogFunction.log(2, x, eps)) -(LogFunction.ln(x, eps) - LogFunction.log(10, x, eps))));
    }
    public double startFunction(double x){
        double result = System(x);
        try (FileWriter fileWriter = new FileWriter("output.csv")) {
            // Записываем данные в CSV-формат
            fileWriter.append(String.valueOf(x)).append(",").append(String.valueOf(result)).append("\n");

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
        return result;
    }
}
