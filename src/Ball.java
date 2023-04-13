import java.awt.*;
import java.util.*;

public class Ball extends Rectangle implements Runnable{
    Random random;
    int xSpeed;
    int ySpeed;
    int moreSpeed = 7;
    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    public void setXDirection(int randomXDirection){
        xSpeed = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        ySpeed = randomYDirection;
    }

    public void move(){
        x += xSpeed;
        y += ySpeed;
    }
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x,y, height, width);
    }

    @Override
    public void run() {
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection*moreSpeed);
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomXDirection*moreSpeed);
        System.out.println("ball");
    }
}
