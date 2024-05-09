import java.util.function.Function;

public class MetodaProstokatow {

    public double kolo(double x) {
        return Math.PI * x * x;
    }

    public  double obliczCalke(double a, double b, int n, Function<Double, Double> funkcja) {
        double suma = 0.0;
        double dx = (b - a) / n;

        for (int i = 0; i < n; i++) {
            double x = a + i * dx;
            suma += Math.PI *  dx;
            System.out.println(suma);
        }
        return suma;
    }

    public double poleKola() {
        double a = 0.0; // lewa granica całkowania
        double b = 1.0; // prawa granica całkowania
        return obliczCalke(a, b, 1000, this::kolo);
    }

    public void testPolaKola() {
        double pole = poleKola();
        double poleWzor = Math.PI; // Wzór na pole koła o promieniu 1
        System.out.println(pole + " " + poleWzor + " " + (pole - Math.abs(poleWzor)));
    }
}
