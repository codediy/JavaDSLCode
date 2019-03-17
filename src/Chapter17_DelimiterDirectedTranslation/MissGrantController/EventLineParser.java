package Chapter17_DelimiterDirectedTranslation.MissGrantController;

import Chapter1_IntroductoryExample.Command;
import Chapter1_IntroductoryExample.Event;

public class EventLineParser extends LineParser{
    public EventLineParser(StateMachineParser context) {
        super(context);
    }

    @Override
    void doParse() {
        if (hasOnlyWord("end")) {
            returnToTopLevel();
        }else if(words().length == 2){
            context.registerEvent(new Event(words(0),words(1)));
        }else {
            failToRecognizeLine();
        }
    }
}
