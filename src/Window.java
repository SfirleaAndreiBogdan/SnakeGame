import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    public static   Window window=null;
    public boolean isRunning=true;

    public  int currentState;
    public  Scena scenaCurenta;

    public  Key keyListener=new Key();
    public  MouseAdap mouseListener=new MouseAdap();
    public Window(int width , int height , String title){
        setSize(width,height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        isRunning=true;
        changeState(0);
    }
    public static Window getWindow(){
        if(Window.window==null){
            Window.window= new Window(ConstatNumber.WIDTH,ConstatNumber.HEIGHT,ConstatNumber.TITLE);
        }
        return Window.window;
    }
    public void exit(){
        isRunning=false;

    }
    public void changeState(int newState){
        currentState=newState;
        switch (currentState){
            case 0:
                scenaCurenta=new Menu(keyListener,mouseListener);
                break;
            case 1:
                scenaCurenta=new GamePlay(keyListener);
                break;

            default:
                System.out.println("Scena in lucru...");
                scenaCurenta=null;
                break;
        }
    }


    public void update(double deltaTime){
    Image dvImage= createImage(getWidth(),getHeight());
    Graphics dbg= dvImage.getGraphics();
    this.draw(dbg);
    getGraphics().drawImage(dvImage,0,0,this);

    scenaCurenta.update(deltaTime);
    }

    public void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        scenaCurenta.draw(g);
    }


    public void run() {
        double lastFrameTime=0.0;
        try{
            while (isRunning){
                double time=Time.gettime();
                double deltaTime= time-lastFrameTime;
                lastFrameTime=time;

                update(deltaTime);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        this.dispose();
    }

}
