package GraphGame;

public class Turn {
    private Colour colour;
    private char node;
    private boolean notSelf;

    public Turn(char pNode, Colour pColour) {
        node = pNode;
        colour = pColour;
        notSelf = false;
    }

    public Turn(char pNode, Colour pColour, boolean pNotSelf) {
        node = pNode;
        colour = pColour;
        notSelf = pNotSelf;
    }

    public Colour getColour() {
        return colour;
    }

    public char getNode() {
        return node;
    }

    public boolean isSelf() {
        return !notSelf;
    }
}
