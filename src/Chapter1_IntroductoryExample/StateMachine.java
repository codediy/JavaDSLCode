package Chapter1_IntroductoryExample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateMachine {
    private State start;
    private List<Event> resetEvents = new ArrayList<>();

    public StateMachine(State start) {
        this.start = start;
    }
    public void addResetEvents(Event...events){
        for (Event e : events) {
            resetEvents.add(e);
        }
    }
    public boolean isResetEvent(String eventCode){
        return resetEventCodes().contains(eventCode);
    }

    public Collection<State> getStates(){
        List<State> result = new ArrayList<>();
        collectStates(result,start);
        return result;
    }

    /*递归获取转换状态关系*/
    private void collectStates(Collection<State> result,State s){
        if (result.contains(s)) return;
        result.add(s);
        for (State next : s.getAllTargets()) {
            collectStates(result,next);
        }
    }

    /*获取所有重置事件*/
    private List<String> resetEventCodes() {
        List<String> result = new ArrayList<>();
        for (Event event : resetEvents) {
            result.add(event.getCode());
        }
        return result;
    }
}
