package GraphGame;

public class ColourHelper {
    public static Colour getColourFromChar(char pInput) {
        switch (pInput) {
            case 'R':
                return Colour.RED;
                
            case 'G':
                return Colour.GREEN;

            case 'B':
                return Colour.BLUE;

            case 'Y':
                return Colour.YELLOW;

            default:
                return null;
        }
    }
}
