import SUPEROLDAPPROACH.DungeonTree;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application
{
    @Override
    public void start(Stage stage) throws InterruptedException
    {
        BSPTree tree = new BSPTree(50, 50, 3);
        System.gc();
        System.exit(0);
    }

    public static void main(String[] args)
    {
        launch(args);
    }




}