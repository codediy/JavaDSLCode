package Chapter32_ExpressionBuilder;


import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class Event {


    private String name,location;



    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    public Event(){}
    public Event(String name,String location,LocalDate date,LocalTime startTime,LocalTime endTime){
            this.name = name;
            this.location = location;
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
    }
    public Event(String name){
        this.name = name;
    }

    public Event on(int year, int month, int day){
        this.date = new LocalDate(year, month,day);
        return this;
    }
    public Event from(String startTime){
        this.startTime = parseTime(startTime);
        return this;
    }
    public Event to(String endTime){
        this.endTime = parseTime(endTime);
        return this;
    }
    public Event at(String location){
        this.location = location;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    private LocalTime parseTime(String time){
        final  DateTimeFormatter fmt = ISODateTimeFormat.hourMinute();
        return new LocalTime(fmt.parseDateTime(time));
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", \nlocation='" + location + '\'' +
                ", \ndate=" + date +
                ", \nstartTime=" + startTime +
                ", \nendTime=" + endTime +
                '}';
    }



}

