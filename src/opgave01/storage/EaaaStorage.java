package opgave01.storage;

import opgave01.models.Person;

import java.util.List;

public interface EaaaStorage {
    List<Person> getPeople();

    void addPerson(Person person);

    void load();

    void save();
}
