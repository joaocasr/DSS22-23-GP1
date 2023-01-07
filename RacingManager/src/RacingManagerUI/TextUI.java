package RacingManagerUI;

import RacingManagerLN.Exceptions.CampeonatoInexistenteException;
import RacingManagerLN.Exceptions.SintaxeIncorretaException;
import RacingManagerLN.*;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoJogos.Inscricao;
import RacingManagerLN.SubGestaoJogos.Simulacao.Configuracao;
import RacingManagerLN.SubGestaoJogos.Simulacao.Simulacao;
import RacingManagerLN.SubGestaoUsers.*;
import RacingManagerLN.SubGestaoCC.Circuito.*;
import RacingManagerLN.SubGestaoCC.*;


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
        opcoes.add("Consultar Ranking\n");
        opcoes.add("Jogar");
        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataEfetuarLogin);
        menu.setHandlers(2, this::trataRegistarConta);
        menu.setHandlers(3, this::trataSimularCampeonato);
        menu.setHandlers(4,this::trataConsultarRankingMenu);
        menu.setHandlers(5,this::trataJogar);
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
            opcoes.add("Mudar Versao\n");
            opcoes.add("Logout");

            menu.setOptions(opcoes);
            menu.setHandlers(1,this::trataSimularCampeonato);
            menu.setHandlers(2,this::trataJogar);
            menu.setHandlers(3,this::trataMudarVersao);
            menu.setHandlers(4,this::trataLogout);
        }
        menu.run();
    }

    public void trataMudarVersao(){
        String username = iRacingManagerLN.getCurrentUser();
        User u = iRacingManagerLN.getUser(username);
        Scanner scanner = new Scanner(System.in);
        if(u.getVersao().equals("B")){
            System.out.println("Mudar para versão Premium ? [S/N]");
            String resposta = scanner.nextLine();
            if(resposta.equalsIgnoreCase("S")){
                iRacingManagerLN.mudaVersao("P",username);
                System.out.println("Mudança de versão da conta efetuada com sucesso.");
            }
        }
        else{
            System.out.println("Mudar para versão Base ? [S/N]");
            String resposta = scanner.nextLine();
            if(resposta.equalsIgnoreCase("S")){
                iRacingManagerLN.mudaVersao("B",username);
                System.out.println("Mudança de versão da conta efetuada com sucesso.");
            }
        }
    }


    public void trataConsultarRankingMenu(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Consultar Ranking de todos os jogadores.\n");
        opcoes.add("Consultar Ranking de jogador.\n");
        opcoes.add("Voltar");

        menu.setOptions(opcoes);
        menu.setHandlers(1,this::trataConsultarRanking);
        menu.setHandlers(2,this::trataConsultarRankingJogador);
        menu.setHandlers(3,this::menuPrincipal2);
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
        List<String> lista = iRacingManagerLN.getCircuitos();
        System.out.println(lista);
        System.out.print("Digite quantos circuitos pretende adicionar ao campeonato:");
        int num = scanner.nextInt();
        scanner.nextLine();
        List<Circuito> l = new ArrayList<>();
        while (num>0) {
            System.out.print(">>>");
            String circuito = scanner.nextLine();
            Circuito c = iRacingManagerLN.getCircuito(circuito);
            if(c==null) System.out.println("something wrong");
            l.add(c);
            num--;
        }
        if(iRacingManagerLN.adicionarCampeonato(nome,participantes,l)) System.out.print("Campeonato adicionado com sucesso.");
        else System.out.println("Ocorreu um erro na adicção do campeonato.");

    }

    public void trataAdicionarCircuito() {
        System.out.println("Digite o nome do circuito:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        if (!iRacingManagerLN.existeCircuito(nome)) {
            System.out.println("Digite a distância:");
            float distancia = scanner.nextFloat();
            System.out.println("Digite o nº de voltas:");
            int voltas = scanner.nextInt();
            System.out.println("Digite o nº de curvas:");
            int ncurvas = scanner.nextInt();
            System.out.println("Digite o nº de chicanes:");
            int nchicanes = scanner.nextInt();
            int nretas = ncurvas + nchicanes;
            scanner.nextLine();
            List<Reta> allRetas = new ArrayList<>();
            List<Chicane> allChicanes = new ArrayList<>();
            List<Curva> allCurvas = new ArrayList<>();
            int i = 1;
            System.out.println("GDU: Possível=0 Difícil=1 Impossível=2");
            while (i <= nretas) {
                System.out.println("Digite o ID da reta:");
                String nomereta = scanner.nextLine();
                System.out.println("Digite o GDU da " + nomereta);
                int gdu = scanner.nextInt();
                scanner.nextLine();
                Reta r = new Reta(nomereta, gdu);
                allRetas.add(r);
                i++;
            }
            i = 1;
            while (i <= nchicanes) {
                System.out.println("Digite o ID da chicane:");
                String nomechicane = scanner.nextLine();
                Chicane chicane = new Chicane(nomechicane, 1);
                allChicanes.add(chicane);
                i++;
            }
            i = 1;
            while (i <= ncurvas) {
                System.out.println("Digite o ID da curva:");
                String nomecurva = scanner.nextLine();
                System.out.println("Digite o GDU da " + nomecurva);
                int gdu = scanner.nextInt();
                scanner.nextLine();
                Curva curva = new Curva(nomecurva, gdu);
                allCurvas.add(curva);
                i++;
            }
            if (iRacingManagerLN.adicionaCircuito(nome, distancia, voltas, nretas, ncurvas, nchicanes, allRetas, allCurvas, allChicanes)) System.out.println("Circuito " + nome + " guardado com sucesso.");
        }else System.out.println("Existe um circuito com a mesma designação no sistema.");
    }

    public void trataConsultarCampeonato(){
        System.out.println("Digite o nome do campeonato:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println(iRacingManagerLN.consultaCampeonato(nome));
    }

    public void trataSimularCampeonato() {
        Simulacao logo = new Simulacao();
        try {
            logo.showGameLogo("RACING MANAGER");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Digite o campeonato que pretende simular:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        try {
            Campeonato campeonato = iRacingManagerLN.getCampeonato(nome);
            List<Inscricao> inscricoes = iRacingManagerLN.getInscricoes(campeonato.getNomeCampeonato());
            if (inscricoes != null && campeonato.getParticipantes() == inscricoes.size()) {
                int NCorridas = campeonato.getNumeroCorridas();
                Configuracao conf = null;
                List<Configuracao> configuracoes = new ArrayList<>();
                Map<String, Integer> numeroConfiguracoes = new HashMap<>();
                List<String> jogadores = iRacingManagerLN.getJogadoresASimular(nome);
                for (String jogador : jogadores) {
                    numeroConfiguracoes.put(jogador, 0);
                }
                Map<String, Integer> pontuacoes = new HashMap<>();
                Map<String, Integer> classificacao = new HashMap<>();
                for (Circuito c : campeonato.getCircuitos()) {
                    Simulacao simulacao = null;
                    try {
                        simulacao = new Simulacao(c, inscricoes, configuracoes);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pontuacoes = simulacao.getScore();
                    campeonato.atualizaClassificacao(pontuacoes);
                    for (String j : jogadores) {
                        if (numeroConfiguracoes.get(j) < ((2 * NCorridas) / 3)) {
                            System.out.println("*** PARAGEM - CONFIGURAÇÃO DO CARRO - jogador =" + j + " ***");
                            System.out.println("Downforce");
                            System.out.print(">>>");
                            float downforce = scanner.nextFloat();
                            scanner.nextLine();
                            System.out.println("Tipo Pneu: [Macio] [Duro] [Chuva]");
                            System.out.print(">>>");
                            String tipo = scanner.nextLine();
                            System.out.println("Modo do Motor: [Conservador] [Normal] [Agressivo]");
                            System.out.print(">>>");
                            String modo = scanner.nextLine();
                            if (iRacingManagerLN.hasCarroC2(nome, j)) {
                                System.out.println("Afinação : [Freio] [Motor] [Suspensao] [Corpo] [Salao]");
                                System.out.print(">>>");
                                String afinacao = scanner.nextLine();
                                conf = new Configuracao(j, downforce, tipo, modo, afinacao);
                            } else {
                                conf = new Configuracao(j, downforce, tipo, modo);
                            }
                            int n = numeroConfiguracoes.get(j);
                            numeroConfiguracoes.put(j, n + 1);
                        }
                        if (configuracoes.size() != 0) configuracoes = new ArrayList<>();
                        configuracoes.add(conf);
                    }
                    classificacao = campeonato.getClasssificacaoCamp();
                    System.out.println("PONTUAÇÃO ATUAL DO CAMPEONATO");
                    System.out.println("---------------------------------");
                    classificacao.forEach((k,v)-> System.out.println(k+" - "+v+"pts"));
                }
                iRacingManagerLN.atualizaScore(classificacao);
            } else System.out.println("\nAVISO: O campeonato não atingiu ou excede o número de jogadores configurados para o campeonato!");
        }catch (CampeonatoInexistenteException e){
            System.out.println(e.getMessage());
        }
    }


    public void trataRemoverCircuito(){
        System.out.println("Digite o ID do circuito a remover:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        if(iRacingManagerLN.removeCircuito(nome)) System.out.println("Circuito removido com sucessso!");
        else System.out.println("Ocorreu um erro- O circuito não foi removido.");
    }

    public void trataRemoverCampeonato(){
        System.out.println("Digite o nome do campeonato a remover:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        if(iRacingManagerLN.removeCampeonato(nome)) System.out.println("Circuito removido com sucesso.");
        else System.out.println("Ocorreu um erro- O campeonato não foi removido.");
    }


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
        if(iRacingManagerLN.validarPericia(cts,sva) && !iRacingManagerLN.validaNomePiloto(nome)){
            iRacingManagerLN.adicionarPiloto(nome,cts,sva);
            System.out.println("Piloto criado com sucesso!");
            menuPrincipal2();
        }
        else{
            System.out.println("Pericia Invalida ou piloto já existente!");
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
        opcoes.add("Voltar");


        menu.setOptions(opcoes);

        menu.setHandlers(1, this::trataCriaCarrosC1);
        menu.setHandlers(2, this::trataCriaCarrosC2);
        menu.setHandlers(3,this::trataCriaCarrosGT);
        menu.setHandlers(4,this::trataCriaCarrosSC);
        menu.setHandlers(5, this::trataCriaCarrosC1Hibrido);
        menu.setHandlers(6, this::trataCriaCarrosC2Hibrido);
        menu.setHandlers(7,this::trataCriaCarrosGTHibrido);
        menu.setHandlers(8,this::menuCarros);


        menu.run();
    }

    private void trataCriaCarrosGTHibrido() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
            int cilindrada = scanner.nextInt();
            if (3000 <= cilindrada && cilindrada <= 5000) {
                System.out.println("Digite o PAC (valor entre 0 e 1):");
                float pac = scanner.nextFloat();
                System.out.println("Digite a potência elétrica:");
                int eletrica= scanner.nextInt();
                scanner.nextLine();
                if (iRacingManagerLN.validaPac(pac)) {

                    System.out.println("Digite a Tipo Pneu:");
                    String tipopneu = scanner.nextLine();
                    System.out.println("Digite a Downforce:");
                    float downforce = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Digite a Tipo Motor:");
                    String motor = scanner.nextLine();
                    iRacingManagerLN.adicionarGTHibrido(id, marca, modelo, potencia,eletrica,pac, cilindrada, tipopneu, downforce, motor);
                    menuPrincipal2();
                } else {
                    System.out.println("Pac Errado!");
                    menuPrincipal2();
                }
            } else {
                System.out.println("Cilindrada Errada!");
                menuPrincipal2();
            }

        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    private void trataCriaCarrosC2Hibrido() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
            int cilindrada = scanner.nextInt();
            if (3000 <= cilindrada && cilindrada <= 5000) {
                System.out.println("Digite o PAC (valor entre 0 e 1):");
                float pac = scanner.nextFloat();
                System.out.println("Digite a potência elétrica:");
                int eletrica= scanner.nextInt();
                scanner.nextLine();
                if (iRacingManagerLN.validaPac(pac)) {

                    System.out.println("Digite a Tipo Pneu:");
                    String tipopneu = scanner.nextLine();
                    System.out.println("Digite a Downforce:");
                    float downforce = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Digite a Tipo Motor:");
                    String motor = scanner.nextLine();
                    iRacingManagerLN.adicionarC2Hibrido(id, marca, modelo, potencia,eletrica, pac, tipopneu, cilindrada, motor, downforce);
                    System.out.println("Carro adicionado ao sistema!");
                    menuPrincipal2();
                } else {
                    System.out.println("Pac Errado!");
                    menuPrincipal2();
                }
            } else {
                System.out.println("Cilindrada Errada!");
                menuPrincipal2();
            }

        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    private void trataCriaCarrosC1Hibrido() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
            int cilindrada = scanner.nextInt();
            if (3000 <= cilindrada && cilindrada <= 5000) {
                System.out.println("Digite o PAC (valor entre 0 e 1):");
                float pac = scanner.nextFloat();
                System.out.println("Digite a potência elétrica:");
                int eletrica= scanner.nextInt();
                scanner.nextLine();
                if (iRacingManagerLN.validaPac(pac)) {

                    System.out.println("Digite a Tipo Pneu:");
                    String tipopneu = scanner.nextLine();
                    System.out.println("Digite a Downforce:");
                    float downforce = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Digite a Tipo Motor:");
                    String motor = scanner.nextLine();
                    iRacingManagerLN.adicionarC1Hibrido(id, marca, modelo, potencia,eletrica ,pac, downforce, cilindrada, tipopneu, motor);
                    System.out.println("Carro adicionado ao sistema!");
                    menuPrincipal2();
                } else {
                    System.out.println("Pac Errado!");
                    menuPrincipal2();
                }
            } else {
                System.out.println("Cilindrada Errada!");
                menuPrincipal2();
            }
        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    private void trataCriaCarrosSC() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite o PAC (valor entre 0 e 1):");
            float pac = scanner.nextFloat();
            scanner.nextLine();
            if (iRacingManagerLN.validaPac(pac)) {

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarSC(id, marca, modelo, potencia, pac, tipopneu, downforce, motor, 2500);
                System.out.println("Carro adicionado ao sistema!");
                menuPrincipal2();
            } else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    private void trataCriaCarrosGT() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite a Cilindrada(entre 2000 e 4000):");
            int cilindrada = scanner.nextInt();
            if (2000 <= cilindrada && cilindrada <= 4000) {
                System.out.println("Digite o PAC (valor entre 0 e 1):");
                float pac = scanner.nextFloat();
                scanner.nextLine();
                if (iRacingManagerLN.validaPac(pac)) {

                    System.out.println("Digite a Tipo Pneu:");
                    String tipopneu = scanner.nextLine();
                    System.out.println("Digite a Downforce:");
                    float downforce = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Digite a Tipo Motor:");
                    String motor = scanner.nextLine();
                    iRacingManagerLN.adicionarGT(id, marca, modelo, potencia, pac, cilindrada, tipopneu, downforce, motor);
                    System.out.println("Carro adicionado ao sistema!");
                    menuPrincipal2();
                } else {
                    System.out.println("Pac Errado!");
                    menuPrincipal2();
                }
            } else {
                System.out.println("Cilindrada Errada!");
                menuPrincipal2();
            }
        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    private void trataCriaCarrosC2() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite a Cilindrada(entre 3000 e 5000):");
            int cilindrada = scanner.nextInt();
            if (3000 <= cilindrada && cilindrada <= 5000) {
                System.out.println("Digite o PAC (valor entre 0 e 1):");
                float pac = scanner.nextFloat();
                scanner.nextLine();
                if (iRacingManagerLN.validaPac(pac)) {

                    System.out.println("Digite a Tipo Pneu:");
                    String tipopneu = scanner.nextLine();
                    System.out.println("Digite a Downforce:");
                    float downforce = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Digite a Tipo Motor:");
                    String motor = scanner.nextLine();
                    iRacingManagerLN.adicionarC2(id, marca, modelo, potencia, tipopneu, pac, cilindrada, downforce, motor);
                    System.out.println("Carro adicionado ao sistema!");
                    menuPrincipal2();
                } else {
                    System.out.println("Pac Errado!");
                    menuPrincipal2();
                }
            } else {
                System.out.println("Cilindrada Errada!");
                menuPrincipal2();
            }
        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    private void trataCriaCarrosC1() {
        System.out.println("Digite o ID do carro a adicionar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if (!iRacingManagerLN.existeCarro(id)) {
            System.out.println("Digite o nome da marca:");
            String marca = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite a potência de combustão:");
            int potencia = scanner.nextInt();
            System.out.println("Digite o PAC (valor entre 0 e 1):");
            float pac = scanner.nextFloat();
            scanner.nextLine();
            if (iRacingManagerLN.validaPac(pac)) {

                System.out.println("Digite a Tipo Pneu:");
                String tipopneu = scanner.nextLine();
                System.out.println("Digite a Downforce:");
                float downforce = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("Digite a Tipo Motor:");
                String motor = scanner.nextLine();
                iRacingManagerLN.adicionarC1(id, marca, modelo, potencia, pac, 6000, tipopneu, downforce, motor);
                System.out.println("Carro adicionado ao sistema!");
                menuPrincipal2();
            } else {
                System.out.println("Pac Errado!");
                menuPrincipal2();
            }
        }else System.out.println("Um carro com o mesmo ID já existe no sistema!");
    }

    public void trataCriaCarros(){
        menuTipoCarros();
    }

    public void trataRemoverCarros(){
        System.out.println("Digite o ID do carro que deseja remover:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        if(iRacingManagerLN.existeCarro(id)){
            iRacingManagerLN.removerCarro(id);
            System.out.println("Carro removido!");
            menuPrincipal2();
        } else {
            System.out.println("Esse carro não existe.");
            menuPrincipal2();
        }


    }

    public void trataJogar(){
        try {
            String userAtual = this.iRacingManagerLN.getCurrentUser();
            User u = this.iRacingManagerLN.getUser(userAtual);
            System.out.println("CAMPEONATOS DISPONÍVEIS:");
            List<String> l = iRacingManagerLN.getCampeonatos();
            System.out.println(l);
            System.out.println("Digite o campeonato a jogar:");
            Scanner scanner = new Scanner(System.in);
            String nomecampeonato = scanner.nextLine();
            Campeonato campeonato = iRacingManagerLN.getCampeonato(nomecampeonato);
            System.out.println("CARROS DISPONÍVEIS:");
            System.out.println("-----------------------------------------------");
            iRacingManagerLN.getCarros().forEach(x -> {
                System.out.println("ID: "+x.getIdCarro() + "| Marca-> " + x.getMarca() + " |  Modelo-> " + x.getModelo()+" |\n***** +INFO  *****\n Categoria:"+x.getCategoria()+"\n Potência:"+x.getPotenciaCombustao()+"cv"+"\n Cilindrada:"+x.getCilindrada()+" cm3");
                System.out.println("-----------------------------------------------");
            });
            System.out.println("Digite o ID do carro com que pretende jogar:");
            String carroid = scanner.nextLine();
            Carro carro = iRacingManagerLN.getCarro(carroid);
            clean();
            System.out.println("--------------------------");
            iRacingManagerLN.getAllPilotos().forEach(x -> {
                System.out.println("Piloto: "+x.getNome());
                System.out.println("Habilidades chuva/tempo seco: "+x.getCTS());
                System.out.println("Característica conservadora/agressiva: "+x.getSVA());
                System.out.println("--------------------------");
            });
            System.out.println("Digite o piloto com que pretende jogar:");
            String pilotoname = scanner.nextLine();
            Piloto piloto = iRacingManagerLN.getPiloto(pilotoname);
            if(iRacingManagerLN.validaNumInscricoes(nomecampeonato)) {
                iRacingManagerLN.adicionaInscricao(u, campeonato, carro, piloto);
                System.out.println("Inscrição para o jogo efetuada com sucesso.");
            }else{
                System.out.println("O campeonato atingiu o limite de jogadores.");
            }
        }catch (CampeonatoInexistenteException e){
            System.out.println(e.getMessage());
        }
    }


    public void trataConsultarRanking(){
        List<User> allUsers =iRacingManagerLN.getAllUsers();
        System.out.println("---------------RANKING-----------------");
        int p=1;
        for (User u : allUsers) {
            if(!u.getIsAdmin()){
            System.out.println("| "+p+"º- "+u.getUsername()+"  ("+u.getScore()+" pts)");
            p++;
            }
        }
        System.out.println("---------------------------------------");
    }

    public void trataConsultarRankingJogador(){
        System.out.println("Digite o jogador:");
        Scanner scanner = new Scanner(System.in);
        String jogador = scanner.nextLine();
        List<User> allUsers =iRacingManagerLN.getAllUsers();
        System.out.println("---------------RANKING-----------------");
        int p=1;
        for (User u : allUsers) {
            if(!u.getIsAdmin() && u.getUsername().equals(jogador)){
                System.out.println("\u001B[35m"+"| "+p+"º- "+u.getUsername()+"  ("+u.getScore()+" pts)"+"\u001B[35m");
                System.out.print("\u001B[33m");
                p++;
            } else if(!u.getIsAdmin() ){
                System.out.println("| "+p+"º- "+u.getUsername()+"  ("+u.getScore()+"pts)");
                p++;
            }
        }
        System.out.println("---------------------------------------");
    }

    public void clean(){
        int i;
        for(i=0;i<40;i++){
            System.out.print("\n");
        }
    }
}
