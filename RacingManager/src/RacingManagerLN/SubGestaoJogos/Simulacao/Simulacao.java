package RacingManagerLN.SubGestaoJogos.Simulacao;

import RacingManagerLN.SubGestaoCC.Circuito.*;
import RacingManagerLN.SubGestaoCP.Carro.*;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoJogos.Inscricao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Simulacao implements Clima {
    private Map<String, Integer> numConf;
    private Map<String, Integer> score; //username - pontuacao
    private Map<Integer,String> ordemPilotos; //posicao-piloto(username)
    private final List<Integer> pontuacoes = Arrays.asList(12,10,8,7,6,5,4,3,2,1,0);
    private Map<String,Configuracao> allConfiguracoes;
    private List<Inscricao> inscricoesCampeonato;

    @Override
    public double probabilidadePrecipitacao() {
        Random prob = new Random();
        return (double)prob.nextInt(0,100)/100;
    }

    @Override
    public int clima() {
        //0:chuva 1:sol
        int clima=0;
        Random rain = new Random();
        if(this.probabilidadePrecipitacao()>(double)rain.nextInt(0,100)/100) clima=1;
        return clima;
    }

    public Simulacao(Circuito c, List<Inscricao> allInscricoes,List<Configuracao> allConfiguracoes){//simulacao base
        if(allConfiguracoes.size()!=0){
            allInscricoes=alteraConfiguracoes(allInscricoes,allConfiguracoes);
        }
        this.ordemPilotos = new HashMap<>();
        this.score=new HashMap<>();
        int numVoltas = c.getVoltas();
        int clima = this.clima();
        this.showPartida(posicionaJogadores(allInscricoes.stream().map(x->x.getPiloto().getNome()+"-"+x.getUser().getUsername()).collect(Collectors.toList())),clima,c.getNomeCircuito());
        //circuito GDU:GRAU DIFICULDADE DE ULTRAPASSAGEM
        //PILOTO : CTS-melhor desempenho em tempo seco > 0.5 SVA-arrica pouco quando <0.5
        //CARRO: fiabilidade
        List<String> percurso = c.constroiPercurso();
        List<String> posicoes=new ArrayList<>();
        Map<Integer,String> posicoes2 = new HashMap<>();
        boolean [] ultrapassagem = new boolean[allInscricoes.size()];
        List<Carro> acidentados = new ArrayList<>();
        double fiabilidade1=0;
        double fiabilidade2=0;
        int i;
        for(i=0;i<numVoltas;i++) {
            for (String p : percurso) {
                int gdu = -1;
                if (c.getChicane(p) != null) gdu = c.getChicane(p).getGdu();
                else if (c.getCurva(p) != null) gdu = c.getCurva(p).getGdu();
                else if (c.getReta(p) != null) gdu = c.getReta(p).getGdu();
                int j=allInscricoes.size()-1;
                while(j>1){
                    Carro carro2 = allInscricoes.get(j).getCarro();
                    Piloto piloto2 = allInscricoes.get(j).getPiloto();
                    String user2 = allInscricoes.get(j).getUser().getUsername();
                    Carro carro1 = allInscricoes.get(j-1).getCarro();
                    Piloto piloto1 = allInscricoes.get(j-1).getPiloto();
                    if (!this.consegueEfetuarPercurso(gdu, carro2, piloto2, clima)){
                        acidentados.add(carro2);
                        relataAcidente(piloto2.getNome());
                    }
                    if (!this.consegueEfetuarPercurso(gdu, carro1, piloto1, clima)){
                        acidentados.add(carro1);
                        relataAcidente(piloto1.getNome());
                    }
                    if(!acidentados.contains(carro1) && !acidentados.contains(carro2)){
                        if(carro1 instanceof C1) fiabilidade1=((C1) carro1).getFiabilidade();
                        else if(carro1 instanceof C2) fiabilidade1=((C2) carro1).getFiabilidade();
                        else if(carro1 instanceof GT) fiabilidade1=((GT) carro1).getFiabilidade(i);
                        else if(carro1 instanceof SC) fiabilidade1=((SC) carro1).getfiabilidade(piloto1.getSVA(),piloto1.getCTS());

                        if(carro2 instanceof C1) fiabilidade2=((C1) carro2).getFiabilidade();
                        else if(carro2 instanceof C2) fiabilidade2=((C2) carro2).getFiabilidade();
                        else if(carro2 instanceof GT) fiabilidade2=((GT) carro2).getFiabilidade(i);
                        else if(carro2 instanceof SC) fiabilidade2=((SC) carro2).getfiabilidade(piloto2.getSVA(),piloto2.getCTS());

                        if(clima==1 && fiabilidade1<fiabilidade2 && piloto1.getCTS()>piloto2.getCTS()) ultrapassagem[j]=false;
                        if(clima==1 && fiabilidade1<fiabilidade2 && piloto1.getCTS()<piloto2.getCTS()) ultrapassagem[j]=true;
                        if(clima==1 && fiabilidade2>fiabilidade1 && piloto1.getCTS()>piloto2.getCTS()) ultrapassagem[j]=false;
                        if(clima==1 && fiabilidade2>fiabilidade1 && piloto1.getCTS()<piloto2.getCTS()) ultrapassagem[j]=true;

                        if(clima==0 && fiabilidade1<fiabilidade2 && piloto1.getCTS()>piloto2.getCTS()) ultrapassagem[j]=true;
                        if(clima==0 && fiabilidade1<fiabilidade2 && piloto1.getCTS()<piloto2.getCTS()) ultrapassagem[j]=false;
                        if(clima==0 && fiabilidade2>fiabilidade1 && piloto1.getCTS()>piloto2.getCTS()) ultrapassagem[j]=true;
                        if(clima==0 && fiabilidade2>fiabilidade1 && piloto1.getCTS()<piloto2.getCTS()) ultrapassagem[j]=false;
                    }
                    else if(acidentados.contains(carro2)) ultrapassagem[j]=false;
                    else if(acidentados.contains(carro1) && !acidentados.contains(carro2)) ultrapassagem[j]=true;
                    j--;
                }
                ultrapassagem[0]=false;
                posicoes=allInscricoes.stream().map(x->x.getPiloto().getNome()+"-"+x.getUser().getUsername()).collect(Collectors.toList());
                for(int insc=1;insc<allInscricoes.size();insc--){
                    if(ultrapassagem[i]){
                        Collections.swap(posicoes,i,i-1);
                        relataUltrapassagem(posicoes.get(i),posicoes.get(i-1));
                    }
                }
            }
        }
        int jscore;
        for(jscore=0;jscore<posicoes.size();jscore++){
            this.score.put(posicoes.get(jscore).split("-")[1],pontuacoes.get(jscore));
        }
        showResultados(posicoes);
    }

    public Map<Integer,String> posicionaJogadores(List<String> jogadores){
        int N = jogadores.size();
        int i;
        for(i=1;i<=N;i++){
            this.ordemPilotos.put(i,jogadores.get(i));
        }
        return this.ordemPilotos;
    }


    public boolean consegueEfetuarPercurso(int gdu,Carro carro,Piloto piloto,int clima){
        //  GDU: Possível=0 Difícil=1 Impossível=2
        boolean consegue=true;
        if(gdu!=0 && clima==0 && carro.getTipoPneu().equals(Carro.tipoPneu.Macio)) consegue=false;
        if(gdu!=0 && clima==0 && (piloto.getCTS()>0.5 || piloto.getSVA()>0.5)) consegue=false;
        if(gdu!=0 && piloto.getSVA()>0.6) consegue=false;
        if(clima==0 && carro.getDownforce()<0.4) consegue=false;
        return consegue;
    }

    public void showPartida(Map<Integer,String> posicoes,int clima,String nomeCircuito){
        System.out.println("David Croft (comentador): Olá a todos. Sejam bem vindos ao autódromo de "+ nomeCircuito+"!");
        if(clima==1){
            System.out.println("James Allen (comentador): Hoje faz um dia bonito de sol. Não achas David?");
            System.out.println("David Croft (comentador): Sim. Sem dúvida Allen.");
        }
        else if(clima==0){
            System.out.println("James Allen (comentador): Hoje faz um dia de chuva, pelo que os pilotos terão de ter em atenção o piso escorregadio.");
        }
        System.out.println("James Allen (comentador):Já temos a confirmação das posições iniciais.");
        for(Map.Entry<Integer,String> m: posicoes.entrySet()){
            System.out.println(m.getKey()+" - "+m.getValue());
        }
        System.out.println("David Croft (comentador): Vamos então dar início à corrida.");
    }

    public void relataAcidente(String piloto){
        System.out.println("David Croft (comentador): Oh não! Que péssima notícia...o piloto "+piloto+" sofreu um acidente.");
    }

    public void relataUltrapassagem(String piloto1,String piloto2){
        System.out.println("David Croft (comentador): WOW! Acabámos de assistir a uma incrível ultrapassagem do "+piloto1+"."+piloto2+" está a perder terreno e fica atrás do "+piloto1);
    }

    public void showResultados(List<String> posicoes){
        System.out.println("David Croft (comentador): Que incrivel corrida! Passo a mostrar os resultados:");
        int lugar=1;
        for(String p : posicoes){
            System.out.println(lugar+"º -"+p);
        }
        System.out.println("David Croft (comentador): Daqui David Croft. Esperemos encontra-lo na proxima corrida.");
    }

    public Map<String,Integer> getScore(){
        return this.score;
    }

    public List<Inscricao> alteraConfiguracoes(List<Inscricao> inscricoes,List<Configuracao> configuracoes) {
        for(Configuracao c: configuracoes){
            for(Inscricao i : inscricoes){
                if(c.getUsername().equals(i.getUser().getUsername()) && i.getCarro() instanceof C2){
                    i.getCarro().setDownforce(c.getDownforce());
                    i.getCarro().setModoMotor(c.getModomotor());
                    i.getCarro().setTipopneu(c.getTipopneu());
                    ((C2) i.getCarro()).setAfinacao(c.getAfinacao());
                }
                else{
                    i.getCarro().setDownforce(c.getDownforce());
                    i.getCarro().setModoMotor(c.getModomotor());
                    i.getCarro().setTipopneu(c.getTipopneu());
                }
            }
        }
        return inscricoes;
    }
    public Simulacao(){};
    public void showGameLogo(String message) throws InterruptedException {
        int height = 35;
        int width =350;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString(message, 12, 24);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "@");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println("\u001B[36m"+sb+"\u001B[36m");
        }
        System.out.print("\nA CARREGAR JOGO");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        System.out.print("\033[0m");
    }

    public void setallConfiguracoes(Configuracao allConfiguracoes) {
    }

    public List<Inscricao> getinscricoesCampeonato() {
        return inscricoesCampeonato;
    }

    public void setinscricoesCampeonato(List<Inscricao> inscricoesCampeonato) {
        this.inscricoesCampeonato = inscricoesCampeonato;
    }
}