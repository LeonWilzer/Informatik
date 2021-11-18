package lib;

public class Triple<First, Second, Third> {
    public First F;
    public Second S;
    public Third T;

    public Triple(First pFirst, Second pSecond, Third pThird) {
        F = pFirst;
        S = pSecond;
        T = pThird;
    }
}