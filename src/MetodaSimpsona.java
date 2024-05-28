import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class MetodaSimpsona extends Metoda {

    public MetodaSimpsona(double Xp, double Xk, int n){
        super(Xp, Xk, n);
    }

    public double result(DoubleUnaryOperator f) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Zle dane");
        }

        double h = (Xk - Xp) / n;
        double sum = f.applyAsDouble(Xp) + f.applyAsDouble(Xk);

        for (int i = 1; i < n; i++) {
            double x = Xp + i * h;
            sum += (i % 2 == 0) ? 2 * f.applyAsDouble(x) : 4 * f.applyAsDouble(x);
        }

        return (h / 3) * sum;
    }

}
