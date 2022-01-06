package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

public class SubSceneChanger
{
    private Pane view;
    public Pane getSubScene(String filename)
    {
        try
        {
            URL fileUrl = Main.class.getResource(filename);
            if(fileUrl == null)
            {
                throw new java.io.FileNotFoundException("FXML file could not be found!!");
            }
            view = new FXMLLoader().load(fileUrl);
        }
        catch (Exception e)
        {
            System.out.println("No page " + filename + " please check file path");
        }
        return view;
    }
}
