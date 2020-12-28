package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Cao extends Humano {

    int deslocamento = 2;

    public Cao(int idTipo, int id, String nome, int x, int y, String imagemPng) {
        super(idTipo, id, nome, x, y, imagemPng);
    }
    @Override
    public String toString() {
        return id + " | " + "Cão" + " | " + "Os Vivos" + " | " + nome + " " + usados + " @ " + salvo();
    }
    @Override
    public boolean comportamentos(int xD, int yD, boolean diaNoite) {

        if (Math.abs((xD - x)) > deslocamento) {
            return false;
        }
        if (Math.abs((yD - y)) > deslocamento) {
            return false;
        }

        if (x == xD || y == yD) {
            return false;
        }


        return true;
    }
}
