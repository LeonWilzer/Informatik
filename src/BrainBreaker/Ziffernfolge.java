package BrainBreaker;

public class Ziffernfolge {
    private int[] ziffern;

    public Ziffernfolge(String pCodierung){
        ziffern = new int[4];
        for(int i=0;i<4;i++)
            ziffern[i]=pCodierung.charAt(i);
    }

    public int gibWert(int pIndex)
    {
        if(pIndex<0 && pIndex>4)
            return -1;
        else
            return ziffern[pIndex];
    }

    public void setzeWert(int pIndex, int pWert)
    {
        if(pIndex<0 && pIndex>4)
            return;
        else
            ziffern[pIndex] = pWert;
    }

    public Ziffernfolge bildeKopie()
    {
        Ziffernfolge zf = new Ziffernfolge("----");
        for(int i=0;i<4;i++)
            zf.setzeWert(i, ziffern[i]);

        return zf;
    }

    public int ermittleBewertung(Ziffernfolge pAndereFolge)
    {
        Ziffernfolge tippKopie = pAndereFolge.bildeKopie();
        Ziffernfolge kopie = bildeKopie();
        int punkte = 0;

        for(int i=0;i<4;i++)
        {
            if(tippKopie.gibWert(i)==kopie.gibWert(i))
            {
                tippKopie.setzeWert(i, -1);
                kopie.setzeWert(i, -2);
                punkte += 10;
            }
        }

        if(punkte==40)
            return punkte;

        for(int i=0;i<4;i++)
        {
            for(int j=0; j<4; j++)
            {
                if(tippKopie.gibWert(i)==kopie.gibWert(j))
                {
                    tippKopie.setzeWert(i, -1);
                    kopie.setzeWert(j, -2);
                    punkte++;

                }
            }
        }
        return punkte;
    }
}