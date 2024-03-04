package opgave01.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import opgave01.controller.Controller;
import opgave01.models.Person;
import opgave01.models.Role;
import opgave01.storage.EaaaFileStorage;
import opgave01.storage.EaaaStorage;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {

    }

//    b) Lav en brugergrænseflade så man kan få vist en liste over personer, tilføje
//    en person og filtrerer på rollen

    private final ListView<Person> personListView = new ListView<>();
    private final ComboBox<Role> roleComboBox = new ComboBox<>();
    private ToggleGroup group = new ToggleGroup();
    private final EaaaStorage eaaaStorage = new EaaaFileStorage();
    private final Controller controller = new Controller(eaaaStorage);

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        int height = 300;
        int width = 300;
        personListView.setPrefSize(width, height);
        personListView.getItems().setAll(controller.getPeople());
        pane.add(personListView, 0, 0);

        TextField txfNavn = new TextField();

        Button btnAddPerson = new Button("Tilføj person");
        btnAddPerson.setOnAction(event -> addPersonAction(txfNavn));

        HBox radioBox = new HBox();

        radioBox.setSpacing(10);

        String[] roleStrings = {"Elev", "Lærer", "Alle"};
        Role[] roles = {Role.STUDENT, Role.TEACHER, null};
        for (int i = 0; i < roles.length; i++) {
            RadioButton rb = new RadioButton();
            radioBox.getChildren().add(rb);
            rb.setText(roleStrings[i]);
            rb.setUserData(roles[i]);
            rb.setToggleGroup(group);
        }

        RadioButton rb0 = (RadioButton) group.getToggles().get(0);
        rb0.setSelected(true);

        HBox box = new HBox(btnAddPerson, txfNavn);
        box.setSpacing(10);
        VBox addPersonBox = new VBox(box, radioBox);
        addPersonBox.setSpacing(10);
        pane.add(addPersonBox, 0, 1);

        Button btnFilter = new Button("Filtrer personer");
        btnFilter.setOnAction(event -> filter());
        pane.add(btnFilter, 0, 2);
    }

    private void filter() {
        RadioButton rb = (RadioButton) group.getSelectedToggle();
        Role role = (Role) rb.getUserData();
        if (role != null) {
            personListView.getItems().setAll(controller.filter(role));
        } else {
            personListView.getItems().setAll(controller.getPeople());
        }
    }

    private void addPersonAction(TextField textField) {
        String name = textField.getText();
        RadioButton rb = (RadioButton) group.getSelectedToggle();
        Role role = (Role) rb.getUserData();
        if (!name.isEmpty()) {
            Person person = new Person(name, role);
            controller.addPerson(person);
            controller.save();
            personListView.getItems().setAll(controller.getPeople());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du mangler at indtaste et navn");
            alert.show();
        }
    }
}
