import java.util.Random;
import java.util.function.DoubleUnaryOperator;
public class Main {
    public static void main(String[] args) {
        testy();
    }



    public static void testy(){
        DoubleUnaryOperator circleFunction = x -> Math.sqrt(1 - x * x);
        System.out.println("S1\noczekiwane "+ Math.PI/2);
        MetodaProstokatow p1 = new MetodaProstokatow(-1, 1, 1000);
        System.out.println("metoda prostokatow "+ p1.result(circleFunction));
        MetodaTrapezow t1 = new MetodaTrapezow(-1, 1, 10000);
        System.out.println("metoda trapezow "+t1.result(circleFunction));
        MetodaSimpsona s1 = new MetodaSimpsona(-1, 1, 10000);
        System.out.println("metoda simpsona " + s1.result(circleFunction));;

        DoubleUnaryOperator UFunction = x -> x * x;
        System.out.println("S2\noczekiwane 1/3");
        MetodaProstokatow p2 = new MetodaProstokatow(0, 1, 1000);
        System.out.println("metoda prostokatow "+ p2.result(UFunction));
        MetodaTrapezow t2 = new MetodaTrapezow(0, 1, 10000);
        System.out.println("metoda trapezow "+t2.result(UFunction));
        MetodaSimpsona s2 = new MetodaSimpsona(0, 1, 10000);
        System.out.println("metoda simpsona " + s2.result(UFunction) + "\n");


        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            double a = random.nextInt(10) + 1;
            double b = random.nextInt(10) + 1;

            DoubleUnaryOperator ellipseFunction = x -> Math.sqrt(1 - x * x / (a * a)) * b;

            System.out.println("Elipsa próba: " + i + " Oczekiwana: " + (Math.PI * a * b));
            MetodaProstokatow p3 = new MetodaProstokatow(-a, a, 1000);
            System.out.println("metoda prostokatow " + p3.result(ellipseFunction) + " dla a: " + a + " b: " + b);
            MetodaTrapezow t3 = new MetodaTrapezow(-a, a, 10000);
            System.out.println("metoda trapezow " + t3.result(ellipseFunction) + " dla a: " + a + " b: " + b);
            MetodaSimpsona s3 = new MetodaSimpsona(-a, a, 10000);
            System.out.println("metoda simpsona " + s3.result(ellipseFunction) + " dla a: " + a + " b: " + b + "\n");
        }


        DoubleUnaryOperator sinFunction = x -> Math.sin(x);
        System.out.println("S4 \noczekiwane: 2 ");
        MetodaProstokatow p4 = new MetodaProstokatow(0, Math.PI, 10000);
        System.out.println("metoda prostokatow " + p4.result(sinFunction));
        MetodaTrapezow t4 = new MetodaTrapezow(0, Math.PI, 10000);
        System.out.println("metoda trapezow " + t4.result(sinFunction));
        MetodaSimpsona s4 = new MetodaSimpsona(0, Math.PI, 10000);
        System.out.println("metoda simpsona " + s4.result(sinFunction) + "\n");

        DoubleUnaryOperator circleDerivative = x -> {
            if (1 - x * x == 0) {
                return 0;
            }
            return -x / Math.sqrt(1 - x * x);
        };
        DoubleUnaryOperator sinDerivative = x -> Math.cos(x);
        DoubleUnaryOperator circleLengthFunction = x -> Math.sqrt(1 + Math.pow(circleDerivative.applyAsDouble(x), 2));
        DoubleUnaryOperator sinLengthFunction = x -> Math.sqrt(1 + Math.pow(sinDerivative.applyAsDouble(x), 2));

        System.out.println("M1\noczekiwane "+ Math.PI / 2);
        MetodaProstokatow p5 = new MetodaProstokatow(0, 1, 10000);
        System.out.println("metoda prostokatow " + p5.result(circleLengthFunction));
        MetodaTrapezow t5 = new MetodaTrapezow(0, 1, 10000);
        System.out.println("metoda trapezow " + t5.result(circleLengthFunction));
        MetodaSimpsona s5 = new MetodaSimpsona(0, 1, 10000);
        System.out.println("metoda simpsona " + s5.result(circleLengthFunction) + "\n");

        for (int i = 1; i < 10; i++) {
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

            System.out.println("M2 Elipsa próba: " + i);

            double expectedCircumference = Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
            System.out.println("Oczekiwane elipsa: " + expectedCircumference);
            MetodaProstokatow p6 = new MetodaProstokatow(-a,  a, 10000);
            System.out.println("Metoda prostokatow: " + p6.result(ellipseLengthFunction) * 2 + " dla a: " + a + " b: " + b);
            MetodaTrapezow t6 = new MetodaTrapezow(-a, a, 10000);
            System.out.println("Metoda trapezow: " + t6.result(ellipseLengthFunction) * 2 + " dla a: " + a + " b: " + b);
            MetodaSimpsona s6 = new MetodaSimpsona(-a, a, 10000);
            System.out.println("Metoda Simpsona: " + s6.result(ellipseLengthFunction) * 2 + " dla a: " + a + " b: " + b + "\n");
        }

        System.out.println("M3\noczekiwane 7.6404");
        MetodaProstokatow p7 = new MetodaProstokatow(0, 2 * Math.PI, 1000);
        System.out.println("metoda prostokatow "+ p7.result(sinLengthFunction));
        MetodaTrapezow t7 = new MetodaTrapezow(0, 2 * Math.PI, 10000);
        System.out.println("metoda trapezow "+t7.result(sinLengthFunction));
        MetodaSimpsona s7 = new MetodaSimpsona(0, 2 * Math.PI, 10000);
        System.out.println("metoda simpsona " + s7.result(sinLengthFunction));

    }

}