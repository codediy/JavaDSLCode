package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

public class Reward {
    protected int points;

    public Reward(int points){
        this.points = points;
    }

    public int Score(Activity a){
        return points;
    }

}
