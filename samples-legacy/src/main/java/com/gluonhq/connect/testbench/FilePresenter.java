package com.gluonhq.connect.testbench;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.charm.glisten.control.Chip;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.converter.JsonIterableInputConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.InputStreamListDataReader;
import com.gluonhq.connect.source.FileDataSource;
import com.gluonhq.connect.testbench.entity.VideoGame;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.*;

public class FilePresenter {

    @FXML
    ListView<VideoGame> videoGames;

    public void initialize() {
        try {
            StorageService storageService = StorageService.create()
                    .orElseThrow(() -> new IllegalStateException("Charm Attach Storage Service is not available. Please make sure that the 'storage' plugin is configured in the jfxmobile -> downConfig -> plugins section in the build.gradle file of your Gluon Mobile project."));


            File rootDir = storageService.getPrivateStorage()
                    .orElseThrow(() -> new IOException("Private storage file not available"));
        
            File videoGamesJson = new File(rootDir, "video_games.xml");
            if (!videoGamesJson.exists()) {
                try (FileWriter fileWriter = new FileWriter(videoGamesJson);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(FilePresenter.class.getResourceAsStream("videogames.json")))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        fileWriter.write(line);
                    }
                }
            }
 
            GluonObservableList<VideoGame> charmVideoGames = DataProvider.retrieveList(
                    new InputStreamListDataReader<>(new FileDataSource(new File(rootDir, "video_games.xml")), new JsonIterableInputConverter<>(VideoGame.class)));
            charmVideoGames.exceptionProperty().addListener((obs, ov, nv) -> {
                if (nv != null) {
                    nv.printStackTrace();
                }
            });

            videoGames.setCellFactory(lv -> new VideoGameListCell());

            videoGames.setItems(charmVideoGames);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static class VideoGameListCell extends ListCell<VideoGame> {

        ListTile tile = new ListTile();

        @Override
        protected void updateItem(VideoGame item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            if (empty) {
                setGraphic(null);
            } else {
                tile.textProperty().setAll(item.getName(), "Developer: " + item.getDeveloper(), "Year: " + String.valueOf(item.getYear()));
                tile.primaryGraphicProperty().set(new Chip(String.valueOf(item.getMetaScore())));
                setGraphic(tile);
            }
        }
    }
}
