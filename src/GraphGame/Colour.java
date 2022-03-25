package GraphGame;

public enum Colour
{
    RED,
    BLUE,
    GREEN,
    YELLOW;

    public char toChar()
    {
        switch(ordinal())
        {
            case 0:
                return 'R';
            case 1:
                return 'B';
            case 2:
                return 'G';
            default:
                return 'Y';
        }
    }
}
