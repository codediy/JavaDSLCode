package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OfferScriptParser {
    /*读取的输入内容*/
    private BufferedReader input;

    private List<Offer> result = new ArrayList<>();


    /*静态调用解析文件*/
    public static List<Offer> loadFile(String fileName){
        try{
            OfferScriptParser loader = new OfferScriptParser(new FileReader(fileName));
            loader.run();
            return loader.result;
        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }
    }

    public OfferScriptParser(Reader reader) {
        input = new BufferedReader(reader);
    }

    /*解析入口*/
    private List<Offer> run(){
        /*当前解析的行*/
        String line;
        try {
            while((line = input.readLine()) != null){
                line = appendContinuingLine(line);
                parseLine(line);
            }
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
        return result;
    }

    private String appendContinuingLine(String line) {
        return line;
    }

    private void parseLine(String line) {
    }


}
