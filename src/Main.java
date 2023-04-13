/**
 * Class "Main" shows program work
 *
 * @author Valentin Zadorogin PIN-32
 */

public class Main {

    private static FramePingPong game;

    static void modelDefineGame(){
        game = new FramePingPong();
    }
    static void view(String msg){
        System.out.println(msg);
    }
    static void controller(){
        view("Preparing to start...");
        modelDefineGame();
        view("End!");
    }
    public static void main(String[] args){
        controller();
    }
}
