import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class GUI extends Application
{
    private static final int TILE_WIDTH = 32;

    private boolean camUp, camDown, camLeft, camRight;
    private double camX = 0, camY = 0, camW = 800, camH = 800;

    public Canvas drawDungeon(BSPTree btree, int initX)
    {
        int x = initX;
        int y = 0;

        Canvas cnv = new Canvas(btree.getDungeonWidth() * TILE_WIDTH, btree.getDungeonHeight() * TILE_WIDTH);
        GraphicsContext gc = cnv.getGraphicsContext2D();

        int[][] map = btree.getTileMap();

        // Draw tiles
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

    @Override
    public void start(Stage stage)
    {
        BSPTree tree = new BSPTree(20, 20);
        tree.loadMap();

        Group g = new Group();
        Scene scene = new Scene(g, 800, 800);

        Character character = new Character(tree, scene.getWidth() / 2, scene.getHeight() / 2, 1, 100, 10);

        Canvas canvas = drawDungeon(tree, 0);
        Canvas canvas2 = new Canvas(scene.getWidth(), scene.getHeight());

        GraphicsContext gc = canvas2.getGraphicsContext2D();

        gc.setFill(Color.AQUAMARINE);
        gc.fillRect(character.getX(), character.getY(), 32, 32);

        g.getChildren().addAll(canvas, canvas2);

        // On keypress
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case W:
                        System.out.println("W pressed");
                        camUp = true;
                        break;
                    case A:
                        System.out.println("A pressed");
                        camLeft = true;
                        break;
                    case S:
                        System.out.println("S pressed");
                        camDown = true;
                        break;
                    case D:
                        System.out.println("D pressed");
                        camRight = true;
                        break;
                    default:
                        break;
                }

                System.out.println("Up: " + camUp + " Down: " + camDown + " Left: " + camLeft + " Right: " + camRight);
            }
        });

        // On key release
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case W:
                        System.out.println("W released");
                        camUp = false;
                        break;
                    case A:
                        System.out.println("A released");
                        camLeft = false;
                        break;
                    case S:
                        System.out.println("S released");
                        camDown = false;
                        break;
                    case D:
                        System.out.println("D released");
                        camRight = false;
                        break;
                    default:
                        break;
                }

                System.out.println("Up: " + camUp + " Down: " + camDown + " Left: " + camLeft + " Right: " + camRight);
            }
        });

        // Animation to move camera
        AnimationTimer animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {

                double delta = TILE_WIDTH / 4;

                BSPLeaf r = tree.getRoot();
                ArrayList<BSPLeaf> test = r.getLeaves();

                character.scanRooms(test);

                if (camUp)
                {
                    camY += delta;
                    character.move('N', delta);
                }

                if (camDown)
                {
                    camY -= delta;
                    character.move('S', delta);
                }

                if (camLeft)
                {
                    camX += delta;
                    character.move('W', delta);
                }

                if (camRight)
                {
                    camX -= delta;
                    character.move('E', delta);
                }

                canvas.setTranslateX(camX);
                canvas.setTranslateY(camY);
            }
        };

        animationTimer.start();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}