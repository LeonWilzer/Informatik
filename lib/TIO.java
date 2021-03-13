package lib;

import java.util.Scanner;

public class TIO {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String COLOR_OUTPUT = ANSI_CYAN;
    public static final String COLOR_QUESTION = ANSI_GREEN;
    public static final String COLOR_ERROR = ANSI_RED;
    public static final String COLOR_WARNING = ANSI_YELLOW;

    static Scanner input = new Scanner(System.in);

    // Shorthand for System.out.println(x);
    static public void prt(String x){
        System.out.println(COLOR_OUTPUT + x + ANSI_RESET);
    }

    // clear Terminal
    static public void cls() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    static public String AskString(String pRequest){
        System.out.println(COLOR_QUESTION + pRequest + ANSI_RESET);

        String text = null;
        while(text == null)
        {
            try{
                text = input.nextLine();
            }
            catch(Exception e){
                System.out.println(COLOR_WARNING+ "Please provide a correct input!" + ANSI_RESET);
                // e.printStackTrace();
            }
        }
        return text;
    }

    static public int AskInt(String pRequest){
        System.out.println(COLOR_QUESTION + pRequest + ANSI_RESET);
        Integer number = null;
        while(number == null){
            try{
                number = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println(COLOR_WARNING+ "Please provide a correct input!" + ANSI_RESET);
                // e.printStackTrace();
            }
        }
        return number;
    }

    static public double AskDouble(String pRequest){
        System.out.println(COLOR_QUESTION + pRequest + ANSI_RESET);

        Double comma = null;
        while(comma == null)
            try{
                comma = Double.parseDouble(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println(COLOR_WARNING+ "Please provide a correct input!" + ANSI_RESET);
                // e.printStackTrace();
            }
        return comma;
    }
}