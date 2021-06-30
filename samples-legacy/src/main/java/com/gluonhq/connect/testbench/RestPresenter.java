package com.gluonhq.connect.testbench;

import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import com.gluonhq.connect.testbench.entity.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestPresenter {

    private static final Logger LOG = Logger.getLogger(RestPresenter.class.getName());

    @FXML
    ListView<ToDoItem> toDoItems;

    public void initialize() {
        // retrieve a list of todo items from a rest source
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://jsonplaceholder.typicode.com")
                .path("/todos");
        GluonObservableList<ToDoItem> charmTodoItems = DataProvider.retrieveList(restClient.createListDataReader(ToDoItem.class));
        toDoItems.setCellFactory(lv -> new ListCell<ToDoItem>() {
            @Override
            protected void updateItem(ToDoItem item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    CheckBox checkBox = new CheckBox(item.getId() + ". " + item.getTitle());
                    checkBox.setSelected(item.isCompleted());
                    checkBox.setDisable(true);
                    setGraphic(checkBox);
                } else {
                    setGraphic(null);
                }
            }
        });
        toDoItems.setItems(charmTodoItems);

        // retrieve a single todo item
        restClient.path("/todos/1");
        GluonObservableObject<ToDoItem> singleToDoItem = DataProvider.retrieveObject(restClient.createObjectDataReader(ToDoItem.class));
        singleToDoItem.stateProperty().addListener((obs, ov, nv) -> {
            if (nv == ConnectState.SUCCEEDED) {
                System.out.println(singleToDoItem.get().getTitle());
            } else if (nv == ConnectState.FAILED) {
                singleToDoItem.getException().printStackTrace();
            }
        });
    }

    @FXML
    public void addToDoItem() {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setCompleted(Math.random() > 0.5);
        toDoItem.setTitle(UUID.randomUUID().toString());
        toDoItem.setUserId(1);

        // write a new todo item to a rest source
        RestClient restClient = RestClient.create()
                .method("POST")
                .contentType("application/json")
                .host("http://jsonplaceholder.typicode.com")
                .path("/todos");

        GluonObservableObject<ToDoItem> charmToDoItem = DataProvider.storeObject(toDoItem, restClient.createObjectDataWriter(ToDoItem.class));

        charmToDoItem.stateProperty().addListener((obs, ov, nv) -> {
            if (ConnectState.SUCCEEDED.equals(nv)) {
                LOG.log(Level.INFO, "ToDoItem successfully written.");
                toDoItems.getItems().add(charmToDoItem.get());
                toDoItems.scrollTo(charmToDoItem.get());
            } else if (ConnectState.FAILED.equals(nv)) {
                if (charmToDoItem.getException() != null) {
                    LOG.log(Level.SEVERE, null, charmToDoItem.getException());
                }
            }
        });
    }
}
