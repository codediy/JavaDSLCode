package Chapter33_FunctionSequence;

import java.util.Arrays;

public class Computer {

    private final ComputerBuilder.Disk[] disks;
    private final ComputerBuilder.Processor processor;

    public Computer(ComputerBuilder.Processor processor, ComputerBuilder.Disk[] disks) {
        this.processor = processor;
        this.disks = disks;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "disks=" + Arrays.toString(disks) +
                ", processor=" + processor +
                '}';
    }
}
