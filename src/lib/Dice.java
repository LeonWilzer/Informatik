package lib;

import java.util.Random;
import java.security.SecureRandom;

public class Dice {
    private Random core;
    private SecureRandom secureCore;

    /**
     * An easy to use random number generator
     */

    public Dice() {
        core = new Random();
    }

    public boolean getTruth() {
        return core.nextBoolean();
    }

    public int getNumber(int upperBound) {
        return core.nextInt(upperBound) + 1;
    }

    public int getNumber(int lowerBound, int upperBound) {
        return getNumber(upperBound - lowerBound) + lowerBound;
    }

    public char getChar() {
        return (char) ('a' + getNumber(26));
    }

    public String getIntString(int lenght) {
        StringBuilder memory = new StringBuilder();
        while (lenght > 1) {
            memory.append(getNumber(0, 9));
            lenght--;
        }
        return memory.toString();
    }

    public String getCharString(int lenght) {
        StringBuilder memory = new StringBuilder();
        while (lenght > 1) {
            memory.append(getChar());
            lenght--;
        }
        return memory.toString();
    }

    public String getPassword(int lenght, int hardness, boolean lowerCase, boolean upperCase, boolean numbers, boolean specials) {
        secureCore = new SecureRandom();
        final StringBuilder password = new StringBuilder();

        // dictionary
        final String alphaLowerCase = "abcdefghijklmnopqrstuvwxyz";
        final String alphaUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String numeric = "0123456789";
        final String special = "!@#$%^&*_=+-/";

        String dictionary = new String();
        if (lowerCase) {
            dictionary += alphaLowerCase;
        }
        if (upperCase) {
            dictionary += alphaUpperCase;
        }
        if (numbers) {
            dictionary += numeric;
        }
        if (specials) {
            dictionary += special;
        }

        while (lenght > 1) {
            final int secret = secureCore.nextInt(hardness); // random number gen needs to guess this for the current char to be accepted
            char newChar;

            do {
                // select new random char from dictionary
                int index = secureCore.nextInt(dictionary.length());
                newChar = dictionary.charAt(index);
            } while (secret != secureCore.nextInt(hardness));

            password.append(newChar);
            lenght--;
        }

        return password.toString();
    }
}
