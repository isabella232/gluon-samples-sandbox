package com.gluonhq.charm.glisten.testbench.samples;
//package com.gluonhq.charm.glisten.testbench;
//
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
//
//import com.gluonhq.charm.glisten.application.MobileApplication;
//import com.gluonhq.charm.glisten.view.BaseViewController;
//
//public class ViewDemoPanel extends VBox {
//    
//    private DemoView view = new DemoView();
//    
//    public ViewDemoPanel() {
//        super(10);
//        
//        this.setPadding(new Insets(10));
//        view.registerAsView();
//        
//        Button switchPageButton = new Button("Go to other view");
//        switchPageButton.setOnAction(e -> view.show());
//        
////        MobileApplication.getInstance().getView(MobileApplication.DEFAULT_PAGE_NAME).ifPresent(view -> {
////            EventHandler<Event> e = event -> System.out.println(event);
////            view.addEventHandler(View.ON_SHOWN, e);
////            view.addEventHandler(View.ON_HIDDEN, e);
////            view.addEventHandler(View.ON_INIT, e);
////            view.addEventHandler(View.ON_DESTROY, e);
////        });
//        
//        this.getChildren().addAll(switchPageButton);
//    }
//    
//    @Override public String toString() {
//        return "View Switching";
//    }
//    
//    private class DemoView extends BaseViewController {
//
//        private BorderPane root = new BorderPane(); // ultimately can be loaded from fxml
//        
//        public DemoView() {
//            Button switchPageButton = new Button("Go home");
//            switchPageButton.setOnAction(e -> {
//                MobileApplication.getInstance().switchView(MobileApplication.DEFAULT_PAGE_NAME);
//            });
//            root.setCenter(switchPageButton);
//        }
//        
//        @Override
//        protected Node getRootNode() {
//            return root;
//        }
//        
//    }
//}
