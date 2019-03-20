package Chapter33_FunctionSequence;

public class Script  extends ComputerBuilder{

    public static void main(String[] args) {
        Script script = new Script();
        script.build();
    }

    protected void build(){
        computer();
        processor();
            corse(2);
            speed(2500);
            i386();
            disk();
            size(150);
            disk();
            size(75);
            speed(7200);
            sata();
        Computer c = getValue();
        /*获取结果*/
        System.out.println(c.toString());
    }

}
