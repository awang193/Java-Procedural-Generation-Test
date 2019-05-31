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
        Group g = new Group();


        tree.loadMap(true);
        g.getChildren().add(drawDungeon(tree, 0));


        stage.setScene(new Scene(g));


        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static Canvas drawDungeon(BSPTree tree, int initX)
    {

        int x = initX;
        int y = 0;

        Canvas cnv = new Canvas(tree.getDungeonWidth() * 3 * TILE_WIDTH, tree.getDungeonHeight() * 3 * TILE_WIDTH);
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
                    case -98:
                        gc.setFill(Color.ROSYBROWN);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -99:
                        gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -10:
                        gc.setFill(Color.GRAY);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;

                }
                x += TILE_WIDTH;
            }
            x = initX;
            y += TILE_WIDTH;
        }

        return cnv;
    }
}