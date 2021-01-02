package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Humano extends Creature {
    protected int usados;
    protected Equipamento equipamento;

    public Humano(int idTipo, int id, String nome, int x, int y, String imagemPng) {
        super(idTipo, id, x, y, nome, imagemPng);

    }

    @Override
    public String getImagePNG() {
        return "human.png";
    }





    public int getUsados() {
        return usados;
    }

    public void setUsados(int usados) {
        this.usados = usados;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getNomeEquipa(){
        return "Os Vivos";
    }
}
