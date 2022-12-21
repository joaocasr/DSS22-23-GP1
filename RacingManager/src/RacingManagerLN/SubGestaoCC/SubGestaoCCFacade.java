package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.Exceptions.CampeonatoInexistenteException;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;
import data.CampDAO;
import data.CircuitoDAO;

import java.util.*;
import java.util.stream.Collectors;

public class SubGestaoCCFacade implements ISubGestaoCCFacade {
    private Map<String,Campeonato> allCampeonatos;
    private Map<String,Circuito> allCircuitos;

    public SubGestaoCCFacade(){
        this.allCampeonatos = CampDAO.getInstance();
        this.allCircuitos = CircuitoDAO.getInstance();
    }

    public boolean validaNomeCampeonato(String aNomeCampeonato) {
        return !this.allCampeonatos.containsKey(aNomeCampeonato);
    }

    public List<Circuito> getAllCircuitos() {
        return this.allCircuitos.values().stream().map(Circuito::clone).collect(Collectors.toList());
    }


    public List<String> allCircuitosNome(){
        return new ArrayList<>(this.allCircuitos.keySet());
    }

    public boolean removeCampeonato(String campeonato){
        boolean b = true;
        if(this.allCampeonatos.remove(campeonato)==null) b=false;
        return b;
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
        Campeonato camp =  this.allCampeonatos.get(aCampNome);
        if(camp == null) return null;
        return camp.getCircuitos();
    }

    public boolean updateCircuitoCampeonato(String aNomeCamp, String aCircNomeAntigo, String aCircNomeNovo) {
        Campeonato camp = this.allCampeonatos.get(aNomeCamp);
        if(camp == null) return false;

        if(!camp.circuitoExiste(aCircNomeAntigo)) return false;

        camp.removeCircuito(aCircNomeAntigo);
        Circuito cir = this.allCircuitos.get(aCircNomeNovo);
        if(cir == null) return false;
        camp.addCircuito(cir);

        return true;
    }

    public boolean apagaCircuitoDoCampeonato(String aCampNome, String aCircNome) {
        Campeonato camp = this.allCampeonatos.get(aCampNome);
        if(camp == null) return false;
        if(!camp.circuitoExiste(aCircNome)) return false;

        camp.removeCircuito(aCircNome);

        return true;
    }

    public List<String> getNomesCampeonatos(){
        Collection<Campeonato> l = this.allCampeonatos.values();
        return l.stream().map(Campeonato::getNomeCampeonato).collect(Collectors.toList());
    }
    public void apagaCampeonato(String aCampNome) {
        this.allCampeonatos.remove(aCampNome);
    }

    public Campeonato getCampeonato(String aNomeCampeonato) throws CampeonatoInexistenteException {
        if(this.allCampeonatos.get(aNomeCampeonato)==null) throw new CampeonatoInexistenteException("O campeonato que digitou não existe.");
        return this.allCampeonatos.get(aNomeCampeonato);
    }

    public List<Campeonato> getCampeonatos() {
        return this.allCampeonatos.values().stream().toList();
    }

    public boolean existeCircuitoEmCampeonato(String aNomeCircuito) {
        //Primeiro buscamos a lista de campeonatos
        //depois percorremos todos os campeonatos e verificamos se algum deles contem o circuito
        return  this.getCampeonatos().stream().anyMatch((camp) -> camp.circuitoExiste(aNomeCircuito));
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
        this.allCircuitos.put(aAntigoCircuito, aCircuito);
    }

    public boolean removeCIrcuito(String aNomeCircuito) {
        boolean b= true;
        if(this.allCircuitos.remove(aNomeCircuito)==null) b=false;
        return b;
    }

    public String consultaCampeonato(String nomeCampeonato){
        return this.allCampeonatos.get(nomeCampeonato).toString();
    }
}