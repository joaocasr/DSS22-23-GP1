package RacingManagerUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUI {
    private static Menu menu = new Menu();
    public void menuPrincipal(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Login\n");
        opcoes.add("Registar Conta\n");
        opcoes.add("Simular Campeonato\n");
        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataEfetuarLogin);
        menu.setHandlers(2, this::trataRegistarConta);
        menu.setHandlers(3, this::trataSimularCampeonato);
        menu.run();
    }

    public void menuPrincipal2(String username,String password){

        //verificar o role

        List<String> opcoes = new ArrayList<>();
        opcoes.add("Gestão de Campeonatos\n");
        opcoes.add("Gestão de Circuitos\n");
        opcoes.add("Gestão de Carros\n");
        opcoes.add("Gestão de Pilotos\n");
        opcoes.add("Jogar\n");
        opcoes.add("Logout\n");



        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataGerirCampeonatos);
        menu.setHandlers(2, this::trataGerirCircuitos);
        menu.setHandlers(3, this::trataGerirCarros);
        menu.setHandlers(4, this::trataGerirPilotos);
        menu.setHandlers(5, this::trataJogar);
        menu.setHandlers(6, this::trataLogout);

        menu.run();
    }

    public void trataEfetuarLogin(){
        System.out.println("Digite o username:");
        Scanner scanner = new Scanner(System.in);
        String username=scanner.nextLine();
        System.out.println("Digite a password:");
        scanner = new Scanner(System.in);
        String password=scanner.nextLine();

        //validar

        menuPrincipal2(username,password);

    }

    public void trataLogout(){
        menuPrincipal();
    }

    public void trataRegistarConta(){}
    public void trataSimularCampeonato(){}
    public void trataGerirCampeonatos(){}
    public void trataGerirCircuitos(){}
    public void trataGerirCarros(){}
    public void trataGerirPilotos(){}
    public void trataJogar(){}

}
