package UserAdministration;

import lib.ComparableContent;

public class Benutzerprofil implements ComparableContent<Benutzerprofil>{
    private String benutzername;
    private String benutzerpw;

    public Benutzerprofil(String login, String pw)
    {
        benutzername = login;
        benutzerpw = pw;
    }

    @Override
    public boolean isGreater(Benutzerprofil pContent) {
        return this.getBenutzername().compareTo(pContent.getBenutzername())>0;
    }

    @Override
    public boolean isEqual(Benutzerprofil pContent) {
        return this.getBenutzername().compareTo(pContent.getBenutzername())==0;
    }

    @Override
    public boolean isLess(Benutzerprofil pContent) {
        return this.getBenutzername().compareTo(pContent.getBenutzername())<0;
    }

    // Getters
    public String getBenutzername() { return benutzername; }
}