import javax.swing.*;
import java.awt.*;

public class FramePingPong extends JFrame{
    GamePanel panel;
    FramePingPong(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Ping-Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}
