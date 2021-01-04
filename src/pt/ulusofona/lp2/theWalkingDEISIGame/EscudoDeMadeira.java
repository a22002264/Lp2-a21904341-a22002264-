package pt.ulusofona.lp2.theWalkingDEISIGame;

public class EscudoDeMadeira extends Equipamento {
    private int durabilidade = 1;
    boolean usoMilitar = false;


    public EscudoDeMadeira(int id, int idTipo, int x, int y, String titulo, String imagemPng,String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }

    public void setDurabilidadeMilitar(){
        durabilidade = 2;
        usoMilitar = true;
    }

    public void tirarDurabilidade(){
        if(durabilidade != 0){
            durabilidade -= 1;
        }
    }

    public int getDurabilidade(){
        return this.durabilidade;
    }
}