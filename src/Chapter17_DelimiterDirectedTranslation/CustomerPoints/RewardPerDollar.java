package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

public class RewardPerDollar extends Reward{
    public RewardPerDollar(int points){
        super(points);
    }

    public int Score(Activity a){
        return a.getRevenue()*points;
    }
}
