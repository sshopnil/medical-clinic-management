package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLSceneChanger
    {
        public static Parent root = null;
        public static Object controller = null;

        public static FXMLSceneChanger load (String fxmlPath)
            {
                FXMLSceneChanger sceneChanger = new FXMLSceneChanger();
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(sceneChanger.getClass().getResource(fxmlPath));

                try
                    {
                        sceneChanger.root = loader.load();
                        sceneChanger.controller = loader.getController();
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                return sceneChanger;
            }
    }
