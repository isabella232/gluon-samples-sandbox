package com.gluonhq.connect.testbench;

import com.gluonhq.cloudlink.client.data.DataClient;
import com.gluonhq.cloudlink.client.data.DataClientBuilder;
import com.gluonhq.cloudlink.client.data.SyncFlag;
import com.gluonhq.cloudlink.client.user.UserClient;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.testbench.entity.Person;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GluonCloudPresenter {

    private static final Logger LOG = Logger.getLogger(GluonCloudPresenter.class.getName());

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Label name;
    @FXML
    private ListView<Person> friends;
    @FXML
    private TextField friendFirstName;
    @FXML
    private TextField friendLastName;

    private GluonObservableObject<Person> charmPerson;
    private GluonObservableList<Person> charmFriends;

    public void initialize() {
        DataClient dataClient = DataClientBuilder.create()
                .authenticateWith(new UserClient())
                .build();

        charmPerson = DataProvider.retrieveObject(dataClient.createObjectDataReader("person", Person.class, SyncFlag.OBJECT_WRITE_THROUGH));
        charmPerson.stateProperty().addListener((obs, ov, nv) -> {
            if (ConnectState.SUCCEEDED.equals(nv)) {
                Person person = charmPerson.get();
                if (person != null) {
                    // if person is not null, it existed in GluonCloud
                    name.textProperty().bind(Bindings.concat(person.firstNameProperty(), " ", person.lastNameProperty()));
                    firstName.setText(person.getFirstName());
                    lastName.setText(person.getLastName());
                } else {
                    // otherwise, store a new person object in GluonCloud
                    person = new Person();
                    person.setFirstName("Johan");
                    person.setLastName("Vos");
                    charmPerson = DataProvider.storeObject(person, dataClient.createObjectDataWriter("person", Person.class, SyncFlag.OBJECT_WRITE_THROUGH));
                    charmPerson.stateProperty().addListener((obs2, ov2, nv2) -> {
                        if (ConnectState.SUCCEEDED.equals(nv)) {
                            Person newPerson = charmPerson.get();
                            name.textProperty().bind(Bindings.concat(newPerson.firstNameProperty(), " ", newPerson.lastNameProperty()));
                            firstName.setText(newPerson.getFirstName());
                            lastName.setText(newPerson.getLastName());
                        } else if (ConnectState.FAILED.equals(nv)) {
                            if (charmPerson.getException() != null) {
                                LOG.log(Level.SEVERE, null, charmPerson.getException());
                            }
                        }
                    });
                }
            } else if (ConnectState.FAILED.equals(nv)) {
                if (charmPerson.getException() != null) {
                    LOG.log(Level.SEVERE, null, charmPerson.getException());
                }
            }
        });

        // retrieve a list of persons from GluonCloud
        charmFriends = DataProvider.retrieveList(dataClient.createListDataReader("friends", Person.class));
        friends.setCellFactory(lv -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(item.getFirstName() + " " + item.getLastName());
                } else {
                    setText(null);
                }
            }
        });
        friends.setItems(charmFriends);
    }

    @FXML
    public void saveInfo() {
        if (charmPerson.get() != null) {
            charmPerson.get().setFirstName(firstName.getText());
            charmPerson.get().setLastName(lastName.getText());
        }
    }

    @FXML
    public void addFriend() {
        Person friend = new Person();
        friend.setFirstName(friendFirstName.getText());
        friend.setLastName(friendLastName.getText());
        charmFriends.add(friend);
    }
}
