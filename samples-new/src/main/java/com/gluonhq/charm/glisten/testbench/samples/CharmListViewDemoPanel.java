package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.GlistenStyleClasses;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CharmListViewDemoPanel extends BorderPane implements Sample {

    public CharmListViewDemoPanel() {

        final ObservableList<Session> sessionList = Session.getSessionList();

        CharmListView<Session, Long> headerListView = new CharmListView<>();
//        headerListView.setPlaceholder(new Label("no items yet"));
        headerListView.setItems(sessionList);
//        headerListView.setItems(new FilteredList<>(sessionList,s->!s.getSpeaker().contains("1")));
        // sort by sessions first, then by speakers
        headerListView.setComparator((o1, o2) -> {
            if (o1.getSession().equals(o2.getSession())) {
                return o1.getSpeaker().compareTo(o2.getSpeaker());
            }
            return o1.getSession().compareTo(o2.getSession());
        });

        headerListView.setHeadersFunction(s -> s.getDate());
        // reverse order by dates
        headerListView.setHeaderComparator((d1, d2) -> d2.compareTo(d1));
        headerListView.setConverter(Session.converter);

        /*
        Cell factory for Regular Cells
        */
        headerListView.setCellFactory(p -> new CharmListCell<Session>() {

            @Override
            public void updateItem(Session item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    VBox buttons = new VBox(MaterialDesignIcon.STAR_BORDER.graphic());
                    buttons.setMaxHeight(Double.MAX_VALUE);
                    buttons.setAlignment(Pos.TOP_RIGHT);

                    ListTile tile = new ListTile();
                    tile.textProperty().setAll(item.toString());
                    tile.setSecondaryGraphic(buttons);
                    tile.setPrefHeight(40);
                    tile.setOnMouseClicked(e -> buttons.getChildren().setAll(MaterialDesignIcon.STAR.graphic()));

                    setText(null);
                    setGraphic(tile);
                } else {
                    setText(null);
                    setGraphic(null);
                }

            }

        });

        headerListView.setOnPullToRefresh(event -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    sessionList.addAll(new Session(1445952955165l,"Session Title 93","Speaker 133"));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /*
        Cell factory for Regular Cells
        */
//        headerListView.setHeaderCellFactory(p -> new CharmListCell<Session>() {
//
//            @Override
//            public void updateItem(Session item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item != null && !empty) {
//                    Label label = new Label();
//                    // use built in toSring based on provided function and string converter
//                    label.setText(headerListView.toString(item));
//                    // or do it yourself:
////                    label.setText(new SimpleDateFormat("MMMM dd'th'",Locale.ENGLISH).format(item.getDate()));
//                    HBox hBox = new HBox(5, new Circle(4, Color.RED), label);
//                    hBox.setPrefHeight(24);
//                    hBox.setAlignment(Pos.CENTER_LEFT);
//                    setText(null);
//                    setGraphic(hBox);
//                } else {
//                    setText(null);
//                    setGraphic(null);
//                }
//
//            }
//
//        });


        ToggleButton visible = new ToggleButton("Floating");
        visible.getStyleClass().add(GlistenStyleClasses.BUTTON_FLAT);
        visible.setSelected(headerListView.isFloatingHeaderVisible());
        visible.setOnAction(e->headerListView.setFloatingHeaderVisible(visible.isSelected()));



        Button add = new Button("Add");
        add.getStyleClass().add(GlistenStyleClasses.BUTTON_FLAT);
        add.setOnAction(e->sessionList.addAll(new Session(1445952955165l,"Session Title 93","Speaker 133")));
        Button remove = new Button("Remove");
        remove.getStyleClass().add(GlistenStyleClasses.BUTTON_FLAT);
        remove.setOnAction(e->sessionList.remove(20));
        Button scroll = new Button("ScrollTo");
        scroll.getStyleClass().add(GlistenStyleClasses.BUTTON_FLAT);
        scroll.setOnAction(e->headerListView.scrollTo(sessionList.get(20)));
        Button cell = new Button("Cell");
        cell.getStyleClass().add(GlistenStyleClasses.BUTTON_FLAT);
        cell.setOnAction(e->headerListView.setHeaderCellFactory(p->new CharmListCell<Session>(){

            @Override
            public void updateItem(Session item, boolean empty) {
                super.updateItem(item, empty);
                if(item!=null && !empty){
                    Label label = new Label();
                    // use built in toSring based on provided function and string converter
                    label.setText(headerListView.toString(item));
                    // or do it yourself:
//                    label.setText(new SimpleDateFormat("MMMM dd'th'",Locale.ENGLISH).format(item.getDate()));
                    HBox hBox = new HBox(5, new Circle(4, Color.GREEN), label);
                    hBox.setPrefHeight(24);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    setText(null);
                    setGraphic(hBox);
                } else {
                    setText(null);
                    setGraphic(null);
                }

            }

        }));

        HBox bar = new HBox(10, visible, add, remove, scroll, cell );
        bar.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-style: solid; " +
                "-fx-border-color: silver transparent transparent transparent;");
        setCenter(headerListView);
        setBottom(bar);
        setStyle("-fx-padding: 10;");

    }

    @Override public String toString() {
        return "CharmListView";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.VIEW_LIST.graphic();
    }
}


