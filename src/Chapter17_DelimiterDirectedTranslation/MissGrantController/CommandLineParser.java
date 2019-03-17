package Chapter17_DelimiterDirectedTranslation.MissGrantController;

import Chapter1_IntroductoryExample.Command;

public class CommandLineParser extends LineParser{
    public CommandLineParser(StateMachineParser context) {
        super(context);
    }

    @Override
    void doParse() {
        if (hasOnlyWord("end")) {
            returnToTopLevel();
        }else if(words().length == 2){
            context.registerCommand(new Command(words(0),words(1)));
        }else {
            failToRecognizeLine();
        }
    }
}
