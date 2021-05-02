package UserAdministration;

import lib.BinarySearchTree;
import lib.TIO;

public class Benutzerverwaltung {
    private BinarySearchTree<Benutzerprofil> benutzerBaum;
    public Benutzerverwaltung(){
        benutzerBaum = new BinarySearchTree<Benutzerprofil>();
    }

    public void neuenNutzerAnlegen(String pBenutzernamen, String pPW)
    {
        benutzerBaum.insert(new Benutzerprofil(pBenutzernamen, pPW));
    }

    public void nutzerLoeschen(String pBenutzernamen, String pPW)
    {
        benutzerBaum.remove(new Benutzerprofil(pBenutzernamen, pPW));
    }

    public boolean profilVorhanden(String pBenutzernamen)
    {
        return benutzerBaum.search(new Benutzerprofil(pBenutzernamen, null)) != null;
    }

    public Benutzerprofil getBenutzerprofil(String pBenutzernamen)
    {
        return benutzerBaum.search(new Benutzerprofil(pBenutzernamen, null));
    }
    public static void Demo(){
        Benutzerverwaltung useradm = new Benutzerverwaltung();
        useradm.neuenNutzerAnlegen(TIO.AskString("Benutzername:"), TIO.AskString("Passwort:"));
        TIO.prt(useradm.profilVorhanden(TIO.AskString("Benutzer vorhanden?"))+"!");
        System.out.print(useradm.getBenutzerprofil(TIO.AskString("Gesuchter Benutzer:")));
        useradm.nutzerLoeschen(TIO.AskString("Gesuchter loeschen:"), TIO.AskString("Passwort:"));
        TIO.prt(useradm.profilVorhanden(TIO.AskString("Benutzer vorhanden?"))+"!");
    }
}