package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

public class TypeActivitySpec extends ActivitySpecification {
    private String type;

    public TypeActivitySpec(String type) {
        this.type = type;
    }

    @Override
    public boolean isSatisfiedBy(Activity a) {
        return a.getType().equals(type);
    }
}
