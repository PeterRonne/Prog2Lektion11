package opgave02.controller;

import opgave02.models.Event;
import opgave02.models.Tag;
import opgave02.storage.Storage;
import opgave02.storage.EventsStorage;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Storage eventsStorage;
    private List<Event> events = new ArrayList<>();

    public Controller(Storage eventsStorage) {
        this.eventsStorage = eventsStorage;
    }

    public void fetchEvents(Tag tag) {
        events = eventsStorage.getEvents(tag.toString());
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public List<Event> organizedBy(String email) {
        List<Event> organizedEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getOrganizer().getEmail().equals(email)) {
                organizedEvents.add(event);
            }
        }
        return organizedEvents;
    }
}
