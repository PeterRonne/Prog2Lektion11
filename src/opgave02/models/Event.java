package opgave02.models;

import java.time.LocalDate;
import java.util.List;

public class Event {
    private String name;
    private Organizer organizer;
    private List<Occurrence> occurrences;
    private String description;
    private String image;


    public Event(String name, Organizer organizer) {
        this.name = name;
        this.organizer = organizer;
    }

    public String getName() {
        return name;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public List<Occurrence> getOccurrences() {
        return occurrences;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", organizer=" + organizer +
                ", occurrences=" + occurrences +
                //", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
