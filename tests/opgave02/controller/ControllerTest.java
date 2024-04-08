package opgave02.controller;

import opgave02.models.Event;
import opgave02.models.Organizer;
import opgave02.models.Tag;
import opgave02.storage.EventsStorage;
import opgave02.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {

//    private Controller controller;
//    private EventsStorage eventsStorageMock;

    @BeforeEach
    void setUp() {
//        eventsStorageMock = mock(EventsStorage.class);
//        controller = new Controller(eventsStorageMock);
    }


    @Test
    void organizedByTestTest() {
        // Arrange
        Storage eventsStorageMock = mock(EventsStorage.class);
        Controller controller = new Controller(eventsStorageMock);
        Tag tag = Tag.MUSIK;
        String organizerEmail = "organizer@example.com";
        String organizerName = "OrganizedBy";
        List<Event> mockEvents = List.of(
                new Event("Event 1", new Organizer(organizerEmail, organizerName)),
                new Event("Event 2", new Organizer(organizerEmail, organizerName)),
                new Event("Event 3", new Organizer("differentEmail", "differentOrganizer"))
        );

        when(eventsStorageMock.getEvents(tag.toString())).thenReturn(mockEvents);
        System.out.println(mockEvents);
        controller.fetchEvents(tag);
        List<Event> AllEvents = eventsStorageMock.getEvents(tag.toString());
        // Act
        boolean eventsOrganizedBy = controller.organizedBy(organizerEmail).stream().allMatch(event -> event.getOrganizer().getEmail().equals(organizerEmail));
        mockEvents = controller.organizedBy(organizerEmail);
        controller.fetchEvents(tag);
        System.out.println(mockEvents);
        // Assert
        assertTrue(eventsOrganizedBy);
        verify(eventsStorageMock).getEvents(tag.toString());
    }

//    @Test
//    void organizedByTest() {
//        // Arrange
//        String organizerEmail = "organizer@example.com";
//        String organizerName = "OrganizedBy";
//        Tag tag = Tag.MUSIK;
//        List<Event> mockEvents = List.of(
//                new Event("Event 1", new Organizer(organizerEmail, organizerName)),
//                new Event("Event 2", new Organizer(organizerEmail, organizerName)),
//                new Event("Event 3", new Organizer("differentEmail", "differentOrganizer"))
//        );
//        System.out.println(mockEvents);
//        controller.fetchEvents(tag);
//        when(eventsStorageMock.getEvents(tag.toString())).thenReturn(mockEvents);
//        // Act
//        boolean eventsOrganizedBy = controller.organizedBy(organizerEmail).stream().allMatch(event -> event.getOrganizer().getEmail().equals(organizerEmail));
//        mockEvents = controller.organizedBy(organizerEmail);
//        controller.fetchEvents(tag);
//        System.out.println(mockEvents);
//        // Assert
//        assertTrue(eventsOrganizedBy);
//        verify(eventsStorageMock).getEvents(tag.toString());
//    }





}