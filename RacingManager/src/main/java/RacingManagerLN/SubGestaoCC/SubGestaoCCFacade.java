package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubGestaoCCFacade implements ISubGestaoCCFacade {
    private Map<String,Campeonato> allCampeonatos;
    private Map<String,Circuito> allCircuitos;

    public  SubGestaoCCFacade(){
        this.allCampeonatos = new HashMap<>();
        this.allCircuitos = new HashMap<>();
    }

    public boolean validaNomeCampeonato(String aNomeCampeonato) {
        return !this.allCampeonatos.containsKey(aNomeCampeonato);
    }

    public List<Circuito> getAllCircuitos() {
        return this.allCircuitos.values().stream().map(Circuito::clone).collect(Collectors.toList());
    }


    public List<String> allCircuitosNome(){
        return this.allCircuitos.values().stream().map(Circuito::getNomeCircuito).collect(Collectors.toList());
    }

    public boolean guardaCampeonato(String aNomeCampeonato, int aNjogadores,List<Circuito> l) {
        Campeonato c = new Campeonato(aNomeCampeonato,aNjogadores,l);
        boolean b = false;
        if(validaNomeCampeonato(aNomeCampeonato)){
            this.allCampeonatos.put(aNomeCampeonato,c.clone());
            b=true;
        }
        return b;
    }

    public List<Circuito> getCircuitosDoCampeonato(String aCampNome) {
        throw new UnsupportedOperationException();
    }

    public boolean updateCircuitoCampeonato(String aNomeCamp, String aCircNomeAntigo, String aCircNomeNovo) {
        return false;
    }

    public boolean apagaCircuitoDoCampeonato(String aCampNome, String aCircNome) {
        return false;
    }

    public void apagaCampeonato(String aCampNome) {
    }

    public Campeonato getCampeonato(String aNomeCampeonato) {

        return null;
    }

    public List<Campeonato> getCampeonatos() {
        return new ArrayList<>();
    }

    public boolean existeCircuitoemCampeonato(String aNomeCircuito) {
        return false;
    }

    public boolean existeCircuito(String aNomeCircuito) {
        return this.allCircuitos.containsKey(aNomeCircuito);
    }

    public int setPercurso(int aNumCurva, int aNumChicane) {
        return 0;
    }

    public boolean registarCircuito(String aNomeCircuito, double aDistancia, int aVoltas, int aNumRetas, int aNumChicanes, int aNumCurvas, List<Curva> aCurvas,List<Reta> aRetas,List<Chicane> aChicanes) {
        boolean b = false;
        if(!existeCircuito(aNomeCircuito)){
            Circuito c = new Circuito(aNomeCircuito,aDistancia,aVoltas,aNumRetas,aNumCurvas,aNumChicanes,aRetas,aCurvas,aChicanes);
            this.allCircuitos.put(aNomeCircuito,c.clone());
            b=true;
        }
        return b;
    }


    public Circuito getCircuito(String aNomeCircuito) {
        return this.allCircuitos.get(aNomeCircuito);
    }

    public void modificaCircuito(String aAntigoCircuito, Circuito aCircuito) {
    }

    public void removeCIrcuito(String aNomeCircuito) {

    }

    public String consultaCampeonato(String nomeCampeonato){
        return this.allCampeonatos.get(nomeCampeonato).toString();
    }
}