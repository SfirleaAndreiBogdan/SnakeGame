import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GamePlay extends Scena{
    Rect background,foreground;
    SnakeBody snake;
    Key keyListener;

    public Food food;

    public GamePlay(Key keyListener){
        background=new Rect(0,0,ConstatNumber.WIDTH,ConstatNumber.HEIGHT);
        foreground=new Rect(24,48,ConstatNumber.TITLE_WIDTH*31,ConstatNumber.TITLE_WIDTH*22);
        snake=new SnakeBody(3,48,48+24,24,24, foreground);
        this.keyListener=keyListener;
        food = new Food(foreground,snake, 12,12,Color.RED);
        food.generatingfood();
    }
    @Override
    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)){
            snake.changeDirec(Directie.UP);
        }else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
            snake.changeDirec(Directie.DOWN);
        }else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            snake.changeDirec(Directie.RIGHT);
        }else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)){
            snake.changeDirec(Directie.LEFT);
        }
        if (!food.isGenerated){
            food.generatingfood();
        }
            food.update(dt);
            snake.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(background.x,background.y,background.width,background.height));
        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(foreground.x,foreground.y,foreground.width,foreground.height));
        snake.draw(g2);
        food.draw(g2);
    }
}
