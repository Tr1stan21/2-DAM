package org.example.prueba1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.prueba1.model.Person;
import org.example.prueba1.view.PersonViewController;

import java.io.IOException;

public class HelloApplication extends Application {
    private BorderPane rootLayout;
    private Stage primaryStage;

    /**
     * The data as an observable list of Persons.
     */
    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor.
     * JavaFX usa el constructor sin argumentos, así que esto es válido.
     */
    public HelloApplication() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /**
     * Returns the data as an observable list of Persons.
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setLocation(HelloApplication.class.getResource("/org/example/prueba1/view/root-layout.fxml"));
        rootLayout = rootLoader.load();

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.setTitle("AddressApp");
        showPersonView();

        stage.show();

    }

    public void showPersonView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("/org/example/prueba1/view/person-view.fxml"));
            AnchorPane personView = loader.load();

            rootLayout.setCenter(personView);

            PersonViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
