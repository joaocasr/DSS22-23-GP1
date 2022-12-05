package RacingManagerUI;

import RacingManagerLN.Exceptions.SintaxeIncorretaException;
import RacingManagerLN.IRacingManagerLN;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUI {
    private static Menu menu = new Menu();
    private IRacingManagerLN iRacingManagerLN;


    public TextUI(IRacingManagerLN iRacingManagerLN) {
        this.iRacingManagerLN = iRacingManagerLN;
    }

    public void menuPrincipal(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Login\n");
        opcoes.add("Registar Conta\n");
        opcoes.add("Simular Campeonato\n");
        opcoes.add("Jogar");
        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataEfetuarLogin);
        menu.setHandlers(2, this::trataRegistarConta);
        menu.setHandlers(3, this::trataSimularCampeonato);
        menu.setHandlers(4,this::trataJogar);
        menu.run();
    }

    public void menuPrincipal2(){

        String userAtual = this.iRacingManagerLN.getCurrentUser();
        User u = this.iRacingManagerLN.getUser(userAtual);
        List<String> opcoes = new ArrayList<>();
        if(u.getIsAdmin()) {
            opcoes.add("Gestão de Campeonatos\n");
            opcoes.add("Gestão de Circuitos\n");
            opcoes.add("Gestão de Carros\n");
            opcoes.add("Gestão de Pilotos\n");
            opcoes.add("Logout");

            menu.setOptions(opcoes);

            menu.setHandlers(1, this::menuCampeonatos);
            menu.setHandlers(2, this::menuCircuitos);
            menu.setHandlers(3, this::trataGerirCarros);
            menu.setHandlers(4, this::trataGerirPilotos);
            menu.setHandlers(5, this::trataLogout);
        }else{
            opcoes.add("Simular Campeonato\n");
            opcoes.add("Jogar\n");
            opcoes.add("Logout");

            menu.setOptions(opcoes);
            menu.setHandlers(1,this::trataSimularCampeonato);
            menu.setHandlers(2,this::trataJogar);
            menu.setHandlers(3,this::trataLogout);
        }
        menu.run();
    }

    public void menuCampeonatos(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Criar Campeonato\n");
        opcoes.add("Remover Campeonato\n");
        opcoes.add("Consultar Campeonato\n");
        opcoes.add("Voltar");
        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataAdicionarCampeonato);
        menu.setHandlers(2, this::trataRemoverCampeonato);
        menu.setHandlers(3, this::trataConsultarCampeonato);
        menu.setHandlers(4,this::menuPrincipal2);

        menu.run();
    }

    public void menuCircuitos(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Criar Circuito\n");
        opcoes.add("Remover Circuito\n");
        opcoes.add("Voltar");

        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataAdicionarCircuito);
        menu.setHandlers(2, this::trataRemoverCircuito);
        menu.setHandlers(3,this::menuPrincipal2);


        menu.run();
    }

    public void trataEfetuarLogin(){
        System.out.println("Digite o username:");
        Scanner scanner = new Scanner(System.in);
        String username=scanner.nextLine();
        System.out.println("Digite a password:");
        scanner = new Scanner(System.in);
        String password=scanner.nextLine();
        if(!iRacingManagerLN.existeUser(username)) System.out.print("O utilizador que digitou não existe.");
        else if(!iRacingManagerLN.efetuaLogin(username,password)) System.out.print("O username ou palavra passe que digitou estão incorretas.");
        else {
            iRacingManagerLN.setUserAtual(username);
            System.out.print("O login foi efetuado com sucesso.");
            menuPrincipal2();
        }
    }

    public void trataLogout(){
        iRacingManagerLN.setUserAtual(null);
        menuPrincipal();
    }

    public void trataRegistarConta() throws SintaxeIncorretaException{
        //User input
        System.out.println("Digite o username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Digite a password:");
        String password= scanner.nextLine();
        System.out.println("Registar conta como administrador?(S/N)");
        String admin = scanner.nextLine();

        //check if account is admin
        boolean isAdmin=false;
        if(admin.equals("S")) isAdmin = true;
        else if(!admin.equals("N")) throw new SintaxeIncorretaException("Não foi possível ler o valor introduzido.");

        //Validate input and register account
        if(iRacingManagerLN.existeUser(username))
            System.out.print("O utilizador que digitou já existe.");
        else if(iRacingManagerLN.registaUser(username,password,isAdmin))
            System.out.print("O registo de conta foi efetuado com sucesso.");
        else
            System.out.print("Registo de conta inválido.");;
    }

    public void trataAdicionarCampeonato(){
        System.out.println("Digite o nome do campeonato:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println("Digite o nº de participantes");
        int participantes = scanner.nextInt();
        System.out.print("Lista de Circuitos:");
        System.out.println(iRacingManagerLN.getCircuitos());
        System.out.print("Digite quantos circuitos pretende adicionar ao campeonato:");
        int num = scanner.nextInt();
        scanner.nextLine();
        List<Circuito> l = new ArrayList<>();
        while (num>0) {
            System.out.print(">>>");
            String circuito = scanner.nextLine();
            Circuito c = iRacingManagerLN.getCircuito(circuito);
            l.add(c);
            num--;
        }
        if(iRacingManagerLN.adicionarCampeonato(nome,participantes,l)) System.out.print("Campeonato adicionado com sucesso.");
        else System.out.println("Ocorreu um erro na adicção do campeonato.");

    }

    public void trataAdicionarCircuito(){
        System.out.println("Digite o nome do circuito:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println("Digite a distância:");
        double distancia = scanner.nextDouble();
        System.out.println("Digite o nº de voltas:");
        int voltas = scanner.nextInt();
        System.out.println("Digite o nº de curvas:");
        int ncurvas = scanner.nextInt();
        System.out.println("Digite o nº de chicanes:");
        int nchicanes = scanner.nextInt();
        int nretas = ncurvas+nchicanes;
        scanner.nextLine();
        List<Reta> allRetas=new ArrayList<>();
        StringBuilder retaNome = new StringBuilder("reta");
        List<Chicane> allChicanes = new ArrayList<>();
        StringBuilder chicaneNome = new StringBuilder("chicane");
        StringBuilder curvaNome = new StringBuilder("curva");
        List<Curva> allCurvas = new ArrayList<>();
        int i=1;
        System.out.println("GDU: Possível=0 Difícil=1 Impossível=2");
        while(i<=nretas){
            String nomereta = retaNome.append(i).toString();
            System.out.println("Digite o GDU da "+nomereta);
            int gdu = scanner.nextInt();
            Reta r = new Reta(nomereta,gdu);
            allRetas.add(r);
            i++;
        }
        i=1;
        while(i<=nchicanes){
            String nomechicane = chicaneNome.append(i).toString();
            Chicane chicane = new Chicane(nomechicane,1);
            allChicanes.add(chicane);
            i++;
        }
        i=1;
        while(i<=ncurvas){
            String nomecurva = curvaNome.append(i).toString();
            System.out.println("Digite o GDU da "+nomecurva);
            int gdu = scanner.nextInt();
            Curva curva = new Curva(nomecurva,gdu);
            allCurvas.add(curva);
            i++;
        }
            scanner.nextLine();
            if(iRacingManagerLN.adicionaCircuito(nome,distancia,voltas,nretas,ncurvas,nchicanes,allRetas,allCurvas,allChicanes)) System.out.println("Circuito "+nome+" guardado com sucesso.");
            else System.out.println("Circuito não foi guardado.");
    }

    public void trataConsultarCampeonato(){
        System.out.println("Digite o nome do campeonato:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println(iRacingManagerLN.consultaCampeonato(nome));
    }

    public void trataRemoverCircuito(){}
    public void trataRemoverCampeonato(){}
    public void trataSimularCampeonato(){}
    public void trataGerirCampeonatos(){}
    public void trataGerirCircuitos(){}
    public void trataGerirCarros(){}
    public void trataGerirPilotos(){}
    public void trataJogar(){}

}
