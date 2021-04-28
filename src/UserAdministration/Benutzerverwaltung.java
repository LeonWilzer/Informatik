package UserAdministration;

import lib.BinarySearchTree;

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
}