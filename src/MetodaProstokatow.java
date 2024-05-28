import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class MetodaProstokatow extends Metoda {

    public MetodaProstokatow(double Xp, double Xk, int n) {
        super(Xp, Xk, n);
    }

    private double f(double v,DoubleUnaryOperator function){
        return function.applyAsDouble(v);
    }
    public double result(DoubleUnaryOperator function) {
        double suma = 0.0;
        double dx = (Xk - Xp) / n;

        for (int i = 0; i < n; i++) {
            double x = Xp + i * dx;
            suma += f(x, function) *  dx;
        }
        return suma;
    }
}
