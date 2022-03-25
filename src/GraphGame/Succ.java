package GraphGame;

import lib.Server;

public enum Succ implements Result
{
    OK,
    NODE_COLOURED;

    @Override
    public void resolve(Server pServer, String pClientIP, int pClientPort) {
        switch(ordinal())
        {
            case 1:
                pServer.send(pClientIP, pClientPort, Protocol.SUCC+Protocol.NODE_COLOURED);
                break;
            default:
                pServer.send(pClientIP, pClientPort, Protocol.SUCC+Protocol.OK);
        }
    }
}