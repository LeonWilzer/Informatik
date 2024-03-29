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
        String code = new String();
        code += rnd.nextInt(6);
        code += rnd.nextInt(6);
        code += rnd.nextInt(6);
        code += rnd.nextInt(6);
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

    public void computerZug()
    {
        int[] h = new int[4];
        for(int i=0; i<6; i++)
        {
            Ziffernfolge zf = new Ziffernfolge(String.format("%o%o%o%o",i));
            int bewertung = geheimerCode.ermittleBewertung(zf);
            int anz = bewertung / 10;
            h[i]=anz;
        }

        List<Ziffernfolge> liste = new List<Ziffernfolge>();
    }

}
