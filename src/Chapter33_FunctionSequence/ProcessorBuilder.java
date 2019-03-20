package Chapter33_FunctionSequence;

public class ProcessorBuilder {
    public int cores;
    public ComputerBuilder.Processor.Type type;
    public int speed;

    public ComputerBuilder.Processor getValue(){
        ComputerBuilder.Processor processor = new ComputerBuilder.Processor(type, cores, speed);
        return processor;
    }
}
