package GraphGame;

public class Protocol
{
    public static final String PORT = "5000";
    public static final char ARG_SEP = '|';
    public static final char CONN_SEP = '-';
    public static final char LIST_SEP = ',';
    public static final char ESCAPE = '\\';
    public static final char BLUE = 'B';
    public static final char GREEN = 'G';
    public static final char YELLOW = 'Y';
    public static final char RED = 'R';
    public static final char SUCC = '+';
    public static final char ERROR = '-';
    public static final String NEWGAME = "NEWGAME";
    public static final String TURN = "TURN";
    public static final String COLARR = "COLARR";
    public static final String GRAPH = "GRAPH";
    public static final String WINNER = "WINNER";
    public static final String OK = "OK";
    public static final String NODE_COLOURED = "NC";
    public static final String ERR = "ERR";
    public static final String ERR_TOO_MANY_USERS = "ETMU";
    public static final String ERR_NODE_ALREADY_COLOURED = "ENAC";
    public static final String ERR_INVALID_COLOUR = "EIC";
    public static final String ERR_INVALID_USER = "EIU";
}