class Session {


    private static ObservableList<Session> sessionList;

    public static ObservableList<Session> getSessionList() {
        return sessionList;
    }

    public static StringConverter<Long> converter = new StringConverter<Long>(){

        @Override
        public String toString(Long object) {
            return new SimpleDateFormat("MMMM dd'th'", Locale.ENGLISH).format(new Date(object));
        }

        @Override
        public Long fromString(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }




    };

    static {
        sessionList = FXCollections.observableArrayList(
                new Session(1445866531171l, "Session Title 12", "Speaker 21"),
                new Session(1445866531171l, "Session Title 13", "Speaker 24"),
                new Session(1445866531171l, "Session Title 14", "Speaker 27"),
                new Session(1445780006925l, "Session Title 2", "Speaker 1"),
                new Session(1446125833871l, "Session Title 31", "Speaker 13"),
                new Session(1445780006925l, "Session Title 3", "Speaker 33"),
                new Session(1445866531171l, "Session Title 15", "Speaker 22"),
                new Session(1445780006925l, "Session Title 1", "Speaker 3"),
                new Session(1445780006925l, "Session Title 4", "Speaker 2"),
                new Session(1446125833871l, "Session Title 32", "Speaker 30"),
                new Session(1446039392481l, "Session Title 27", "Speaker 44"),
                new Session(1446039392481l, "Session Title 28", "Speaker 45"),
                new Session(1446039392481l, "Session Title 29", "Speaker 49"),
                new Session(1445780006925l, "Session Title 6", "Speaker 7"),
                new Session(1445780006925l, "Session Title 7", "Speaker 8"),
                new Session(1445780006925l, "Session Title 8", "Speaker 10"),
                new Session(1445780006925l, "Session Title 9", "Speaker 12"),
                new Session(1445952955165l, "Session Title 18", "Speaker 30"),
                new Session(1445952955165l, "Session Title 19", "Speaker 34"),
                new Session(1445866531171l, "Session Title 10", "Speaker 13"),
                new Session(1445866531171l, "Session Title 11", "Speaker 23"),
                new Session(1445866531171l, "Session Title 16", "Speaker 43"),
                new Session(1445866531171l, "Session Title 17", "Speaker 29"),
                new Session(1445780006925l, "Session Title 5", "Speaker 4"),
                new Session(1445952955165l, "Session Title 20", "Speaker 32"),
                new Session(1445952955165l, "Session Title 23", "Speaker 36"),
                new Session(1446039392481l, "Session Title 24", "Speaker 41"),
                new Session(1445952955165l, "Session Title 21", "Speaker 31"),
                new Session(1445952955165l, "Session Title 22", "Speaker 30"),
                new Session(1446039392481l, "Session Title 25", "Speaker 42"),
                new Session(1446039392481l, "Session Title 26", "Speaker 47"),
                new Session(1446039392481l, "Session Title 30", "Speaker 46"),
                new Session(1446125833871l, "Session Title 33", "Speaker 32"),
                new Session(1446125833871l, "Session Title 34", "Speaker 23")
        );
    }

    public Session(){
        this(0l,"","");
    }

    public Session(long date, String session, String speaker) {
        this.dateProperty = new SimpleLongProperty(this, "date", date);
        this.sessionProperty = new SimpleStringProperty(this, "session", session);
        this.speakerProperty = new SimpleStringProperty(this, "speaker", speaker);
    }

    private final LongProperty dateProperty;
    public final LongProperty dateProperty() { return dateProperty; }
    public Long getDate() { return dateProperty.get(); }
    public void setDate(Long newDate) { dateProperty.set(newDate); }

    private final StringProperty sessionProperty;
    public final StringProperty sessionProperty() { return sessionProperty; }
    public String getSession() { return sessionProperty.get(); }
    public void setSession(String newSession) { sessionProperty.set(newSession); }

    private final StringProperty speakerProperty;
    public final StringProperty speakerProperty() { return speakerProperty; }
    public String getSpeaker() { return speakerProperty.get(); }
    public void setSpeaker(String newSpeaker) { speakerProperty.set(newSpeaker); }

    @Override
    public String toString(){
        return getSession()+" ("+getSpeaker()+" - "+converter.toString(getDate())+")";
    }

}
