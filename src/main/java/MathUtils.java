public class MathUtils {

    public int add(int a, int b) {
        return a + b;
    }

    public int substract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) { /* "throws" word is use  to warn
    the programmer that this method can throw exception ArithmeticException and he should
    threat it when he call the method divide. */
        return a / b;
    }

    public double computeCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

}
