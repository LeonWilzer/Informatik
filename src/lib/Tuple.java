package lib;

public class Tuple<Key, Value> {
    public Key K;
    public Value V;

    public Tuple(Key pKey, Value pValue) {
        K = pKey;
        V = pValue;
    }
}