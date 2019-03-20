package Chapter33_FunctionSequence;

import javax.annotation.processing.Processor;
import java.util.ArrayList;
import java.util.List;

abstract public class ComputerBuilder {
    private ProcessorBuilder processor;
    private List<DiskBuilder> disks = new ArrayList<>();

    private ProcessorBuilder currentProcessor;
    private DiskBuilder currentDisk;

    /*处理器类型*/
    public static class Processor {
        public enum Type {
            i386("i386");
            private String type;

            Type(String type) {
                this.type = type;
            }
        }

        private Type type;
        private int cores;
        private int speed;

        public Processor(Type type, int cores,int speed) {
            this.type = type;
            this.cores = cores;
            this.speed = speed;
        }



        @Override
        public String toString() {
            return "Processor{" +
                    "type=" + type +
                    ", cores=" + cores +
                    ", speed=" + speed +
                    '}';
        }
    }

    /*Disk类型*/
    public static class Disk {


        public enum Interface {
            SATA("sata");
            private String type;

            Interface(String type) {
                this.type = type;
            }
        }

        private Interface iface;
        private int size;
        private int speed;

        public Disk(Interface iface, int size,int speed) {
            this.iface = iface;
            this.size = size;
            this.speed = speed;
        }

        @Override
        public String toString() {
            return "Disk{" +
                    "iface=" + iface +
                    ", size=" + size +
                    ", speed=" + speed +
                    '}';
        }
    }

    public void computer() {
        currentProcessor = null;
        currentDisk = null;
    }

    public void processor() {
        currentProcessor = new ProcessorBuilder();
        processor = currentProcessor;
        currentDisk = null;
    }

    public void disk() {
        currentDisk = new DiskBuilder();
        disks.add(currentDisk);
        currentProcessor = null;
    }

    public void corse(int arg) {
        currentProcessor.cores = arg;
    }

    public void i386() {
        currentProcessor.type = Processor.Type.i386;
    }

    public void size(int arg) {
        currentDisk.size = arg;
    }

    public void sata() {
        currentDisk.iface = Disk.Interface.SATA;
    }

    public void speed(int arg) {
        if (currentProcessor != null) {
            currentProcessor.speed = arg;
        } else if (currentDisk != null) {
            currentDisk.speed = arg;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Computer getValue() {
        return new Computer(processor.getValue(), getDiskValues());
    }

    private Disk[] getDiskValues() {
        Disk[] result = new Disk[disks.size()];
        for (int i = 0; i < disks.size(); i++) {
            result[i] = disks.get(i).getValue();
        }
        return result;
    }

    public Computer run() {
        build();
        return getValue();
    }

    abstract protected void build();

}
