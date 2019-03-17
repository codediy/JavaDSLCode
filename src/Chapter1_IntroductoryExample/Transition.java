package Chapter1_IntroductoryExample;

public class Transition {
    private State source,target;
    private Event trigger;

    public Transition(State source, Event trigger, State target) {
        source = source;
        trigger = trigger;
        target = target;
    }
    public State getSource() {
        return source;
    }
    public State getTarget() {
        return target;
    }
    public Event getTrigger() {
        return trigger;
    }

    public String getEventCode(){
        return trigger.getCode();
    }
}
