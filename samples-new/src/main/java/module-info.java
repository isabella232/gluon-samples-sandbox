module com.gluonhq.charm.samples {
    // glisten
    requires javafx.controls;
    requires com.gluonhq.charm.glisten;
    requires com.gluonhq.attach.display;
    requires java.logging;

    exports com.gluonhq.charm.glisten.testbench;

    // afterburner
    requires javafx.fxml;
    requires com.gluonhq.glisten.afterburner;
    requires java.annotation;
    requires afterburner.mfx;

    opens com.gluonhq.glisten.afterburner.testbench.service to afterburner.mfx;
    opens com.gluonhq.glisten.afterburner.testbench.views to com.gluonhq.glisten.afterburner,
            afterburner.mfx, javafx.fxml;

    exports com.gluonhq.glisten.afterburner.testbench;

    // connect
    requires com.gluonhq.cloudlink.client;
    requires com.gluonhq.attach.storage;
//    requires com.gluonhq.charm.glisten.connect.view;
    requires javafx.web;

    opens com.gluonhq.connect.testbench to javafx.fxml, com.gluonhq.connect, com.gluonhq.charm.glisten.connect.view;
    opens com.gluonhq.connect.testbench.entity to com.gluonhq.cloudlink.client, com.gluonhq.connect;
    exports com.gluonhq.connect.testbench;

}