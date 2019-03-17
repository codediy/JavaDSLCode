package Chapter21_RecursiveDescentParser;

import Chapter1_IntroductoryExample.Command;
import Chapter1_IntroductoryExample.Event;
import Chapter1_IntroductoryExample.State;
import Chapter1_IntroductoryExample.StateMachine;
import Chapter20_RegexTableLexer.ScannerPatterns;
import Chapter20_RegexTableLexer.Token;

import java.util.ArrayList;
import java.util.Map;

public class StateMachineParser {

    private TokenBuffer tokenBuffer;
    private StateMachine machineResult;

    private ArrayList<Event> machineEvents;
    private ArrayList<Command> machineCommands;
    private ArrayList<Event> resetEvents;
    private Map<String, State> matchineStates;
    private State partialState;

    public StateMachine startParser(){
        if (stateMachine()){
            loadResetEvents();
        }
        return machineResult;
    }
    private boolean stateMachine() {
        boolean parseSuccess = false;
        if (eventBlock()){
            if (optionalResetBlock()){
                if (optionCommnadBlock()){
                    if (stateList()) {
                        parseSuccess = true;
                    }
                }
            }
        }
        return parseSuccess;
    }

    /*Event解析*/
    private boolean eventBlock() {
        Token t;
        boolean parseSuccess = false;

        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_EVENT)){
            tokenBuffer.popToken();
            /*事件列表解析*/
            parseSuccess = eventDecList();
        }
        if (parseSuccess){
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_END)){
                tokenBuffer.popToken();
            }else{
                parseSuccess = false;
            }
        }
        if (!parseSuccess){
            tokenBuffer.resetCurrentPosition(save);
        }
        return parseSuccess;
    }

    private boolean optionalResetBlock() {
        int save = tokenBuffer.getCurrentPosition();
        boolean parseSuccess = true; /*默认返回true*/
        Token t = tokenBuffer.nextToken();

        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_RESET)){
            tokenBuffer.popToken();
            t = tokenBuffer.nextToken();
            parseSuccess = true;
            while ((!(t.isTokenType(ScannerPatterns.TokenTypes.TT_END))) && parseSuccess){
                /*解析resetEvent*/
                parseSuccess = resetEvent();
                t = tokenBuffer.nextToken();
            }
        }
        if (parseSuccess){
            /*TT_END*/
            tokenBuffer.popToken();
        }else{
            tokenBuffer.resetCurrentPosition(save);
        }
        return parseSuccess;
    }

    private boolean optionCommnadBlock() {
        Token t;
        boolean parseSuccess = true;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_COMMANDS)){
            tokenBuffer.popToken();
            /*事件列表解析*/
            parseSuccess = commandDecList();
        }
        if (parseSuccess){
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_END)){
                tokenBuffer.popToken();
            }else{
                parseSuccess = false;
            }
        }
        if (!parseSuccess){
            tokenBuffer.resetCurrentPosition(save);
        }
        return parseSuccess;
    }
    private boolean stateList() {
        Token t;
        int save = tokenBuffer.getCurrentPosition();
        boolean parseSuccess = false;
        t = tokenBuffer.nextToken();
        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_STATE)){
            tokenBuffer.popToken();
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
              parseSuccess =  stateDecList();
            }
        }

        if (parseSuccess){
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_END)){
                tokenBuffer.popToken();
            }else{
                parseSuccess = false;
            }
        }
        if (!parseSuccess){
            tokenBuffer.resetCurrentPosition(save);
        }

        return parseSuccess;
    }

    /*解析多个StateDec*/
    private boolean stateDecList() {
        int save = tokenBuffer.getCurrentPosition();
        boolean parseSuccess = false;

        if (stateDec()){
            parseSuccess = true;
            while (parseSuccess){
                parseSuccess = stateDec();
            }
            parseSuccess = true;
        }else{
            tokenBuffer.resetCurrentPosition(save);
        }
        return parseSuccess;
    }

    private boolean commandDecList() {
        int save = tokenBuffer.getCurrentPosition();
        boolean parseSuccess = false;

        if (commandDec()){
            parseSuccess = true;
            while (parseSuccess){
                parseSuccess = commandDec();
            }
            parseSuccess = true;
        }else{
            tokenBuffer.resetCurrentPosition(save);
        }
        return parseSuccess;
    }

    private boolean eventDecList() {
        int save = tokenBuffer.getCurrentPosition();
        boolean parseSuccess = false;
        /*反复读取event声明*/
        if (eventDec()){
            parseSuccess = true;
            while (parseSuccess){
                parseSuccess = eventDec();
            }
            /*最后一个解析失败 重置为成功*/
            parseSuccess = true;
        }else{
            /*解析识别 重置Token解析列表*/
            tokenBuffer.resetCurrentPosition(save);
        }

        return parseSuccess;
    }

    private boolean commandDec() {
        Token t;
        boolean parseSuccess = false;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        String elementLeft = "";
        String elementRight = "";

        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
            elementLeft = cosumeIdentifier(t);
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
                elementRight = cosumeIdentifier(t);
                parseSuccess = true;
            }
        }

        if (parseSuccess){
            makeCommandDec(elementLeft,elementRight);
        }else{
            tokenBuffer.resetCurrentPosition(save);
        }
        return false;
    }


    private boolean eventDec() {
        Token t;
        boolean parseSuccess = false;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        String elementLeft = "";
        String elementRight = "";

        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
            elementLeft = cosumeIdentifier(t);
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
                elementRight = cosumeIdentifier(t);
                parseSuccess = true;
            }
        }

        if (parseSuccess){
            makeEventDec(elementLeft,elementRight);
        }else{
            tokenBuffer.resetCurrentPosition(save);
        }
        return false;
    }

    /*单个StateDec*/
    private boolean stateDec() {
        Token t;
        boolean parseSuccess = false;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
            partialState = new State(t.getTokenValue());
            tokenBuffer.popToken();
            if (optionActionBlock()){
                if (transitionDec()){
                    parseSuccess = true;
                }
            }
        }

        if (parseSuccess){
            makeStateDec();
        }else{
            tokenBuffer.resetCurrentPosition(save);
        }
        return false;
    }

    private void makeStateDec() {
    }

    /*解析action*/
    private boolean optionActionBlock() {
        Token t;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        boolean parseSuccess = true;

        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_ACTIONS)){
            tokenBuffer.popToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
                parseSuccess = actionDecList();
            }else{
                parseSuccess = false;
            }
        }

        return  parseSuccess;
    }

    /*action列表*/
    private boolean actionDecList() {
        int save = tokenBuffer.getCurrentPosition();
        boolean parseSuccess = false;
        /*反复读取event声明*/
        if (actionDec()){
            parseSuccess = true;
            while (parseSuccess){
                parseSuccess = actionDec();
            }
            /*最后一个解析失败 重置为成功*/
            parseSuccess = true;
        }else{
            /*解析识别 重置Token解析列表*/
            tokenBuffer.resetCurrentPosition(save);
        }

        return parseSuccess;
    }

    private boolean actionDec() {
        Token t;
        boolean parseSuccess = false;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        String action = "";

        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
            action = cosumeIdentifier(t);
            partialState.addAction(findCommandFromName(action));
            parseSuccess = true;
        }

        if (!parseSuccess){
            tokenBuffer.resetCurrentPosition(save);
        }
        return parseSuccess;
    }


    private boolean transitionDec() {
        Token t;
        boolean parseSuccess = false;
        int save = tokenBuffer.getCurrentPosition();
        t = tokenBuffer.nextToken();
        String triggerName = ""; /*event名称*/
        String tragetName = "";  /*state名称*/

        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
            triggerName = cosumeIdentifier(t);
            t = tokenBuffer.nextToken();
            if (t.isTokenType(ScannerPatterns.TokenTypes.TT_TRANSITION)){
                tokenBuffer.popToken();
                t = tokenBuffer.nextToken();
                if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
                    tragetName = cosumeIdentifier(t);
                    parseSuccess = true;
                }
            }
            /*解析成功*/
            if (parseSuccess){
                Event e = findEventFromName(triggerName);
                State target = findStateFromName(tragetName);
                if (e != null && t != null){
                    partialState.addTransition(e,target);
                }else{
                    parseSuccess = false;
                }
            }
        }

        if (!parseSuccess){
            tokenBuffer.resetCurrentPosition(save);
        }
        return false;
    }


    private String cosumeIdentifier(Token t) {
        String identName = t.getTokenValue();
        tokenBuffer.popToken();
        return identName;

    }


    private void makeEventDec(String left, String right) {
        machineEvents.add(new Event(left,right));
    }
    private void makeCommandDec(String left, String right) {
        machineCommands.add(new Command(left,right));
    }
    private void makeStateDec(String name) {

    }




    private boolean resetEvent() {
        Token t;
        boolean parseSuccess = false;

        t = tokenBuffer.nextToken();
        if (t.isTokenType(ScannerPatterns.TokenTypes.TT_IDENTIFIER)){
            /*查找添加的事件*/
            resetEvents.add(findEventFromName(t.getTokenValue()));
            parseSuccess = true;
            /*reset的声明字符串*/
            tokenBuffer.popToken();
        }
        return parseSuccess;
    }

    private Command findCommandFromName(String name) {
        for (Command command : machineCommands) {
            if (command.getName().equals(name)){
                return command;
            }
        }
        return null;
    }
    private Event findEventFromName(String name) {
        for (Event event : machineEvents) {
            if (event.getName().equals(name)){
                return event;
            }
        }
        return null;
    }

    private State findStateFromName(String name) {
        for (State state : matchineStates.values()) {
            if (state.getName().equals(name)){
                return state;
            }
        }
        return null;
    }









    private void loadResetEvents() {
    }


}
