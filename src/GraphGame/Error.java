package GraphGame;

import lib.Server;

public enum Error implements Result
{
    ERR,
    ERR_TOO_MANY_USERS,
    ERR_NODE_ALREADY_COLOURED,
    ERR_INVALID_COLOUR,
    ERR_INVALID_USER;

    @Override
    public void resolve(Server pServer, String pClientIP, int pClientPort) {
        switch(ordinal())
        {
            case 1:
                pServer.send(pClientIP, pClientPort, Protocol.ERROR + Protocol.ERR_TOO_MANY_USERS);
            case 2:
                pServer.send(pClientIP, pClientPort, Protocol.ERROR + Protocol.ERR_NODE_ALREADY_COLOURED);
            case 3:
                pServer.send(pClientIP, pClientPort, Protocol.ERROR + Protocol.ERR_INVALID_COLOUR);
            case 4:
                pServer.send(pClientIP, pClientPort, Protocol.ERROR + Protocol.ERR_INVALID_USER);
            default:
                pServer.send(pClientIP, pClientPort, Protocol.ERROR + Protocol.ERR);
        }
    }
}