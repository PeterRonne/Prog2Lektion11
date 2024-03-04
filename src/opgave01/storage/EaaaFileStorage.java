package opgave01.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import opgave01.models.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EaaaFileStorage implements EaaaStorage {
    private List<Person> people = new ArrayList<>();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final File file = new File("./src/opgave01/eaaa.json");

    public EaaaFileStorage() {
        load();
    }

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
        try (FileReader fileReader = new FileReader(file)) {
            people = gson.fromJson(fileReader, new TypeToken<ArrayList<Person>>(){}.getType());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void save() {
        try (FileWriter fileWriter = new FileWriter(file)){
            gson.toJson(people, fileWriter);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
