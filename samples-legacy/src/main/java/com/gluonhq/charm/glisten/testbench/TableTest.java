package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.PopupView;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableTest extends MobileApplication {


    @Override
    public void init() {
        addViewFactory(HOME_VIEW, () -> {
            TableView<Dessert> tableView = new TableView<Dessert>();

            Dessert yogurt = new Dessert("Frozen Yogurt", 159, 6, 24);
            Dessert iceCream = new Dessert("Ice cream sandwich", 237, 9, 37);
            Dessert eclair = new Dessert("Eclair", 262, 16, 24);
            ObservableList<Dessert> desserts = FXCollections.observableArrayList(yogurt, iceCream, eclair);
            TableColumn<Dessert, String> nameCol = new TableColumn<Dessert, String>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory("name"));
            TableColumn<Dessert, String> caloriesCol = new TableColumn<Dessert, String>("Calories");
            caloriesCol.setCellValueFactory(new PropertyValueFactory<>("calories"));
            TableColumn<Dessert, String> fatCol = new TableColumn<Dessert, String>("Fat");
            fatCol.setCellValueFactory(new PropertyValueFactory<>("fat"));
            TableColumn<Dessert, String> carbsCol = new TableColumn<Dessert, String>("Carbs");
            carbsCol.setCellValueFactory(new PropertyValueFactory<>("carbs"));

            tableView.getColumns().addAll(nameCol, caloriesCol, fatCol, carbsCol);
            tableView.setItems(desserts);



            return new View(tableView) {



                @Override
                protected void updateAppBar(AppBar appBar) {
                    appBar.setTitleText("TableView");
                }
            };
        });

    }

    public class Dessert {

        public Dessert(String name, int calories, int fat, int carbs) {
            setName(name);
            setCalories(calories);
            setFat(fat);
            setCarbs(carbs);
        }

        private final StringProperty nameProperty = new SimpleStringProperty();
        public String getName() { return nameProperty.get(); }
        public void setName(String name) { nameProperty.set(name); }
        public StringProperty nameProperty() { return nameProperty; }

        private final IntegerProperty caloriesProperty = new SimpleIntegerProperty();
        public int getCalories() { return caloriesProperty.get(); }
        public void setCalories(int calories) { caloriesProperty.set(calories); }
        public IntegerProperty caloriesProperty() { return caloriesProperty; }

        private final IntegerProperty fatProperty = new SimpleIntegerProperty();
        public int getFat() { return fatProperty.get(); }
        public void setFat(int fat) { fatProperty.set(fat); }
        public IntegerProperty fatProperty() { return fatProperty; }

        private final IntegerProperty carbsProperty = new SimpleIntegerProperty();
        public int getCarbs() { return carbsProperty.get(); }
        public void setCarbs(int carbs) { carbsProperty.set(carbs); }
        public IntegerProperty carbsProperty() { return carbsProperty; }
    }
}
