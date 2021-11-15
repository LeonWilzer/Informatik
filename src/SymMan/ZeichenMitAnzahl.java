package SymMan;

import lib.ComparableContent;

public class ZeichenMitAnzahl implements ComparableContent<ZeichenMitAnzahl>{
    private String zeichen;
    private int anzahl = -1;
    public ZeichenMitAnzahl(String pZeichen)
    {
        zeichen = pZeichen;
        anzahl = 1;
    }

    public void erhoeheZeichen()
    {
        anzahl++;
    }

    @Override
    public boolean isGreater(ZeichenMitAnzahl pContent)
    {
        return zeichen.compareTo(pContent.getZeichen())>0;
    }

    @Override
    public boolean isEqual(ZeichenMitAnzahl pContent)
    {
        return zeichen.compareTo(pContent.getZeichen())==0;
    }

    @Override
    public boolean isLess(ZeichenMitAnzahl pContent)
    {
        return zeichen.compareTo(pContent.getZeichen())<0;
    }

    public int getAnzahl() { return anzahl; }
    public String getZeichen() { return zeichen; }
}
