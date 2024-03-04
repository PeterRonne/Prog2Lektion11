package opgave02.controller;

import opgave02.models.Event;
import opgave02.models.Organizer;
import opgave02.models.Tag;
import opgave02.storage.EventsStorage;
import opgave02.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {

    private Controller controller;
    private EventsStorage eventsStorageMock;

    @BeforeEach
    void setUp() {
        eventsStorageMock = mock(EventsStorage.class);
        controller = new Controller(eventsStorageMock);
    }

    @Test
    void organizedBy() {
        // Arrange
        String organizerEmail = "organizer@example.com";
        String organizerName = "OrganizedBy";
        Tag tag = Tag.MUSIK;
        List<Event> mockEvents = List.of(
                new Event("Event 1", new Organizer(organizerEmail, organizerName)),
                new Event("Event 2", new Organizer(organizerEmail, organizerName)),
                new Event("Event 3", new Organizer("differentEmail", "differentOrganizer"))
        );
        System.out.println(mockEvents);
        controller.fetchEvents(tag);
        when(eventsStorageMock.getEvents(tag.toString())).thenReturn(mockEvents);

        // Act
        List<Event> organizedEvents = controller.organizedBy(organizerEmail);


        // Assert
        assertEquals(2, organizedEvents.size());
        verify(eventsStorageMock).getEvents(tag.toString());
    }
}