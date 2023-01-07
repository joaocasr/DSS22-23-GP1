package RacingManagerLN.SubGestaoJogos;

import RacingManagerLN.Exceptions.CampeonatoInexistenteException;
import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.C1;
import RacingManagerLN.SubGestaoCP.Carro.C2;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Carro.GT;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubGestaoJogosFacade implements ISubGestaoJogosFacade{
    private Map<String, List<Inscricao>> allInscricoes;

    public SubGestaoJogosFacade(){
        this.allInscricoes = new HashMap<>();
        User user1 = new User("tomas","tomaspass",false,0,"B");
        User user2 = new User("joao","joaopass",false,0,"B");
        User user3 = new User("renato","renatopass",false,0,"B");
        User user4 = new User("duarte","duartepass",false,0,"B");
        Campeonato campeonato = new Campeonato("UM-CAMP",3);
        Piloto p1 = new Piloto("Max Verstappen", 0.5F, 0.5F);
        Piloto p2 = new Piloto("Charles Leclerc", 0.4F, 0.6F);
        Piloto p3 = new Piloto("Lewis Hamilton", 0.3F, 0.7F);
        Piloto p4 = new Piloto("George Russell", 0.35F, 0.65F);

        C1 c1 = new C1("carro1","McLaren","M14A",6000,200,0.3F, Carro.tipoPneu.Duro,0.4F, Carro.modoMotor.Agressivo);
        C2 c2 = new C2("carro2","Mercedes","D23J",4000,230,0.4F, Carro.tipoPneu.Duro,0.5F, Carro.modoMotor.Agressivo);
        GT c3 = new GT("carro3","Ferrari","H34K",2500,210,0.4F, Carro.tipoPneu.Duro,0.6F, Carro.modoMotor.Agressivo);
        C2 c4 = new C2("carro4","Austin Martin","H45G",3000,240,0.6F, Carro.tipoPneu.Duro,0.5F, Carro.modoMotor.Agressivo);

        Inscricao i1 = new Inscricao(user1,campeonato,c1,p1);
        Inscricao i2 = new Inscricao(user2,campeonato,c2,p2);
        Inscricao i3 = new Inscricao(user3,campeonato,c3,p3);
        Inscricao i4 = new Inscricao(user4,campeonato,c4,p4);
        List<Inscricao> l = new ArrayList<>();
        l.add(i1);
        l.add(i3);
        l.add(i2);
        //l.add(i4);

        this.allInscricoes.put("UM-CAMP",l);
    }

    public void guardaEscolhasUser(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto) {
        Inscricao inscricao = new Inscricao(aUser,aCampeonato,aCarro,aPiloto);
        String nomeCampeonato= aCampeonato.getNomeCampeonato();
        try {
            if(getInscricoesCampeonato(nomeCampeonato)==null) {
                List<Inscricao> l = new ArrayList<>();
                l.add(inscricao);
                allInscricoes.put(nomeCampeonato, l);
            }else{
                List<Inscricao> l = allInscricoes.get(nomeCampeonato);
                l.add(inscricao);
                allInscricoes.put(nomeCampeonato, l);
            }
        } catch (CampeonatoInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Inscricao> getInscricoesCampeonato(String aNomeCampeonato) throws CampeonatoInexistenteException {
        if(allInscricoes.get(aNomeCampeonato)==null) throw new CampeonatoInexistenteException("O campeonato que digitou não existe no sistema.");
        return allInscricoes.get(aNomeCampeonato);
    }

    public List<String> getJogadoresASimular(String nomeCampeonato){
        return allInscricoes.get(nomeCampeonato).stream().map(x->x.getUser().getUsername()).collect(Collectors.toList());
    }

    public boolean hasCarroC2(String campeonato,String username){
        return (this.allInscricoes.get(campeonato).stream().anyMatch(x -> x.getUser().getUsername().equals(username) && x.getCarro() instanceof C2));
    }

    public void removeInscricoesCampeonato(String aNomeCampeonato) {
        allInscricoes.remove(aNomeCampeonato);
    }

    public String getTipoCarro(String aNomeCampeonato, String aUsername) {
        List<Inscricao> inscricoes = allInscricoes.get(aNomeCampeonato);
        String s="";
        for (Inscricao insc:inscricoes) {
            if(insc.getUser().getUsername().equals(aUsername)){
                s=insc.getCarro().getModelo();
            }
        }
        return  s;
    }

    public boolean validaNumeroInscricoes(String aNomeCampeonato) {
        return allInscricoes.get(aNomeCampeonato).size() < allInscricoes.get(aNomeCampeonato).get(0).getCampeonato().getParticipantes();
    }
}