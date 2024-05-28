/*
    przedzial calkowania [Xp, Xk] dzielimy na "n+1" równo odlleggłych punktów X0,X1,...,Xn
    
    
    -----wyznaczanie punktow----
    dla i = 0,1,2,..n
    
    Xi = Xp + i/n * (Xk - Xp)

    -----odleglosc miedzy dwoma sasiednimi punktami = wysokosc kazdego trapezu ----

    dx = (Xk - Xp) / n

    ----Dla kazdego wyznaczonego punktu obliczamy wartosc funkcji f(x) w tym punkcie
    Fi = f(Xi)
    
    ---Przybliżona wartość całki jest sumą pól wszystkich otrzymanych w ten sposób trapezów

    s = P1+P2+..+Pn

 */

 /*
 WEJSCIE
    Xp - poczatek przedzialu calkowania -> R
    Xk - koniec przedzialu calkowania -> R
    n - liczba punktow podzialowych -> N
    f(x) - funkcja rzeczywista, ktorej calke liczymy


WYJSCIE 
    s - przyblizony wynik    
  
 POMOCNICZE
    dx -- odleglosc miedzy dwoma sasiednimi punktami podzialowymi, dx -> R

    i - licznik punktow podzialowych -> N
  
    */

import java.util.function.DoubleUnaryOperator;
public class MetodaTrapezow extends Metoda {


    public MetodaTrapezow(double Xp, double Xk, int n){
        super(Xp, Xk, n);
    }

    private double dx() {
        return (Xk - Xp) / n;
    }
    private double f(double v,DoubleUnaryOperator function){
        return function.applyAsDouble(v);
    }

    public double result(DoubleUnaryOperator function) {
        double s = 0.0;
        double dx = dx();
        for (int i = 1; i < n; i++) {
            double x = Xp + i * dx;
            s += f(x, function);
        }
        s = (s + (f(Xp, function) + f(Xk, function)) / 2) * dx;
        return s;
    }
    
}
