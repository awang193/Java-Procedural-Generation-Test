import OLDAPPROACH.DungeonMap;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application
{
    @Override
    public void start(Stage stage)
    {
        DungeonMap newMap = new DungeonMap(20, 20, 1);
        newMap.initialize();

        System.gc();
        System.exit(0);
    }

    public static void main(String[] args)
    {
        launch(args);
    }




}