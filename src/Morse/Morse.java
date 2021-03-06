package Morse;

import lib.BinaryTree;
import lib.Queue;
import lib.TreeHelper;

public class Morse {
    private final BinaryTree<Character> morseTree;
    private Queue<Character> morseAlphabet;

    public Morse(){
        morseAlphabet = new Queue<Character>();
        // Dynamic binary tree setup
        morseAlphabet.enqueue('e');
        morseAlphabet.enqueue('t');
        morseAlphabet.enqueue('i');
        morseAlphabet.enqueue('a');
        morseAlphabet.enqueue('s');
        morseAlphabet.enqueue('u');
        morseAlphabet.enqueue('h');
        morseAlphabet.enqueue('v');
        morseAlphabet.enqueue('5');
        morseAlphabet.enqueue('4');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('3');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('f');
        morseAlphabet.enqueue('ü');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('2');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('r');
        morseAlphabet.enqueue('w');
        morseAlphabet.enqueue('l');
        morseAlphabet.enqueue('ä');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('p');
        morseAlphabet.enqueue('j');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('1');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('n');
        morseAlphabet.enqueue('m');
        morseAlphabet.enqueue('d');
        morseAlphabet.enqueue('k');
        morseAlphabet.enqueue('b');
        morseAlphabet.enqueue('x');
        morseAlphabet.enqueue('6');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('c');
        morseAlphabet.enqueue('y');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('g');
        morseAlphabet.enqueue('o');
        morseAlphabet.enqueue('z');
        morseAlphabet.enqueue('q');
        morseAlphabet.enqueue('7');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('ö');
        morseAlphabet.enqueue('?');
        morseAlphabet.enqueue('8');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('\u0000');
        morseAlphabet.enqueue('9');
        morseAlphabet.enqueue('0');

        // Preparing Tree setup
        TreeHelper<Character> btrBuilder = new TreeHelper<Character>();
        btrBuilder.setTreeQueue(morseAlphabet);
        btrBuilder.setBreakCondition('\u0000');

        // Building tree
        morseTree = btrBuilder.buildBinTree('\u0000');

        /*
        // Alphabet test
        System.out.println(decode(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.."));
        System.out.println(encode("abcdefghijklmnopqrstuvwxyz"));
        if(decode(encode("abcdefghijklmnopqrstuvwxyz"))=="abcdefghijklmnopqrstuvwxyzu");
            System.out.println(true);
        */
    }


    public String decode(String pMorse){
        char[] morseArray = pMorse.toCharArray();
        String curString = "";
        BinaryTree<Character> curTree = morseTree;

        for(char i : morseArray){
            switch(i)
            {
                case '.':
                    curTree = curTree.getLeftTree();
                    break;
                case '-':
                    curTree = curTree.getRightTree();
                    break;
                case ' ':
                    curString += curTree.getContent() + " ";
                    curTree = morseTree;
                    break;
                default:
                    curString += curTree.getContent();
                    curTree = morseTree;
                    break;
            }
        }
        curString += curTree.getContent();
        return curString;
    }

    public String encodeString(String pSentence)
    {
        String encodedText = "";
        for(char i : pSentence.toLowerCase().toCharArray())
        {
            if(Character.isLetterOrDigit(i))
                encodedText += encodeChar(i, morseTree) + "|";
            else if (Character.isWhitespace(i))
                encodedText += i;
        }
        return encodedText;
    }

    public String encodeChar(char pClear, BinaryTree<Character> pMorseTree)
    {
        if(pMorseTree.getContent() == pClear)
            return "";

        String morseCode;
        if(!pMorseTree.getLeftTree().isEmpty())
        {
            morseCode = encodeChar(pClear, pMorseTree.getLeftTree());
            if (morseCode != null)
                return "." + morseCode;
        }
        if(!pMorseTree.getRightTree().isEmpty())
        {
            morseCode = encodeChar(pClear, pMorseTree.getRightTree());
            if(morseCode != null)
                return "-" + morseCode;
        }
        return null;
    }
}