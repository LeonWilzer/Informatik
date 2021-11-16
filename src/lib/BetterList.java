package lib;

public class BetterList<ContentType> extends List<ContentType>{
    public BetterList()
    {
        super();
    }
    public int size()
    {
        int count = 0;
    
        toFirst();
        while(hasAccess())
            {
                count++;
                next();
            }
        return count;
    }
}
