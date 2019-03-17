package Chapter17_DelimiterDirectedTranslation.MissGrantController;

import java.util.Arrays;

abstract class LineParser {
    protected final StateMachineParser context;
    protected String line;
    public LineParser(StateMachineParser context) {
        this.context = context;
    }

    public void parse(String s){
        line = s;
        line = removeComment(line);
        line = line.trim();
        if (isBlankLine()) return;
        /*实际的解析过程*/
        doParse();
    }

    /*以\开头的*/
    private boolean isBlankLine(){
        return line.matches("^\\s*$");
    }
    /*注释内容#. 替换为空*/
    private String removeComment(String line){
        return line.replaceFirst("#.*","");
    }

    /*检查当前行是否是单词word*/
    protected boolean hasOnlyWord(String word){
        if (words(0).equals(word)){
            if (words().length != 1) failToRecognizeLine();
            return true;
        }else{
            return false;
        }
    }
    /*以空格拆分单行*/
    protected String[] words(){
        return line.split("\\s+");
    }
    protected  String  words(int index){
        return words()[index];
    }
    /*获取行单词的一部分*/
    protected String[] wordsStartStringWith(int start){
        return Arrays.copyOfRange(words(),start,words().length);
    }
    /*设置位顶级行解析器*/
    protected void returnToTopLevel(){
        context.setLineParser(new TopLevelLineParser(context));
    }
    protected  void failToRecognizeLine(){
        throw new RuntimeException("行识别错误");
    }

    /*实际的行解析*/
    abstract void doParse();
}
