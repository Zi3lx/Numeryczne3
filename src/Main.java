import java.util.function.DoubleUnaryOperator;
public class Main {
    public static void main(String[] args) {

        //MetodaProstokatow mp = new MetodaProstokatow();
        //mp.testPolaKola();
        MetodaTrapezow s = new MetodaTrapezow(-1, 1, 10000);
        DoubleUnaryOperator circleFunction = x -> Math.sqrt(1 - x * x);
        System.out.println("oczekiwane "+ Math.PI/2 +", wynik: "+s.oblicz(circleFunction));//1.5707946637152863

        MetodaTrapezow s2 = new MetodaTrapezow(0, 1, 10000);
        DoubleUnaryOperator UFunction = x -> x * x;
        System.out.println("oczekiwane 1 wynik: " + s2.oblicz(UFunction)); // 0.333



        double a = 2;
        double b = 1;
        MetodaTrapezow s3 = new MetodaTrapezow(-2, 2, 10000);
        DoubleUnaryOperator elipseFunction = x -> Math.sqrt(1 - x * x / (a * a)) * b;
        System.out.println("oczekiwane "+ Math.PI +", wynik: "+ s3.oblicz(elipseFunction));//3.1415893274305726

        MetodaTrapezow s4 = new MetodaTrapezow(0, Math.PI, 10000);
        DoubleUnaryOperator sinFunction = x -> Math.sin(x);
        System.out.println("oczekiwane 2 wynik: " + s4.oblicz(sinFunction)); // 1.9999999835506608


        



    }
}