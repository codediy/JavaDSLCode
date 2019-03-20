package Chapter33_FunctionSequence;

public class DiskBuilder {
    public int size;
    public ComputerBuilder.Disk.Interface iface;
    public int speed;


    /*创建Disk*/
    public ComputerBuilder.Disk getValue() {
        ComputerBuilder.Disk disk = new ComputerBuilder.Disk(iface, size, speed);
        return disk;
    }
}
