# Gluon Samples

Sandbox repository to test API changes made to Glisten and Glisten-Afterburner.

## Changes in Glisten framework

* `MobileApplication` has been deprecated, use `MobileApplicationManager` instead.
* There is no need to extend from `MobileApplication`, extending from `Application` should be enough.
 Make sure to instantiate `MobileApplicationManager` and call `MobileApplicationManager#start` from `Application#start`.

```
public class GluonApp extends Application {

    private MobileApplicationManager app = MobileApplicationManager.initialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }
    ...
}
```

* To add and switch views, use methods from `MobileApplicationManager` instead:

```
MobileApplicationManager.getInstance().addViewFactory(HOME, GluonView::new)
MobileApplicationManager.getInstance().switchView(HOME);
```

* To update get instances of AppBar and NavigationDrawer, use the following methods:

```
MobileApplicationManager.getInstance().getAppBar()
MobileApplicationManager.getInstance().getDrawer();
```

## Changes in Glisten Afterburner framework

1. Presenter/Controller class no longer needs to extend `GluonPresenter`, which has been deprecated
2. `AppView#registerView` no longer needs an instance of `MobileApplication`

## Build and run the samples

Visit each sample's README for further instructions.

## Issues and Contributions

Issues can be reported to the https://github.com/gluonhq/gluon-samples/issues[Issue tracker]

Contributions can be submitted via https://github.com/gluonhq/gluon-samples/pulls[Pull requests],
providing you have signed the https://cla.gluonhq.com[Gluon Individual Contributor License Agreement (CLA)].
