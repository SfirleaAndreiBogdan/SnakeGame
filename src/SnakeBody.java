import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SnakeBody {
    public Rect[] body = new Rect[100];
    public double bodywidth , bodyheight;

    public int size;
    public int tail=0;
    public int head=0;

    public Directie direction=Directie.RIGHT;
    public double waitBetweenTheUd=0.1F;
    public double waitTimeLeft=waitBetweenTheUd;

    public Rect background;
    public SnakeBody(int size , double StartX , double StartY , double bodyWidth ,double bodyHeight , Rect background){
        this.size = size;
        this.bodywidth=bodyWidth;
        this.bodyheight=bodyHeight;
        this.background=background;

        for(int i=0;i<= size ; i++){
            Rect bodyPiece = new Rect(StartX+i*bodyWidth,StartY,bodyWidth,bodyHeight);
            body[i]=bodyPiece;
            head++;
        }
        head--;
    }

    public void changeDirec(Directie newDirectie){
        if (newDirectie==Directie.RIGHT && direction!=Directie.LEFT){
            direction=Directie.RIGHT;
        }else if (newDirectie==Directie.LEFT && direction!=Directie.RIGHT){
            direction=Directie.LEFT;
        }else if (newDirectie==Directie.UP && direction!=Directie.DOWN){
            direction=Directie.UP;
        }else if (newDirectie==Directie.DOWN && direction!=Directie.UP) {
            direction = Directie.DOWN;
        }else if (newDirectie==Directie.DOWN && direction!=Directie.LEFT) {
            direction = Directie.DOWN;
        }else if (newDirectie==Directie.LEFT && direction!=Directie.DOWN) {
            direction = Directie.LEFT;
        }else if (newDirectie==Directie.DOWN && direction!=Directie.RIGHT) {
            direction = Directie.DOWN;
        }else if (newDirectie==Directie.RIGHT && direction!=Directie.DOWN) {
            direction = Directie.RIGHT;
        }else if (newDirectie==Directie.UP && direction!=Directie.RIGHT) {
            direction = Directie.UP;
        }else if (newDirectie==Directie.RIGHT && direction!=Directie.UP) {
            direction = Directie.RIGHT;
        }

    }
    public void update(double dt){
        if(waitTimeLeft>0){
            waitTimeLeft-=dt;
            return;
        }

        if (intersectingWithSelf()){
            Window.getWindow().changeState(0);
        }

        waitTimeLeft=waitBetweenTheUd;
        double newX=0;
        double newY=0;
        if(direction==Directie.RIGHT){
            newX=body[head].x+bodywidth;
            newY=body[head].y;
        }else if(direction==Directie.LEFT){
            newX=body[head].x-bodywidth;
            newY=body[head].y;
        }else if(direction==Directie.UP){
            newX=body[head].x;
            newY=body[head].y-bodywidth;
        }else if(direction==Directie.DOWN){
            newX=body[head].x;
            newY=body[head].y+bodywidth;
        }


        body[(head+1)%body.length]=body[tail];
        body[tail]=null;
        head=(head+1)%body.length;
        tail=(tail+1)%body.length;

        body[head].x=newX;
        body[head].y=newY;
        
    }
    public boolean intersectingWithSelf(){
        Rect headR=body[head];
        for (int i=tail; i!=head-1; i=(i+1)%body.length){
                if (intersection(headR,body[i])){
                    return true || intersectingWithScreen(headR);
                }
        }
        return false || intersectingWithScreen(headR);
    }

    public boolean intersectingWithFood(Rect rect){

        for (int i=tail; i!=head; i=(i+1)%body.length){
            if (intersection(rect,body[i])){
                return true;
            }
        }
        return false;
    }
    public boolean intersection(Rect r1 , Rect r2){
        return (r1.x>=r2.x && r1.x+r1.width<= r2.x+ r2.width && r1.y>=r2.y && r1.y+r1.height<= r2.y+ r2.height);
    }

    public boolean intersectingWithScreen(Rect head){
        return (head.x < background.x || (head.x + head.width) > background.x + background.width || head.y < background.y || (head.y + head.height) > background.y + background.height);
    }
    public void grow(){
        double newX=0;
        double newY=0;

        if(direction==Directie.RIGHT){
            newX=body[tail].x-bodywidth;
            newY=body[tail].y;
        }else if(direction==Directie.LEFT){
            newX=body[tail].x+bodywidth;
            newY=body[tail].y;
        }else if(direction==Directie.UP){
            newX=body[tail].x;
            newY=body[tail].y+bodywidth;
        }else if(direction==Directie.DOWN){
            newX=body[tail].x;
            newY=body[tail].y-bodywidth;
        }
        Rect newBodyPiece = new Rect(newX,newY,bodywidth,bodyheight);
        tail=(tail-1)%body.length;
        body[tail]=newBodyPiece;
    }
    public void draw(Graphics2D g2){
        for (int i=tail; i!=head; i=(i+1)%body.length){
            Rect piece = body[i];
            double subWidth= (piece.width-6.0)/2.0;
            double subHeight= (piece.height-6.0)/2.0;

            g2.setColor(Color.GREEN);
            g2.fill(new Rectangle2D.Double(piece.x+2.0,piece.y+2.0,subWidth,subHeight));
            g2.fill(new Rectangle2D.Double(piece.x+4.0+subWidth,piece.y+2.0,subWidth,subHeight));
            g2.fill(new Rectangle2D.Double(piece.x+2.0,piece.y+4.0+subHeight,subWidth,subHeight));
            g2.fill(new Rectangle2D.Double(piece.x+4.0+subWidth,piece.y+4.0+subHeight,subWidth,subHeight));
        }
    }
}
