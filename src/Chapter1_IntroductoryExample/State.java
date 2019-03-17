package Chapter1_IntroductoryExample;

import java.util.*;
public class State {

    private String name;
    private List<Command> actions = new ArrayList<>();
    private Map<String, Transition> transitions = new HashMap<String, Transition>();

    public State(String name) {
        this.name = name;
    }

    public void addTransition(Event event,State  targetState){
        assert null != targetState;
        transitions.put(event.getCode(),new Transition(this,event,targetState));
    }

    public boolean hasTransition(String eventCode){
        return transitions.containsKey(eventCode);
    }

    public State targetState(String eventCode){
        return transitions.get(eventCode).getTarget();
    }

    /*执行动作*/
    public void executeActions(CommandChannel commandsChannel){
        for (Command command : actions) {
            /*发送命令到设备*/
            commandsChannel.send(command.getCode());
        }
    }
    public void addAction(Command command){
        actions.add(command);
    }
    /*获取所有的转换目标*/
    public Collection<State> getAllTargets(){
        List<State> result = new ArrayList<>();
        for (Transition transition : transitions.values()) {
            result.add(transition.getTarget());
        }
        return  result;
    }


}
