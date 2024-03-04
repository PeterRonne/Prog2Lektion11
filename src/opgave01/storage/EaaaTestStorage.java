package opgave01.storage;

import opgave01.models.Person;

import java.util.ArrayList;
import java.util.List;

public class EaaaTestStorage implements EaaaStorage{
    private List<Person> people = new ArrayList<>();
    @Override
    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    @Override
    public void addPerson(Person person) {
        people.add(person);
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }
}
