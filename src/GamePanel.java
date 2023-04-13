import javax.swing.*;
import java.awt.*;
import  java.awt.event.*;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*0.5555);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_SIZE = 15;
    static final int RACKET_WIDTH = 25;
    static final int RACKET_HEIGHT = 100;
    static final int totalScore = 3;

    Image image;
    Graphics graphics;

    Racket racket1;
    Racket racket2;
    Ball ball;
    Score score;

    Thread gameThread;
    Thread BallThread;
    Thread Racket1Thread;
    Thread Racket2Thread;

    GamePanel(){
        newRackets();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        ball = new Ball((GAME_WIDTH/2 - BALL_SIZE/2), (GAME_HEIGHT/2-BALL_SIZE/2), BALL_SIZE,BALL_SIZE);
        BallThread = new Thread(ball);
        BallThread.start();
    }
    public void newRackets(){
        racket1 = new Racket(0,(GAME_HEIGHT/2)-(RACKET_HEIGHT/2), RACKET_WIDTH, RACKET_HEIGHT,1);
        racket2 = new Racket(GAME_WIDTH-RACKET_WIDTH ,(GAME_HEIGHT/2)-(RACKET_HEIGHT/2), RACKET_WIDTH, RACKET_HEIGHT,2);
        Racket1Thread = new Thread(racket1);
        Racket2Thread = new Thread(racket2);
        Racket1Thread.start();
        Racket2Thread.start();
    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        racket1.draw(g);
        racket2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        racket1.move();
        racket2.move();
        ball.move();
    }
    public void checkCollision(){
        if(ball.y<=0){
            ball.setYDirection(-ball.ySpeed);
        }
        if(ball.y > GAME_HEIGHT - BALL_SIZE){
            ball.setYDirection(-ball.ySpeed);
        }

        if(ball.intersects(racket1)) {
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.setXDirection(ball.xSpeed);
        }
        if(ball.intersects(racket2)) {
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.setXDirection(-ball.xSpeed);
        }

        if(ball.x<=0){
            score.player2++;
            newRackets();
            newBall();
        }

        if(ball.x>=GAME_WIDTH-BALL_SIZE){
            score.player1++;
            newRackets();
            newBall();
        }

        if(racket1.y<=0){
            racket1.y=0;
        }
        if(racket1.y>=(GAME_HEIGHT-RACKET_HEIGHT)){
            racket1.y = GAME_HEIGHT-RACKET_HEIGHT;
        }
        if(racket2.y<=0){
            racket2.y=0;
        }
        if(racket2.y>=(GAME_HEIGHT-RACKET_HEIGHT)){
            racket2.y = GAME_HEIGHT-RACKET_HEIGHT;
        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(score.player1 < totalScore && score.player2 < totalScore){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
        if (score.player1 == totalScore) {
            int answer = JOptionPane.showConfirmDialog(null, "Winner - Player 1!!!\n" +
                    "Restart?", "Finish", JOptionPane.YES_NO_OPTION);
            if (answer == 0)
            {
                restart();
                run();
            }
            else
                System.exit(0);

        }
        if (score.player2 == totalScore) {
            int answer = JOptionPane.showConfirmDialog(null, "Winner - Player 2!!!\n" +
                    "Restart?", "Finish", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                restart();
                run();
            }
            else
                System.exit(0);
        }
    }


    public void restart() {
        score.player1 = 0;
        score.player2 = 0;
        newRackets();
        newBall();
    }

    public class AListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            racket1.keyPressed(e);
            racket2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            racket1.keyReleased(e);
            racket2.keyReleased(e);

        }
    }
}

