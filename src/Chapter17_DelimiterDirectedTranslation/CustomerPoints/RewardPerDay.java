package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

public class RewardPerDay  extends Reward{

    public RewardPerDay(int points){
        super(points);
    }
    public int Score(Activity a){
        if (!a.getType().equals("stay")){
            throw new IllegalArgumentException();
        }
        return a.getAmount()*points;
    }
}
