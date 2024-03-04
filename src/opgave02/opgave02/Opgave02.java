package opgave02.opgave02;

import opgave02.controller.Controller;
import opgave02.models.Event;
import opgave02.models.Tag;
import opgave02.storage.EventsStorage;
import opgave02.storage.Storage;

public class Opgave02 {
    public static void main(String[] args) {
        Storage eventsStorage = new EventsStorage();
        Controller controller = new Controller(eventsStorage);
        controller.fetchEvents(Tag.MUSIK);
        for (Event event : controller.getEvents()) {
            System.out.println(event);
        }

    }
}
