package RacingManagerUI;

import RacingManagerLN.Exceptions.SintaxeIncorretaException;
import RacingManagerLN.*;
import RacingManagerLN.SubGestaoCP.ISubGestaoCPFacade;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoCP.SubGestaoCPFacade;
import RacingManagerLN.SubGestaoJogos.Simulacao.Configuracao;
import RacingManagerLN.SubGestaoJogos.Simulacao.Simulacao;
import RacingManagerLN.SubGestaoUsers.*;
import RacingManagerLN.SubGestaoCC.Circuito.*;
import RacingManagerLN.SubGestaoCC.*;
import data.PilotoDAO;


import java.util.*;

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
            menu.setHandlers(3, this::menuCarros);
            menu.setHandlers(4, this::menuPilotos);
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

    public void trataSimularCampeonato(){
        System.out.println("Digite o campeonato que pretende simular:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        Campeonato campeonato =  iRacingManagerLN.getCampeonato(nome);
        int NCorridas = campeonato.getNumeroCorridas();
        Configuracao conf=null;
        List<Configuracao> configuracoes= new ArrayList<>();
        Map<String,Integer> numeroConfiguracoes = new HashMap<>();
        List<String> jogadores = iRacingManagerLN.getJogadoresASimular(nome);
        for(String jogador: jogadores){
            numeroConfiguracoes.put(jogador,0);
        }
        Map<String,Integer> pontuacoes = new HashMap<>();
        for(Circuito c: campeonato.getCircuitos()){
            Simulacao simulacao = new Simulacao(c,iRacingManagerLN.getInscricoes(nome),configuracoes);
            pontuacoes=simulacao.getScore();
            campeonato.atualizaClassificacao(pontuacoes);
            int nj=0;
            for(String j : jogadores) {
                nj++;
                if (numeroConfiguracoes.get(j) < ((2 * NCorridas) / 3)) {
                    System.out.println("*** PARAGEM- BOX "+nj+" - jogador =" + j + " ***");
                    System.out.println("Downforce");
                    System.out.print(">>>");
                    float downforce= scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Tipo Pneu: [Macio] [Duro] [Chuva]");
                    System.out.print(">>>");
                    String tipo=scanner.nextLine();
                    System.out.println("Modo do Motor: [Conservador] [Normal] [Agressivo]");
                    System.out.print(">>>");
                    String modo = scanner.nextLine();
                    if(iRacingManagerLN.hasCarroC2(nome,j)){
                        System.out.println("Afinação : [Freio] [Motor] [Suspensao] [Corpo] [Salao]");
                        System.out.print(">>>");
                        String afinacao = scanner.nextLine();
                        conf = new Configuracao(j,downforce,tipo,modo,afinacao);
                    }else{
                        conf = new Configuracao(j,downforce,tipo,modo);
                    }
                    int n=numeroConfiguracoes.get(j);
                    numeroConfiguracoes.put(j,n+1);
                }
                if(configuracoes.size()!=0) configuracoes = new ArrayList<>();
                configuracoes.add(conf);
            }
        }
        Map<String,Integer> classsificacao = campeonato.getClasssificacaoCamp();
        iRacingManagerLN.atualizaScore(classsificacao);
    }


    public void trataRemoverCircuito(){}
    public void trataRemoverCampeonato(){}
    public void trataGerirCampeonatos(){}
    public void trataGerirCircuitos(){}

    public void menuPilotos(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Criar Piloto\n");
        opcoes.add("Remover Piloto\n");
        opcoes.add("Voltar");

        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataCriaPilotos);
        menu.setHandlers(2, this::trataRemoverPilotos);
        menu.setHandlers(3,this::menuPrincipal2);


        menu.run();
    }
    public void trataCriaPilotos(){
        System.out.println("Digite o nome do Piloto:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println("Digite o CTS:");
        float cts = scanner.nextFloat();
        System.out.println("Digite o SVA:");
        float sva = scanner.nextFloat();
        if(iRacingManagerLN.validarPericia(cts,sva) && iRacingManagerLN.validaNomePiloto(nome)){
            iRacingManagerLN.adicionarPiloto(nome,cts,sva);
            System.out.println("Piloto criado com sucesso!");
            menuPrincipal2();
        }
        else{
            System.out.println("Pericia Invalida!");
            trataCriaPilotos();
        }


    }

    public void trataRemoverPilotos(){
        System.out.println("Digite o nome do Piloto que deseja remover:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        if(iRacingManagerLN.validaNomePiloto(nome)){
            iRacingManagerLN.removerPiloto(nome);
            System.out.println("Piloto removido!");
            menuPrincipal2();
        } else {
            System.out.println("Esse Piloto não existe.");
            menuPrincipal2();
        }


    }

    public void menuCarros(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Criar Carro\n");
        opcoes.add("Remover Carro\n");
        opcoes.add("Voltar");

        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataCriaCarros);
        menu.setHandlers(2, this::trataRemoverCarros);
        menu.setHandlers(3,this::menuPrincipal2);


        menu.run();
    }

    public void menuTipoCarros(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("C1\n");
        opcoes.add("C2\n");
        opcoes.add("GT\n");
        opcoes.add("SC\n");
        opcoes.add("C1Hibrido\n");
        opcoes.add("C2Hibrido\n");
        opcoes.add("GTHibrido\n");


        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataCriaCarrosC1);
        menu.setHandlers(2, this::trataCriaCarrosC2);
        menu.setHandlers(3,this::trataCriaCarrosGT);
        menu.setHandlers(4,this::trataCriaCarrosSC);
        menu.setHandlers(5, this::trataCriaCarrosC1Hibrido);
        menu.setHandlers(6, this::trataCriaCarrosC2Hibrido);
        menu.setHandlers(7,this::trataCriaCarrosGTHibrido);


        menu.run();
    }

    private void trataCriaCarrosGTHibrido() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
        System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
        int cilindrada = scanner.nextInt();
        if (3000<=cilindrada && cilindrada<=5000){
            System.out.println("Digite o PAC (valor entre o e 1):");
            float pac = scanner.nextFloat();
            if (iRacingManagerLN.validaPac(pac)){

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarGTHibrido("7",marca,modelo,potencia,pac,100,cilindrada,tipopneu,downforce,motor);
                menuPrincipal2();
            }
            else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        } else {
            System.out.println("Cilindrada Errada!");
            menuPrincipal2();
        }

    }

    private void trataCriaCarrosC2Hibrido() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
        System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
        int cilindrada = scanner.nextInt();
        if (3000<=cilindrada && cilindrada<=5000){
            System.out.println("Digite o PAC (valor entre o e 1):");
            float pac = scanner.nextFloat();
            if (iRacingManagerLN.validaPac(pac)){

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarC2Hibrido("6",marca,modelo,potencia,pac,100,tipopneu,cilindrada,motor,downforce);
                menuPrincipal2();
            }
            else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        } else {
            System.out.println("Cilindrada Errada!");
            menuPrincipal2();
        }


    }

    private void trataCriaCarrosC1Hibrido() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
        System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
        int cilindrada = scanner.nextInt();
        if (3000<=cilindrada && cilindrada<=5000){
            System.out.println("Digite o PAC (valor entre o e 1):");
            float pac = scanner.nextFloat();
            if (iRacingManagerLN.validaPac(pac)){

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarC1Hibrido("5",marca,modelo,potencia,pac,100,downforce,cilindrada,tipopneu,motor);
                menuPrincipal2();
            }
            else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        } else {
            System.out.println("Cilindrada Errada!");
            menuPrincipal2();
        }


    }

    private void trataCriaCarrosSC() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
            System.out.println("Digite o PAC (valor entre o e 1):");
            float pac = scanner.nextFloat();
            if (iRacingManagerLN.validaPac(pac)){

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarSC("4",marca,modelo,potencia,pac,tipopneu,downforce,motor,2500);
                menuPrincipal2();
            }
            else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }

    }

    private void trataCriaCarrosGT() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
        System.out.println("Digite a Cilindrada(entre 2000 e 4000):");
        int cilindrada = scanner.nextInt();
        if (2000<=cilindrada && cilindrada<=4000){
            System.out.println("Digite o PAC (valor entre o e 1):");
            float pac = scanner.nextFloat();
            if (iRacingManagerLN.validaPac(pac)){

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarGT("3",marca,modelo,potencia,pac,cilindrada,tipopneu,downforce,motor);
                menuPrincipal2();
            }
            else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        } else {
            System.out.println("Cilindrada Errada!");
            menuPrincipal2();
        }


    }

    private void trataCriaCarrosC2() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
        System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
        int cilindrada = scanner.nextInt();
        if (3000<=cilindrada && cilindrada<=5000){
            System.out.println("Digite o PAC (valor entre o e 1):");
            float pac = scanner.nextFloat();
            if (iRacingManagerLN.validaPac(pac)){

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarC2("2",marca,modelo,potencia,tipopneu,pac,cilindrada,downforce,motor);
                menuPrincipal2();
            }
            else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        } else {
            System.out.println("Cilindrada Errada!");
            menuPrincipal2();
        }



    }

    private void trataCriaCarrosC1() {
        System.out.println("Digite o nome da Marca:");
        Scanner scanner = new Scanner(System.in);
        String marca = scanner.nextLine();
        System.out.println("Digite o Modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a Potencia:");
        int potencia = scanner.nextInt();
        System.out.println("Digite o PAC (valor entre o e 1):");
        float pac = scanner.nextFloat();
        if (iRacingManagerLN.validaPac(pac)){

            System.out.println("Digite a Tipo Pneu:");
            String tipopneu = scanner.nextLine();
            System.out.println("Digite a Downforce:");
            float downforce = scanner.nextFloat();
            System.out.println("Digite a Tipo Motor:");
            String motor = scanner.nextLine();
            iRacingManagerLN.adicionarC1("1",marca,modelo,potencia,pac,6000,tipopneu,downforce,motor);
            menuPrincipal2();
        }
        else {
            System.out.println("Pac Errado!");
            menuPrincipal2();
        }
    }

    public void trataCriaCarros(){
        menuTipoCarros();
    }

    public void trataRemoverCarros(){
        System.out.println("Digite o Id do Carro que deseja remover:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if(iRacingManagerLN.existeCarro(id)){
            iRacingManagerLN.removerCarro(id);
            System.out.println("Carro removido!");
            menuPrincipal2();
        } else {
            System.out.println("Esse Carro não existe.");
            menuPrincipal2();
        }


    }

    public void trataJogar(){}

}
