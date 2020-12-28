package pt.ulusofona.lp2.theWalkingDEISIGame;

public class AdultoZombie extends Zombie {
    int deslocamento = 2;
    String turno = "Todos";


    public AdultoZombie(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }
    @Override
    public String toString() {

        return id + " | " + "Adulto (Zombie)" + " | " + "Os Outros" + " | " + nome + " " + totalEquipDestrui + " @ " + salvo();
    }
    @Override
    public boolean comportamentos(int xD, int yD, boolean diaNoite) {


        if (Math.abs((xD - x)) > deslocamento) {
            return false;
        }
        if (Math.abs((yD - y)) > deslocamento) {
            return false;
        }


        return true;
    }
}
