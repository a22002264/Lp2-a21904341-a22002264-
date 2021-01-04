package pt.ulusofona.lp2.theWalkingDEISIGame;

public class MilitarVivo extends Humano {
    int deslocamento = 3;
    String turno = "Todos";


    public MilitarVivo(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }

    @Override
    public String toString() {
        if (transformado){
            return id + " | " + "Militar (Zombie)" + " | " + "Os Outros" + " | " + nome + " "+toolsDestroy+" @ "+salvo();
        }else{
            return id + " | " + "Militar (Vivo)" + " | " + "Os Vivos" + " | " + nome + " " + usados + " @ " + salvo();
        }
    }

    @Override
    public boolean comportamentos(int xD, int yD, boolean diaNoite, boolean safeHaven) {
        if (Math.abs((xD - x)) > deslocamento) {
            return false;
        }
        if (Math.abs((yD - y)) > deslocamento) {
            return false;
        }

        return true;
    }

}
