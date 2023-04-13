import java.awt.*;
import  java.awt.event.*;

public class Racket extends Rectangle implements Runnable{
    int id;
    int ySpeed;
    int speed = 10;
    Racket(int x, int y, int RACKET_WIDTH, int RACKET_HEIGHT, int id){
        super(x,y,RACKET_WIDTH, RACKET_HEIGHT);
        this.id=id;
    }
    public void keyPressed(KeyEvent e){
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    //move();
                    run();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection){
        ySpeed = yDirection;
    }
    public void move(){
        y += ySpeed;
    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.blue);
        }
        else g.setColor(Color.red);
        g.fillRect(x,y,width,height);
    }

    @Override
    public void run() {
    }
}
