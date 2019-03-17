package Chapter32_ExpressionBuilder;

public class Main {
    public static void main(String[] args) {
        withOutBuilder();
        withBuilder();
        withEventBuilder();
    }
    private static void withOutBuilder() {
        System.out.println("\n*********withOutBuilder***********\n");
        Calendar cal = new Calendar();
        cal.add("DSL Tutorial")
                .on(2019,3,17)
                .from("09:00")
                .to("16:00")
                .at("Bj");
        cal.add("Making use of Patterns")
                .on(2019,3,18)
                .from("14:15")
                .to("15:45")
                .at("Sh");

        System.out.println(cal.toString());
    }
    private static void withBuilder() {
        System.out.println("\n*********withBuilder***********\n");
        CalendarBuilder builder = new CalendarBuilder();
        builder.add("Builder DSL Tutorial")
                    .on(2019,3,17)
                    .from("09:00")
                    .to("16:00")
                    .at("Bj")
                .add("Making use of Patterns")
                    .on(2019,3,18)
                    .from("14:15")
                    .to("15:45")
                    .at("Sh");
        System.out.println(builder.getContent().toString());
    }

    private static void withEventBuilder() {
        System.out.println("\n*********withEventBuilder***********\n");
        CalendarBuilder builder = new CalendarBuilder();
        builder.addEvent("EventBuilder DSL Tutorial")
                    .on(2019,3,17)
                    .from("09:00")
                    .to("16:00")
                    .at("Bj")
                .addEvent("Making use of Patterns")
                    .on(2019,3,18)
                    .from("14:15")
                    .to("15:45")
                    .at("Sh");
        System.out.println(builder.getEventContent().toString());
    }

}
