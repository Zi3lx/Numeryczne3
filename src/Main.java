import java.util.function.DoubleUnaryOperator;
public class Main {
    public static void main(String[] args) {
        double a = 2;
        double b = 1;
        int n = 100000;
        double elipsa = Math.PI * ((3/2)*(a+b) - Math.sqrt(a*b)); // liczone z przybliżonego wzoru

        DoubleUnaryOperator circleFunction = x -> Math.sqrt(1 - x * x);
        DoubleUnaryOperator circleDerivative = x -> -x / Math.sqrt(1 - x * x);

        DoubleUnaryOperator UFunction = x -> x * x;

        DoubleUnaryOperator ellipseFunction = x -> Math.sqrt(1 - x * x / (a * a)) * b;
        DoubleUnaryOperator ellipseDerivative = x -> -x / (a * a) * Math.sqrt(1 - x * x / (a * a)) * b;

        DoubleUnaryOperator sinFunction = x -> Math.sin(x);
        DoubleUnaryOperator sinDerivative = x -> Math.cos(x);

        DoubleUnaryOperator circleLengthFunction = x -> Math.sqrt(1 + Math.pow(circleDerivative.applyAsDouble(x), 2));
        DoubleUnaryOperator ellipseLengthFunction = x -> Math.sqrt(1 + Math.pow(ellipseDerivative.applyAsDouble(x), 2));
        DoubleUnaryOperator sinLengthFunction = x -> Math.sqrt(1 + Math.pow(sinDerivative.applyAsDouble(x), 2));

        System.out.println("POLA: ");
        System.out.println("Metoda prostokątów:");
        MetodaProstokatow mp = new MetodaProstokatow(0, 1, n);
        System.out.println("oczekiwane kolo " + Math.PI / 4 + ", wynik: " + mp.obliczCalke(circleFunction));//1.570794663715291

        MetodaProstokatow mp1 = new MetodaProstokatow(0, 1, n);
        System.out.println("oczekiwane 0.5, parabola " + ", wynik: " + mp1.obliczCalke(UFunction));//0.33328333499999957

        MetodaProstokatow mp2 = new MetodaProstokatow(-2, 2, n);
        System.out.println("oczekiwane elipsa " + Math.PI / 2 + ", wynik: " + mp2.obliczCalke(ellipseFunction));//3.141589327430582

        MetodaProstokatow mp3 = new MetodaProstokatow(0, Math.PI, n);
        System.out.println("oczekiwane sinus " + Math.PI / 2 + ", wynik: " + mp3.obliczCalke(sinFunction));//1.999999983550664



        System.out.println("Metoda trapezów");
        MetodaTrapezow s = new MetodaTrapezow(0, 1, n);
        System.out.println("oczekiwane "+ Math.PI / 4 +", wynik: "+s.oblicz(circleFunction));//1.5707946637152863

        MetodaTrapezow s2 = new MetodaTrapezow(0, 1, n);
        System.out.println("oczekiwane 0.5 wynik: " + s2.oblicz(UFunction)); // 0.333

        MetodaTrapezow s3 = new MetodaTrapezow(-2, 2, n);
        System.out.println("oczekiwane "+ Math.PI + ", wynik: "+ s3.oblicz(ellipseFunction));//3.1415893274305726

        MetodaTrapezow s4 = new MetodaTrapezow(0, Math.PI, n);
        System.out.println("oczekiwane 2 wynik: " + s4.oblicz(sinFunction)); // 1.9999999835506608

        //-----------------------------DLUGOSCI-----------------------------------------
        System.out.println("Obwody");
        System.out.println("Metoda prostoktów");
        MetodaProstokatow mp4 = new MetodaProstokatow(0, 1, n);
        System.out.println("Oczekiwana kola: " + 2*Math.PI/4 + ", otrzymane: " + mp4.obliczCalke(circleLengthFunction));

        MetodaProstokatow mp5 = new MetodaProstokatow(-2, 2, n);
        System.out.println("Oczekiwana elipsy: " + elipsa + ", otrzymane: " + mp5.obliczCalke(ellipseLengthFunction));;

        MetodaProstokatow mp6 = new MetodaProstokatow(0, 2 * Math.PI, n);
        System.out.println("Oczekiwana sin: " + 7.6404 + ", otrzymane: " + mp6.obliczCalke(sinLengthFunction));;


        System.out.println("Metoda trapezów");
        MetodaTrapezow s5 = new MetodaTrapezow(0, 1, n);
        System.out.println("Oczekiwana kola: " + 2*Math.PI/4 + ", otrzymane: " + s5.oblicz(circleLengthFunction));

        MetodaTrapezow s6 = new MetodaTrapezow(-2, 2, n);
        System.out.println("Oczekiwana elipsy: " + elipsa + ", otrzymane: " + s6.oblicz(ellipseLengthFunction));;

        MetodaTrapezow s7 = new MetodaTrapezow(0, 2 * Math.PI, n);
        System.out.println("Oczekiwana sin: " + 7.6404 + ", otrzymane: " + s7.oblicz(sinLengthFunction));
    }
}