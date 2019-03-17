package Chapter17_DelimiterDirectedTranslation.MissGrantController;

import Chapter1_IntroductoryExample.Event;

public class ResetEventLineParser extends LineParser{
    public ResetEventLineParser(StateMachineParser context) {
        super(context);
    }

    @Override
    void doParse() {
        if (hasOnlyWord("end")) {
            returnToTopLevel();
        }else if(words().length == 1){
            context.registerResetEvent(words(0));
        }
    }
}
