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

    public Simulacao(Circuito c, List<Inscricao> allInscricoes,List<Configuracao> allConfiguracoes) throws InterruptedException {//simulacao base
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
            System.out.println("David Croft (Comentador) : Vamos para a volta nº"+(i+1));
            Thread.sleep(2000);
            if(i==numVoltas-1) System.out.println("David Croft (Comentador) : Vamos para a última volta!!!");
            Thread.sleep(2000);
            for (String p : percurso) {
                System.out.println("David Croft (Comentador) : Estamos a chegar à "+p+" do circuito");
                Thread.sleep(2000);
                int gdu = -1;
                if (c.getChicane(p) != null) gdu = c.getChicane(p).getGdu();
                else if (c.getCurva(p) != null) gdu = c.getCurva(p).getGdu();
                else if (c.getReta(p) != null) gdu = c.getReta(p).getGdu();
                int j=allInscricoes.size()-1;
                while(j>=1){
                    Carro carro2 = allInscricoes.get(j).getCarro();
                    Piloto piloto2 = allInscricoes.get(j).getPiloto();
                    String user2 = allInscricoes.get(j).getUser().getUsername();
                    Carro carro1 = allInscricoes.get(j-1).getCarro();
                    Piloto piloto1 = allInscricoes.get(j-1).getPiloto();
                    if (!this.consegueEfetuarPercurso(gdu, carro2, piloto2, clima) && !acidentados.contains(carro2)){
                        acidentados.add(carro2);
                        relataAcidente(piloto2.getNome());
                    }
                    if (!this.consegueEfetuarPercurso(gdu, carro1, piloto1, clima) && !acidentados.contains(carro2)){
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

                        if(calculaQualidade(gdu,piloto2.getSVA(),piloto2.getCTS(),fiabilidade2,carro2.getPac(),carro2.getDownforce(),carro2.getPotenciaCombustao(),clima)>
                                calculaQualidade(gdu,piloto1.getSVA(),piloto1.getCTS(),fiabilidade1,carro1.getPac(),carro1.getDownforce(),carro1.getPotenciaCombustao(),clima)) ultrapassagem[j]=true;
                    }
                    else if(acidentados.contains(carro2)) ultrapassagem[j]=false;
                    else if(acidentados.contains(carro1) && !acidentados.contains(carro2)) ultrapassagem[j]=true;
                    j--;
                }
                ultrapassagem[0]=false;
                posicoes=allInscricoes.stream().map(x->x.getPiloto().getNome()+"-"+x.getUser().getUsername()).collect(Collectors.toList());
                for(int insc=1;insc<allInscricoes.size();insc++){
                    if(ultrapassagem[insc]){
                        Collections.swap(posicoes,insc,insc-1);
                        Collections.swap(allInscricoes,insc,insc-1);
                        relataUltrapassagem(posicoes.get(insc-1),posicoes.get(insc));
                    }
                }
                int k;
                for(k=0;k<ultrapassagem.length;k++) ultrapassagem[k]=false;
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
        for(i=0;i<N;i++){
            this.ordemPilotos.put(i+1,jogadores.get(i));
        }
        return this.ordemPilotos;
    }


    public double calculaQualidade(int gdu,double sva,double cts,double fiabilidade,float pac,float downforce,int potencia,int clima){
        double qualidade =0;
        double pot = (double) potencia/10000;
        if(clima==1 && cts<0.5 && downforce>0.5){
            qualidade= (-cts+ sva)*2*(-0.5-gdu) + fiabilidade + pac - downforce + pot ;
        }
        if(clima==1 && cts<0.5 && downforce<0.5 || clima==0 && cts>0.5 && sva<0.5 && downforce>0.5){
            qualidade= (-cts+ sva)*2*(-0.5-gdu) + fiabilidade + pac + downforce + pot ;
        }
        if(clima==1 && cts>0.5 && downforce>0.5){
            qualidade= (cts+ sva)*2*(-0.5-gdu) + fiabilidade + pac - downforce + pot ;
        }
        if(clima==1 && cts>0.5 && downforce<0.5 || clima==0 && cts<0.5 && sva<0.5 && downforce<0.5){
            qualidade= (cts+ sva)*2*(-0.5-gdu) + fiabilidade + pac + downforce + pot ;
        }
        if(clima==0 && cts>0.5 && sva>0.5 && downforce>0.5){
            qualidade= (-cts- sva)*2*(-0.5-gdu) + fiabilidade + pac - downforce + pot ;
        }
        if(clima==0 && cts>0.5 && sva>0.5 && downforce<0.5){
            qualidade= (cts- sva)*2*(-0.5-gdu) + fiabilidade + pac - downforce + pot ;
        }
        return qualidade;
    }

    public boolean consegueEfetuarPercurso(int gdu,Carro carro,Piloto piloto,int clima){
        //  GDU: Possível=0 Difícil=1 Impossível=2
        boolean consegue=true;
        if(gdu!=0 && clima==0 && carro.getTipoPneu().equals(Carro.tipoPneu.Macio)) consegue=false;
        if(gdu!=0 && clima==0 && (piloto.getCTS()>0.8 || piloto.getSVA()>0.8)) consegue=false;
        if(gdu!=0 && piloto.getSVA()>0.7) consegue=false;
        if(clima==0 && carro.getDownforce()<0.3) consegue=false;
        return consegue;
    }

    public void showPartida(Map<Integer,String> posicoes,int clima,String nomeCircuito) throws InterruptedException {
        System.out.println("David Croft (comentador): Olá a todos. Sejam bem vindos ao autódromo de "+ nomeCircuito+"!");
        Thread.sleep(3000);
        if(clima==1){
            System.out.println("James Allen (comentador): Hoje faz um dia bonito de sol. Não achas David?");
            Thread.sleep(3000);
            System.out.println("David Croft (comentador): Sim. Sem dúvida Allen.");
            Thread.sleep(3000);
        }
        else if(clima==0){
            System.out.println("James Allen (comentador): Hoje faz um dia de chuva, pelo que os pilotos terão de ter em atenção o piso escorregadio.");
            Thread.sleep(3000);
        }
        System.out.println("James Allen (comentador):Já temos a confirmação das posições iniciais.");
        System.out.println("---------------------------------");
        for(Map.Entry<Integer,String> m: posicoes.entrySet()){
            System.out.println(m.getKey()+"º - "+m.getValue());
        }
        System.out.println("---------------------------------");
        Thread.sleep(3000);
        System.out.println("David Croft (comentador): Vamos então dar início à corrida.");
        Thread.sleep(3000);
    }

    public void relataAcidente(String piloto) throws InterruptedException {
        System.out.println("David Croft (comentador): Oh não! Que péssima notícia...o piloto "+piloto+" sofreu um acidente.");
        Thread.sleep(3000);
    }

    public void relataUltrapassagem(String piloto1,String piloto2) throws InterruptedException {
        System.out.println("David Croft (comentador): WOW! "+piloto1+" ultrapassa "+piloto2);
        Thread.sleep(3000);
    }

    public void showResultados(List<String> posicoes) throws InterruptedException {
        System.out.println("David Croft (comentador): Que incrivel corrida! Passámos a mostrar os resultados:");
        Thread.sleep(3000);
        int lugar=1;
        System.out.println("---------------------------------");
        for(String p : posicoes){
            System.out.println(lugar+"º -"+p);
            lugar++;
        }
        System.out.println("---------------------------------");
        Thread.sleep(3000);
        System.out.println("David Croft (comentador): Daqui David Croft. Esperámos encontrá-lo na próxima corrida.");
        Thread.sleep(3000);
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
        int height = 30;
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
        System.out.println(".");
        System.out.print("\u001B[33m");
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