package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Humano extends Creature {
    protected int usados;
    protected Equipamento equipamento;
    protected boolean envenenado = false;
    protected boolean transformado = false;
    protected int zombiesDestruidos;


    public Humano(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(idTipo, id, x, y, nome, imagemPng);

    }

    @Override
    public String getImagePNG() {
        return "human.png";
    }

    public boolean getEnvenenado() {
        return envenenado;
    }

    public void envenenar() {
        envenenado = true;
    }

    public void curar() {
        envenenado = false;
    }

    public void transformar() {
        transformado = true;
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

    public String getNomeEquipa() {
        return "Os Vivos";
    }
}
