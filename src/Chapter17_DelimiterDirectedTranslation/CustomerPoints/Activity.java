package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

public class Activity {
    private String Type;
    private int Amount;
    private int Revenue;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getRevenue() {
        return Revenue;
    }

    public void setRevenue(int revenue) {
        Revenue = revenue;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    private String Location;
}
