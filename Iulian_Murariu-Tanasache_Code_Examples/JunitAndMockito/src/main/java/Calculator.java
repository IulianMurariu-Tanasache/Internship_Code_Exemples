public class Calculator {

    public double add(double x, double y) {
        return x + y;
    }

    public double badSubstract(double x, double y) {
        return x - y - 1;
    }

    public double multiply(double x, double y) {
        return x * y;
    }

    public double divide(double x, double y) throws ArithmeticException{
        if(y == 0) throw new ArithmeticException();
        return x / y;
    }
}
