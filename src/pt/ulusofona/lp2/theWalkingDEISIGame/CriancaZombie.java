package pt.ulusofona.lp2.theWalkingDEISIGame;

public class CriancaZombie extends Zombie {
    int deslocamento = 1;
    String turno = "Todos";

    public CriancaZombie(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }
    @Override
    public String toString() {

        return id + " | " + "Criança (Zombie)" + " | " + "Os Outros" + " | " + nome + " " + getToolsDestroy() +  " @ " + salvo();
    }
    @Override
    public boolean comportamentos(int xD, int yD, boolean diaNoite, boolean safeHaven) {
        if (safeHaven == true) {
            return false;

        }
        if (Math.abs((xD - x)) > deslocamento) {
            return false;
        }
        if (Math.abs((yD - y)) > deslocamento) {
            return false;
        }

        if (x != xD && y != yD) {
            return false;
        }


        return true;
    }
}
