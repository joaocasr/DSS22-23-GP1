import RacingManagerLN.RacingManagerLN;
import RacingManagerUI.TextUI;

public class Main {
    public static void main(String[] args){
        try{
            RacingManagerLN racingManagerLN = new RacingManagerLN();
            new TextUI(racingManagerLN).menuPrincipal();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}