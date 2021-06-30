package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.SettingsPane;
import com.gluonhq.charm.glisten.control.settings.DefaultOption;
import com.gluonhq.charm.glisten.control.settings.Option;
import com.gluonhq.charm.glisten.control.settings.OptionBase;
import com.gluonhq.charm.glisten.control.settings.OptionEditor;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.testbench.samples.settings.CheckBoxEditor;
import com.gluonhq.charm.glisten.testbench.samples.settings.ComboBoxEditor;
import com.gluonhq.charm.glisten.testbench.samples.settings.SliderEditor;
import com.gluonhq.charm.glisten.testbench.samples.settings.SpinnerEditor;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public class SettingsDemoPanel extends BorderPane implements Sample {

    enum OS { WINDOWS, MAC, LINUX, OTHER }
    
    private final Person person;
    
    public SettingsDemoPanel() {
//        Locale.setDefault(Locale.US);
//        Locale.setDefault(new Locale("es","ES"));


        person = new Person("Jose", "Jose", 1.90, true, 30, 4, "Spain", 4123413413241L, 
                LocalDate.of(1987,6,5), Color.BURLYWOOD, OS.MAC, "PHONE_ANDROID", true);
        
        person.nameProperty().addListener((obs, ov, nv) -> System.out.println("Person new name: " + nv));
        person.heightProperty().addListener((obs, ov, nv) -> System.out.println("Person new height: " + nv));
        person.maleProperty().addListener((obs, ov, nv) -> System.out.println("Person is male: " + nv));
        person.ageProperty().addListener((obs, ov, nv) -> System.out.println("Person new age: " + nv));
        person.kidsProperty().addListener((obs, ov, nv) -> System.out.println("Person new kids: " + nv));
        person.countryProperty().addListener((obs, ov, nv) -> System.out.println("Person new country: " + nv));
        person.timestampProperty().addListener((obs, ov, nv) -> System.out.println("Person new timestamp: " + nv));
        person.birthdayProperty().addListener((obs, ov, nv) -> System.out.println("Person new birthday: " + nv));
        person.colorProperty().addListener((obs, ov, nv) -> System.out.println("Person new color: " + nv));
        person.OSProperty().addListener((obs, ov, nv) -> System.out.println("Person new OS: " + nv));
        person.deviceProperty().addListener((obs, ov, nv) -> System.out.println("Person new device: " + nv));
        person.wifiProperty().addListener((obs, ov, nv) -> System.out.println("Person uses wifi: " + nv));
        
        /**
         * REGULAR OPTIONS
         */
        // Case 1: generate defaults options, with default editors

        // wrap primitives
        final Option<String> textOption = new DefaultOption(
                "Name", "Set the name", null, person.getOldName(), true);

        // or use JavaFX properties (preferred way)
        final Option<String> textOption2 = new DefaultOption(
                "Name 2", "Set the name", null, person.nameProperty(), true);
        
        final Option<Double> heightOption = new DefaultOption(
                MaterialDesignIcon.BORDER_VERTICAL.graphic(), "Height", "Set the height", "Icon", 
                person.heightProperty(), true);
        
        // Case 2: generate defaults options with custom editors provided by developer
        final Option<Double> heightOption2 = new DefaultOption(
                MaterialDesignIcon.BORDER_VERTICAL.graphic(), "Height 2", "Set the height with the slider", "Icon", 
                OptionBase.valueProperty(1.90), true, 
                option -> new SliderEditor((Option<Number>) option, 0.0, 2.50));
        // listening to changes in option value:
        heightOption2.valueProperty().addListener((o, ov, nv) -> {
            // reflect change in model -> it will be reflected as well in heightOption
            person.setHeight(nv);
        });
        
        final DefaultOption<Long> timestampOption = new DefaultOption(
                MaterialDesignIcon.TIME_TO_LEAVE.graphic(), "Timestamp", "Set the timestamp", "Icon",
                person.timestampProperty(), true);
        timestampOption.setLayout(Orientation.VERTICAL);
        
        final Option<Integer> childrenOption = new DefaultOption(
                MaterialDesignIcon.CHILD_CARE.graphic(), "Children", "Set the children", "Icon", 
                person.kidsProperty(), true);
        
        // Case 3: generate custom options provided by developer, using default editors
        final Option<String> customOption = new CustomOption("Name", "Set the name", null, 
                new SimpleStringProperty("Jose"), true);
        
        // Case 4: generate custom options provided by developer, using custom editor provided by developer
        final Option<Number> spinOption = new NumberOption(
                MaterialDesignIcon.CAKE.graphic(), "Age", "Set the age in the textfield", "Icon", person.ageProperty(), 
                true, new IntegerSpinnerValueFactory(0, 100));
        
        final ObservableList<String> countries = FXCollections.observableArrayList("Belgium", "India", 
                "New Zealand", "Portugal", "Spain", "US");
        
        final Option<String> spinListOption = new ListOption(
                MaterialDesignIcon.LOCATION_CITY.graphic(), "Country", "Set the country in the spinner", "Icon",
                person.countryProperty(), true, new ListSpinnerValueFactory<>(countries));
        
        final Option<Number> sliderOption = new SliderOption(
                MaterialDesignIcon.BORDER_HORIZONTAL.graphic(), "Age 2", "Set the age in the slider", "Icon", 
                new SimpleDoubleProperty(30.0), true, 0.0, 100.0);
        
        /**
         *  EXTENDED OPTIONS
         */
        final DefaultOption<Boolean> checkOption = new DefaultOption("Gender", "Set the gender", null, 
                person.maleProperty(), true);
        String extended = "Settings that require longer explanations may add a description on a second screen. \n\n"
                + "Provide a long description here.";
        checkOption.setExtendedDescription(extended);
        checkOption.setStringConverter(new StringConverter<Boolean>(){
            @Override public String toString(Boolean object) {
                return object ? "Male" : "Female";
            }
            @Override public Boolean fromString(String string) {
                return string.equals("Male");
            }
        });
        
        /**
         *  GROUPS OF OPTIONS
         */
        // group 2
        Option<String> groupOption2 = new DefaultOption(MaterialDesignIcon.DEVICES_OTHER.graphic(), 
                "Level 2", "Set different device options in a group", null);
        final Option<OS> OSOption = new DefaultOption(MaterialDesignIcon.LAPTOP.graphic(), "Operative System", 
                "Set the preferred OS", "Operative System",  person.OSProperty(), true);
        
        final ObservableList<String> devices = FXCollections.observableArrayList(
                "PHONE_ANDROID", "TABLET_ANDROID", 
                "PHONE_IPHONE", "TABLET_MAC", 
                "SMARTPHONE");
        
        groupOption2.getChildren().setAll(
                new ComboBoxOption(MaterialDesignIcon.DEVICES.graphic(), "Device", "Set the favorite device", "Devices", person.deviceProperty(), true, devices),
                new DefaultOption(MaterialDesignIcon.WIFI.graphic(), "WiFi", "Set Wifi or Wire", "Devices", person.wifiProperty(), true, 
                        option -> new CheckBoxEditor((Option<Boolean>) option)), 
                new DefaultOption(Option.SEPARATOR), 
                OSOption);
        
        // group 1
        Option<String> groupOption = new DefaultOption<>("Level 1", "Set different options in a group", null);
        groupOption.getChildren().setAll(
                new DefaultOption(MaterialDesignIcon.DATE_RANGE.graphic(), "Birthday", "Set the birthday", "Personal", person.birthdayProperty(), true),
                new DefaultOption(MaterialDesignIcon.PALETTE.graphic(), "Color", "Set the favorite color", "Personal", person.colorProperty(), true),
                new DefaultOption(Option.SEPARATOR),
                groupOption2
        );
        
        /**
         * SETTINGS PANE
         */

        SettingsPane settings = new SettingsPane(
            FXCollections.<Option>observableArrayList(
                    groupOption,
                    customOption, 
                    checkOption, textOption, textOption2,  
                    new DefaultOption(Option.SEPARATOR),
                    heightOption, heightOption2, timestampOption, childrenOption, spinListOption,
                    spinOption, sliderOption
            ));
//        settings.setSearchBoxVisible(true);
        setCenter(settings);
     
        
// Case 5: custom OptionEditorFactory: develper provides its own editor factory
// Note: all properties should find a proper editor 

//        settings.setOptionEditorFactory(option -> {
//            
//            return new OptionEditorBase<String, TextField>(option, new TextField()) {
//                
//                private final OptionBase<String> stringOp;
//                {
//                    stringOp = new DefaultOption(option.getCaption(), option.getDescription(), 
//                            option.getCategory(), String.class, option.getValue().getClass().toString(), true);
//                }
//
//                @Override public StringProperty valueProperty() {
//                    return getEditor().textProperty();
//                }
//
//                @Override public void setValue(String value) {
//                    getEditor().setText(value);
//                }
//
//            };
//        });
                
    }

    @Override
    public String toString() {
        return "Settings";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.SETTINGS.graphic();
    }
    
    /**
     * Custom implementation of OptionBase formatting
     */
    class CustomOption extends OptionBase<String> {
        
        public CustomOption(String caption, String description, String category, StringProperty value, boolean isEditable) {
            super(caption, description, category, value, isEditable);
        }

        @Override
        public String getCaption() {
            return "Custom " + caption;
        }

        @Override
        public String getDescription() {
            return "Custom " + description;
        }

        @Override
        public Property<String> valueProperty() {
            return value;
        }

    }
    
    /**
     * Custom implementation of OptionBase using a SpinnerEditor, a Spinner control for Integer or Double values
     */
    class NumberOption extends OptionBase<Number> {
        
        private final SpinnerValueFactory spinnerValueFactory;
        
        public NumberOption(Node graphic, String caption, String description, String category, IntegerProperty value, 
                boolean isEditable, SpinnerValueFactory spinnerValueFactory) {
            super(graphic, caption, description, category, (Property<Number>) value, isEditable);
            
            this.spinnerValueFactory = spinnerValueFactory;
        }
        
        @Override
        public Property<Number> valueProperty() {
            return value;
        }

        @Override
        public Optional<Function<Option<Number>, OptionEditor<Number>>> editorFactoryProperty() {
            return Optional.of(option -> new SpinnerEditor<>(option, spinnerValueFactory));
        }
        
    }
    
    /**
     * Custom implementation of OptionBase using a SpinnerEditor, a Spinner control for a List<> of values
     */
    class ListOption extends OptionBase<String> {
        
        private final ListSpinnerValueFactory<String> spinnerValueFactory;
        
        public ListOption(Node graphic, String caption, String description, String category, StringProperty value, 
                boolean isEditable, ListSpinnerValueFactory<String> spinnerValueFactory) {
            super(graphic, caption, description, category, value, isEditable);
            
            this.spinnerValueFactory = spinnerValueFactory;
        }
        
        @Override
        public Property<String> valueProperty() {
            return value;
        }

        @Override
        public Optional<Function<Option<String>, OptionEditor<String>>> editorFactoryProperty() {
            return Optional.of(option -> new SpinnerEditor<>(option, spinnerValueFactory));
        }
        
    }
    
    /**
     * Custom implementation of OptionBase using a SliderEditor, a Slider control
     */
    class SliderOption extends OptionBase<Number> {

        double min, max;
        
        public SliderOption(Node graphic, String caption, String description, String category, DoubleProperty value, 
                boolean isEditable, double min, double max) {
            super(graphic, caption, description, category, (Property<Number>) value, isEditable);
            this.min = min;
            this.max = max;
        }
        
        @Override
        public Property<Number> valueProperty() {
            return value;
        }


        @Override
        public Optional<Function<Option<Number>, OptionEditor<Number>>> editorFactoryProperty() {
            return Optional.of(option -> new SliderEditor(option, min, max));
        }
        
    }
    
    /**
     * Custom implementation of OptionBase using a SliderEditor, a Slider control
     */
    class ComboBoxOption extends OptionBase<String> {

        Collection<String> items;
        
        public ComboBoxOption(Node graphic, String caption, String description, String category, StringProperty value, 
                boolean isEditable, Collection<String> items) {
            super(graphic, caption, description, category, value, isEditable);
            this.items = items;
        }
        
        @Override
        public Property<String> valueProperty() {
            return value;
        }

        @Override
        public Optional<Function<Option<String>, OptionEditor<String>>> editorFactoryProperty() {
            return Optional.of(option -> new ComboBoxEditor(option, items));
        }
        
    }
    
    class Person {
        
        // JavaBean with primitives and properties
        public Person(String oldName, String name, double height, boolean male, int age, int kids, 
                String country, long timestamp, LocalDate birthday, Color color, OS os, String device, boolean wifi) {
            this.oldName = oldName;
            this.name = new SimpleStringProperty(name);
            this.height = new SimpleDoubleProperty(height);
            this.male = new SimpleBooleanProperty(male);
            this.age = new SimpleIntegerProperty(age);
            this.kids = new SimpleIntegerProperty(kids);
            this.country = new SimpleStringProperty(country);
            this.timestamp = new SimpleLongProperty(timestamp);
            this.birthday = new SimpleObjectProperty<>(birthday);
            this.color = new SimpleObjectProperty<>(color);
            this.os = new SimpleObjectProperty<>(os);
            this.device = new SimpleStringProperty(device);
            this.wifi = new SimpleBooleanProperty(wifi);
        }
        
        // primitives
        private String oldName;

        public String getOldName() {
            return oldName;
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }
        
        // properties
        private final StringProperty name;
        private final DoubleProperty height;
        private final BooleanProperty male;
        private final IntegerProperty age;
        private final IntegerProperty kids;
        private final StringProperty country;
        private final LongProperty timestamp;
        private final ObjectProperty<Color> color;
        private final ObjectProperty<LocalDate> birthday;
        private final ObjectProperty<OS> os;
        private final StringProperty device;
        private final BooleanProperty wifi;
        
        public final void setName(String value) {
            name.set(value);
        }

        public final String getName() {
            return name.get();
        }

        public final StringProperty nameProperty() {
            return name;
        }

        public final void setHeight(Double value) {
            height.set(value);
        }

        public final Double getHeight() {
            return height.get();
        }

        public final DoubleProperty heightProperty() {
            return height;
        }

        public final void setMale(Boolean value) {
            male.set(value);
        }

        public final Boolean getMale() {
            return male.get();
        }

        public final BooleanProperty maleProperty() {
            return male;
        }

        public final void setAge(Integer value) {
            age.set(value);
        }

        public final Integer getAge() {
            return age.get();
        }

        public final IntegerProperty ageProperty() {
            return age;
        }

        public final void setKids(Integer value) {
            kids.set(value);
        }

        public final Integer getKids() {
            return kids.get();
        }

        public final IntegerProperty kidsProperty() {
            return kids;
        }

        public final void setCountry(String value) {
            country.set(value);
        }

        public final String getCountry() {
            return country.get();
        }

        public final StringProperty countryProperty() {
            return country;
        }

        public final void setTimestamp(Long value) {
            timestamp.set(value);
        }

        public final Long getTimestamp() {
            return timestamp.get();
        }

        public final LongProperty timestampProperty() {
            return timestamp;
        }

        public final void setDevice(String value) {
            device.set(value);
        }

        public final String getDevice() {
            return device.get();
        }

        public final StringProperty deviceProperty() {
            return device;
        }

        public final void setWifi(Boolean value) {
            wifi.set(value);
        }

        public final Boolean getWifi() {
            return wifi.get();
        }

        public final BooleanProperty wifiProperty() {
            return wifi;
        }
        public final void setColor(Color value) {
            color.set(value);
        }

        public final Color getColor() {
            return color.get();
        }

        public final ObjectProperty<Color> colorProperty() {
            return color;
        }

        public final void setBirthday(LocalDate value) {
            birthday.set(value);
        }

        public final LocalDate getBirthday() {
            return birthday.get();
        }

        public final ObjectProperty<LocalDate> birthdayProperty() {
            return birthday;
        }
        
        public final void setOS(OS value) {
            os.set(value);
        }

        public final OS getOS() {
            return os.get();
        }

        public final ObjectProperty<OS> OSProperty() {
            return os;
        }
        
    }

}
