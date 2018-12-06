package activity.drawer.navigation.com.leovegastask.Pojo;

public class Events
{
    private String items;

    private String collectionURI;

    private String available;

    private String returned;

    public String getItems ()
    {
        return items;
    }

    public void setItems (String items)
    {
        this.items = items;
    }

    public String getCollectionURI ()
    {
        return collectionURI;
    }

    public void setCollectionURI (String collectionURI)
    {
        this.collectionURI = collectionURI;
    }

    public String getAvailable ()
    {
        return available;
    }

    public void setAvailable (String available)
    {
        this.available = available;
    }

    public String getReturned ()
    {
        return returned;
    }

    public void setReturned (String returned)
    {
        this.returned = returned;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [items = "+items+", collectionURI = "+collectionURI+", available = "+available+", returned = "+returned+"]";
    }
}