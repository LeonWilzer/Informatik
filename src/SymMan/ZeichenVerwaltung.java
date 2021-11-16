package SymMan;

import lib.BinarySearchDrawer;
import lib.BinarySearchTree;
import lib.TIO;

public class ZeichenVerwaltung {
    //private String wort;
    private BinarySearchTree<ZeichenMitAnzahl> zeichenBaum;

    public ZeichenVerwaltung(String pWort)
    {
        //wort = pWort;
        WortEinlesen(pWort);
    }

    private void ZeichenEinsortieren(String pZeichen)
    {
        if (ZeichenVorhanden(pZeichen) != -1)
            zeichenBaum.search(new ZeichenMitAnzahl(pZeichen)).erhoeheZeichen();
        else
            zeichenBaum.insert(new ZeichenMitAnzahl(pZeichen));
    }

    private int ZeichenVorhanden(String pZeichen)
    {
        try{
            return zeichenBaum.search(new ZeichenMitAnzahl(pZeichen)).getAnzahl();
        }
        catch(NullPointerException e)
        {
            return -1;
        }
    }

    public void WortEinlesen(String pWort)
    {
        zeichenBaum = new BinarySearchTree<ZeichenMitAnzahl>();
        for (char i : pWort.toCharArray())
            ZeichenEinsortieren(Character.toString(i));
    }

    public static void Demo()
    {
        ZeichenVerwaltung zeiver = new ZeichenVerwaltung(TIO.AskString("Ein Wort bitte:"));
        BinarySearchDrawer bzeich = new BinarySearchDrawer(200, 200, zeiver.getBaum());
        TIO.prt("doe");
    }

    // Getters & Setters
    //public String getWord() { return wort; }
    //public void setWord(String pWort) { wort = pWort; }
    public BinarySearchTree<ZeichenMitAnzahl> getBaum() { return zeichenBaum; }
}