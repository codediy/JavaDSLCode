package Chapter32_ExpressionBuilder;


import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class EventBuilder {
    private CalendarBuilder parent;

    private String name, location;
    private LocalDate date;
    private LocalTime statrTime, endTime;

    public EventBuilder(CalendarBuilder parent) {
        this.parent = parent;
    }


    public void setName(String name) {
        this.name = name;
    }

    public EventBuilder addEvent(String name){
        return parent.addEvent(name);
    }
    public EventBuilder on(int year, int month, int day) {
        this.date = new LocalDate(year, month, day);
        return this;
    }

    public EventBuilder from(String startTime){
        this.statrTime = parseTime(startTime);
        return this;
    }

    public EventBuilder to(String endTime){
        this.endTime = parseTime(endTime);
        return this;
    }

    public EventBuilder at(String location){
        this.location = location;
        return this;
    }

    public Event getContent(){
        return new Event(name,location,date,statrTime,endTime);
    }
    private LocalTime parseTime(String startTime) {
        DateTimeFormatter fmt = ISODateTimeFormat.hourMinute();
        return new LocalTime(fmt.parseDateTime(startTime));
    }

}
