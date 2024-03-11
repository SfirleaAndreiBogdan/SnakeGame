import java.awt.*;

public class Food {

    public Rect background;
    public SnakeBody snake;

    public int width,height;
    public Color color;

    public int xPadding;
    public Rect rect;
    public boolean isGenerated;
    public Food(Rect background , SnakeBody snake,int width,int height,Color color){
            this.background=background;
            this.snake=snake;
            this.color=color;
            this.height=height;
            this.width=width;
            this.rect = new Rect(0,0,width,height);

            xPadding=(int)((ConstatNumber.TITLE_WIDTH-this.width)/2.0);
    }
    public void generatingfood(){
        do {
                double randomX=(int)(Math.random()*(int)(background.width/ConstatNumber.TITLE_WIDTH))*ConstatNumber.TITLE_WIDTH+ background.x;
                double randomY=(int)(Math.random()*(int)(background.height/ConstatNumber.TITLE_WIDTH))*ConstatNumber.TITLE_WIDTH+ background.y;

                this.rect.x=randomX;
                this.rect.y=randomY;
        }while (snake.intersectingWithFood(this.rect));

        this.isGenerated=true;
    }

    public void update(double dt){
        if (snake.intersectingWithFood(this.rect)){
            snake.grow();
            this.rect.x=-100;
            this.rect.y=-100;
            isGenerated=false;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillRect((int)this.rect.x+xPadding,(int)this.rect.y+xPadding,width,height);
    }
}
