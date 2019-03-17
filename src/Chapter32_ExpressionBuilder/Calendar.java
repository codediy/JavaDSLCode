package Chapter32_ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Event> events = new ArrayList<Event>();
    public Event add(String name){
        Event newEvent = new Event(name);
        events.add(newEvent);
        return newEvent;
    }

    public Event addEvent(Event e){
        events.add(e);
        return e;
    }
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Event event : events) {
            s.append(event.toString()).append(",\n");
        }
        return s.toString();
    }
}
