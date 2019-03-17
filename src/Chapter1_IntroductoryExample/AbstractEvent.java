package Chapter1_IntroductoryExample;

public class AbstractEvent {
    protected String name,code;

    public AbstractEvent(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
