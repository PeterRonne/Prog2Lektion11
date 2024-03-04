package opgave02.storage;

import opgave02.models.Event;
import opgave02.models.Tag;

import java.util.List;

public interface Storage {
    List<Tag> getTags();

    List<Event> getEvents(String tag);
}
