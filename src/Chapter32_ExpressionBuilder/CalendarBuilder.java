package Chapter32_ExpressionBuilder;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public class CalendarBuilder {
    private Calendar content = new Calendar();
    private List<EventBuilder> events = new ArrayList<>();

    public CalendarBuilder add(String name){
        content.addEvent(new Event());
        getCurrentEvent().setName(name);
        return this;
    }
    public EventBuilder addEvent(String name){
        EventBuilder child = new EventBuilder(this);
        events.add(child);
        child.setName(name);
        return child;
    }
    public CalendarBuilder on(int year,int month,int day){
        getCurrentEvent().setDate(new LocalDate(year,month,day));
        return this;
    }
    public CalendarBuilder from(String startTime){
        getCurrentEvent().setStartTime(parseTime(startTime));
        return this;
    }
    public CalendarBuilder to(String endTime){
        getCurrentEvent().setEndTime(parseTime(endTime));
        return this;
    }
    public CalendarBuilder at(String location){
        getCurrentEvent().setLocation(location);
        return this;
    }
    private LocalTime parseTime(String startTime) {
        final DateTimeFormatter fmt = ISODateTimeFormat.hourMinute();
        return new LocalTime(fmt.parseDateTime(startTime));
    }

    private Event getCurrentEvent(){
        return content.getEvents().get(content.getEvents().size()-1);
    }

    public Calendar getContent(){
        return content;
    }

    public Calendar getEventContent(){
        Calendar result = new Calendar();
        for (EventBuilder e : events) {
            result.addEvent(e.getContent());
        }
        return result;
    }
}
