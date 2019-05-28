import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application
{
    private static final int TILE_WIDTH = 6;

    @Override
    public void start(Stage stage)
    {
        BSPTree tree = new BSPTree(100, 100);
        tree.loadMap(true);

        Group g = new Group(drawDungeon(tree));

        stage.setScene(new Scene(g));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static Canvas drawDungeon(BSPTree tree)
    {

        int x = 0;
        int y = 0;

        Canvas cnv = new Canvas(tree.getDungeonWidth() * TILE_WIDTH, tree.getDungeonHeight() * TILE_WIDTH);
        GraphicsContext gc = cnv.getGraphicsContext2D();

        int[][] map = tree.getTileMap();

        for (int r = 0; r < map.length; r++)
        {
            for (int c = 0; c < map[r].length; c++)
            {
                switch (map[r][c])
                {
                    case -1:
                        gc.setFill(Color.BLUE);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -2:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -3:
                        gc.setFill(Color.LIGHTBLUE);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -4:
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -10:
                        gc.setFill(Color.GRAY);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;

                }
                x += TILE_WIDTH;
            }
            x = 0;
            y += TILE_WIDTH;
        }

        return cnv;
    }
}