package Chapter17_DelimiterDirectedTranslation.MissGrantController;

import Chapter1_IntroductoryExample.State;

public class TopLevelLineParser extends LineParser{
    TopLevelLineParser(StateMachineParser context){
        super(context);
    }

    @Override
    void doParse() {
        if (hasOnlyWord("commands")){
            context.setLineParser(new CommandLineParser(context));
        }else if(hasOnlyWord("events")){
            context.setLineParser(new EventLineParser(context));
        }else if(hasOnlyWord("resetEvents")){
            context.setLineParser(new ResetEventLineParser(context));
        }else if(hasOnlyWord("state")){
            processState();
        }else{
            failToRecognizeLine();
        }
    }


    private void processState() {
        /*注册state*/
        State state = context.obtainState(words(1));
        /*初始化StateMachine*/
        context.primeMachine(state);
        /*进入StateLineParser*/
        context.setLineParser(new StateLineParser(context,state));
    }



}
