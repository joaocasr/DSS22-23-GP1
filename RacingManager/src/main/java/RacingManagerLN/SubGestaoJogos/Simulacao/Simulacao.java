package RacingManagerLN.SubGestaoJogos.Simulacao;

import RacingManagerLN.SubGestaoCC.Circuito.*;
import RacingManagerLN.SubGestaoCP.Carro.*;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoJogos.Inscricao;

import java.util.*;
import java.util.stream.Collectors;

public class Simulacao implements Clima {
    private Map<String, Integer> numConf;
    private Map<String, Integer> pontuacoes;
    private Configuracao allConfiguracoes;
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

    public Simulacao(Circuito c, List<Inscricao> allInscricoes){//simulacao base
        int numVoltas = c.getVoltas();
        int clima = this.clima();
        this.showPartida(c.posicionaJogadores(allInscricoes.stream().map(x->x.getPiloto().getNome()+"-"+x.getUser().getUsername()).collect(Collectors.toList())),clima,c.getNomeCircuito());
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
        c.posicionaJogadores(posicoes);

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



    public Map<String, Integer> getnumConf() {
        return numConf;
    }

    public void setnumConf(Map<String, Integer> numConf) {
        this.numConf = numConf;
    }

    public Map<String, Integer> getpontuacoes() {
        return pontuacoes;
    }

    public void setpontuacoes(Map<String, Integer> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }

    public Configuracao getallConfiguracoes() {
        return allConfiguracoes;
    }

    public void setallConfiguracoes(Configuracao allConfiguracoes) {
        this.allConfiguracoes = allConfiguracoes;
    }

    public List<Inscricao> getinscricoesCampeonato() {
        return inscricoesCampeonato;
    }

    public void setinscricoesCampeonato(List<Inscricao> inscricoesCampeonato) {
        this.inscricoesCampeonato = inscricoesCampeonato;
    }
}