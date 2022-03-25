package GraphGame;

import lib.Server;

public interface Result
{
    public void resolve(Server pServer, String pClientIP, int pClientPort);

    // Example:
    // default public void resolve(Server pServer, String pClientIP, int pClientPort)
    // {
    //     pServer.send(pClientIP, pClientPort, "DEFAULT RESULT MESSAGE");
    // }
}