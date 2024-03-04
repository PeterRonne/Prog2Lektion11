package opgave01.controller;

import opgave01.models.Person;
import opgave01.models.Role;
import opgave01.storage.EaaaFileStorage;
import opgave01.storage.EaaaStorage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    EaaaStorage eaaaStorage;

    public Controller(EaaaStorage eaaaStorage) {
        this.eaaaStorage = eaaaStorage;
    }

    /**
     *
     * @param role
     * @return List<Person> where all person has the given role
     */
    public ArrayList<Person> filter(Role role) {
        ArrayList<Person> list = new ArrayList<>();
        for (Person person : eaaaStorage.getPeople()) {
            if (person.getRole() == role)
                list.add(person);
        }
        return list;
    }

    /**
     *
     * @return all persons
     */
    public ArrayList<Person> getPeople() {
        return new ArrayList<>(eaaaStorage.getPeople());
    }

    /**
     * Adds a new person
     * @param person
     */
    public void addPerson(Person person) {
        eaaaStorage.addPerson(person);
    }

    /**
     * Persists all people
     */
    public void save() {
        eaaaStorage.save();
    }
}
