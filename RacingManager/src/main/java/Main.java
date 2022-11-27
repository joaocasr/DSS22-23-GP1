import RacingManagerUI.TextUI;

public class Main {
    public static void main(String[] args){
        try{
            new TextUI().menuPrincipal();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}