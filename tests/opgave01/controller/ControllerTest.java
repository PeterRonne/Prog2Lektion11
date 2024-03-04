package opgave01.controller;

import opgave01.models.Person;
import opgave01.models.Role;
import opgave01.storage.EaaaFileStorage;
import opgave01.storage.EaaaTestStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    @BeforeEach
    void setUp() {
        controller = new Controller(new EaaaTestStorage());
        controller.addPerson(new Person("Student", Role.STUDENT));
        controller.addPerson(new Person("Student", Role.STUDENT));
        controller.addPerson(new Person("Teacher", Role.TEACHER));
    }

    @Test
    void filter() {
        // Arrange
        ArrayList<Person> people = controller.getPeople();
        Role teacherRole = Role.TEACHER;
        Role studentRole = Role.STUDENT;
        // Act
        ArrayList<Person> teachers = controller.filter(teacherRole);
        ArrayList<Person> Students = controller.filter(studentRole);
        // Assert
        for (Person person : teachers) {
            assertEquals(person.getRole(), teacherRole);
        }

        for (Person person : Students) {
            assertEquals(person.getRole(), studentRole);
        }
    }

    @Test
    void getPeople() {
        int sizeBeforeAdding = controller.getPeople().size();

        Person person = new Person("Rasmus", Role.STUDENT);
        controller.addPerson(person);

        ArrayList<Person> peopleList = controller.getPeople();
        assertEquals(sizeBeforeAdding + 1, peopleList.size());
    }

    @Test
    void addPerson() {
        // Arrange
        Person person = new Person("Bob", Role.TEACHER);
        controller.addPerson(person);
        // Act
        ArrayList<Person> peopleList = controller.getPeople();
        // Assert
        assertEquals("Bob", peopleList.getLast().getName());
    }
}