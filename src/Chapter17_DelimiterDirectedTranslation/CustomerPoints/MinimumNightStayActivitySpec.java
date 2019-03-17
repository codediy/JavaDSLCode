package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

public class MinimumNightStayActivitySpec extends ActivitySpecification {
    private int minimumNumberOfNights;
    public MinimumNightStayActivitySpec(int number){
        this.minimumNumberOfNights = number;
    }

    @Override
    public boolean isSatisfiedBy(Activity a) {
        return a.getType().equals("stay") && a.getAmount() >= minimumNumberOfNights;
    }
}
