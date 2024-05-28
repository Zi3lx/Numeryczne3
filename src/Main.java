import java.io.*;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;

public class Main {
    public static void main(String[] args) {
        /*String fileName = "wyniki.csv"; // Nazwa pliku CSV

        try (PrintStream fileOut = new PrintStream(new FileOutputStream(fileName));
             PrintStream consoleOut = System.out) {

            // Przekierowanie wyjścia standardowego do pliku
            System.setOut(fileOut);

            // Testy
            testy();

            // Przywrócenie oryginalnego wyjścia standardowego
            System.setOut(consoleOut);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        testy();
    }

    public static void testy() {
        int N = 2000000;
        DoubleUnaryOperator circleFunction = x -> Math.sqrt(1 - x * x);
        MetodaProstokatow p1 = new MetodaProstokatow(-1, 1, N);
        MetodaTrapezow t1 = new MetodaTrapezow(-1, 1, N);
        MetodaSimpsona s1 = new MetodaSimpsona(-1, 1, N);
        System.out.println("MP, MT, MS, Oczekiwana, a, b, BMP, BMT, BMS");
        double mp = p1.result(circleFunction) * 2;
        double mt = t1.result(circleFunction) * 2;
        double ms = s1.result(circleFunction) * 2;
        System.out.println(mp + ", " + mt + ", " + ms + ", " + Math.PI + ", " + 0 + ", " + 0 + ", " + Math.abs(Math.PI - mp) + ", " + Math.abs(Math.PI - mt) + ", " + Math.abs(Math.PI - ms));

        DoubleUnaryOperator UFunction = x -> x * x;
        MetodaProstokatow p2 = new MetodaProstokatow(0, 1, N);
        MetodaTrapezow t2 = new MetodaTrapezow(0, 1, N);
        MetodaSimpsona s2 = new MetodaSimpsona(0, 1, N);
        double help = (double) 1 / 3;
        mp = p2.result(UFunction);
        mt = t2.result(UFunction);
        ms = s2.result(UFunction);
        System.out.println(mp + ", " + mt + ", " + ms + ", " + help + ", " + 0 + ", " + 0 + ", " + Math.abs(help - mp) + ", " + Math.abs(help - mt) + ", " + Math.abs(help - ms));

        Random random = new Random();
        for (int i = 1; i < 1; i++) {
            double a = random.nextInt(15) + 1;
            double b = random.nextInt(15) + 1;

            DoubleUnaryOperator ellipseFunction = x -> Math.sqrt(1 - x * x / (a * a)) * b;
            MetodaProstokatow p3 = new MetodaProstokatow(-a, a, N);
            MetodaTrapezow t3 = new MetodaTrapezow(-a, a, N);
            MetodaSimpsona s3 = new MetodaSimpsona(-a, a, N);
            double ans = Math.PI * a * b;
            mp = p3.result(ellipseFunction) * 2;
            mt = t3.result(ellipseFunction) * 2;
            ms = s3.result(ellipseFunction) * 2;
            System.out.println(mp + ", " + mt + ", " + ms + ", " + ans + ", " + a + ", " + b + ", " + Math.abs(ans - mp) + ", " + Math.abs(ans - mt) + ", " + Math.abs(ans - ms));
        }


        DoubleUnaryOperator sinFunction = x -> Math.sin(x);
        MetodaProstokatow p4 = new MetodaProstokatow(0, Math.PI, N);
        MetodaTrapezow t4 = new MetodaTrapezow(0, Math.PI, N);
        MetodaSimpsona s4 = new MetodaSimpsona(0, Math.PI, N);
        mp = p4.result(sinFunction);
        mt = t4.result(sinFunction);
        ms = s4.result(sinFunction);
        System.out.println(mp + ", " + mt + ", " + ms + ", " + 2 + ", " + 0 + ", " + 0 + ", " + Math.abs(2 - mp) + ", " + Math.abs(2 - mt) + ", " + Math.abs(2 - ms));

        DoubleUnaryOperator circleDerivative = x -> {
            if (1 - x * x == 0) {
                return 0;
            }
            return -x / Math.sqrt(1 - x * x);
        };
        DoubleUnaryOperator sinDerivative = x -> Math.cos(x);
        DoubleUnaryOperator circleLengthFunction = x -> Math.sqrt(1 + Math.pow(circleDerivative.applyAsDouble(x), 2));
        DoubleUnaryOperator sinLengthFunction = x -> Math.sqrt(1 + Math.pow(sinDerivative.applyAsDouble(x), 2));

        MetodaProstokatow p5 = new MetodaProstokatow(0, 1, N);
        MetodaTrapezow t5 = new MetodaTrapezow(0, 1, N);
        MetodaSimpsona s5 = new MetodaSimpsona(0, 1, N);

        mp = p5.result(circleLengthFunction) * 4;
        mt = t5.result(circleLengthFunction) * 4;
        ms = s5.result(circleLengthFunction) * 4;
        System.out.println(mp + ", " + mt + ", " + ms + ", " + Math.PI * 2 + ", " + 0 + ", " + 0 + ", " + Math.abs(Math.PI * 2 - mp) + ", " + Math.abs(Math.PI * 2 - mt) + ", " + Math.abs(Math.PI * 2 - ms));

        for (int i = 1; i < 1; i++) {
            double a = random.nextInt(10) + 1;
            double b = random.nextInt(10) + 1;
            double h = Math.pow((a - b), 2) / Math.pow((a + b), 2);

            DoubleUnaryOperator ellipseDerivative = x -> {
                if (1 - (x * x) / (a * a) < 1e-10) {
                    return 0;
                }
                return -((b * x) / (a * a * Math.sqrt(1 - (x * x) / (a * a))));
            };
            DoubleUnaryOperator ellipseLengthFunction = x -> Math.sqrt(1 + Math.pow(ellipseDerivative.applyAsDouble(x), 2));

            double expectedCircumference = Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
            MetodaProstokatow p6 = new MetodaProstokatow(-a,  a, N);
            MetodaTrapezow t6 = new MetodaTrapezow(-a, a, N);
            MetodaSimpsona s6 = new MetodaSimpsona(-a, a, N);
            mp = p6.result(ellipseLengthFunction) * 2;
            mt = t6.result(ellipseLengthFunction) * 2;
            ms = s6.result(ellipseLengthFunction) * 2;
            System.out.println(mp + ", " + mt + ", " + ms + ", " + expectedCircumference + ", " + a + ", " + b + ", " + Math.abs(expectedCircumference - mp) + ", " + Math.abs(expectedCircumference - mt) + ", " + Math.abs(expectedCircumference - ms));
        }

        MetodaProstokatow p7 = new MetodaProstokatow(0, 2 * Math.PI, N);
        MetodaTrapezow t7 = new MetodaTrapezow(0, 2 * Math.PI, N);
        MetodaSimpsona s7 = new MetodaSimpsona(0, 2 * Math.PI, N);
        mp = p7.result(sinLengthFunction);
        mt = t7.result(sinLengthFunction);
        ms = s7.result(sinLengthFunction);
        System.out.println(mp + ", " + mt + ", " + ms + ", " + 7.6404 + ", " + 0 + ", " + 0 + ", " + Math.abs(7.6404 - mp) + ", " + Math.abs(7.6404 - mt) + ", " + Math.abs(7.6404 - ms));
    }

}