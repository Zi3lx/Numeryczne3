import java.util.function.DoubleUnaryOperator;

public class MetodaProstokatow {

    private double a;
    private double b;
    private int n;

    public MetodaProstokatow(double a, double b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    private double f(double v, DoubleUnaryOperator function) {
        return function.applyAsDouble(v);
    }

    public double obliczCalke(DoubleUnaryOperator function) {
        double suma = 0.0;
        double dx = (b - a) / n;

        for (int i = 0; i < n; i++) {
            double x = a + i * dx;
            suma += f(x, function) * dx;
        }
        return suma;
    }
}
