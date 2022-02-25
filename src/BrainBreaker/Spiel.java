package BrainBreaker;

import java.util.Random;

import lib.List;

public class Spiel {
    private int anzahlAbgegebenerTipps;
    private List<Ziffernfolge> tipps;
    private Ziffernfolge geheimerCode;

    public Spiel()
    {
        Random rnd = new Random();
        anzahlAbgegebenerTipps= 0;
        tipps = new List<Ziffernfolge>();
        String code = "";
        code +=  rnd.nextInt(6) + rnd.nextInt(6) + rnd.nextInt(6)+rnd.nextInt(6);
        geheimerCode = new Ziffernfolge(code);
    }
    public void fuegeTippsHinzu(Ziffernfolge pTipp)
    {
        if(pTipp == null)
            return;

        tipps.append(pTipp);
        anzahlAbgegebenerTipps++;
    }
    public int gibAnzahlTipps(){
        return anzahlAbgegebenerTipps;
    }

    public Ziffernfolge gibTipp(int pIndex)
    {
        tipps.toFirst();
        int i = 0;
        while(tipps.hasAccess() && i<pIndex)
        {
            tipps.next();
            i++;
        }
        return tipps.getContent();
    }
}
