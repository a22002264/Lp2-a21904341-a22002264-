package pt.ulusofona.lp2.theWalkingDEISIGame;

public class AdultoVivo extends Humano {
    int deslocamento = 2;
    String turno = "Todos";


    public AdultoVivo(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }
    @Override
    public String toString() {
        if (transformado){
            return id + " | " + "Adulto (Zombie)" + " | " + "Os Outros" + " | " + nome + " "+toolsDestroy+" @ "+salvo();
        }else{
            return id + " | " + "Adulto (Vivo)" + " | " + "Os Vivos" + " | " + nome + " " + usados + " @ " + salvo();
        }
    }

    @Override
    public boolean comportamentos(int xD, int yD, boolean diaNoite,boolean safeHaven) {

        if (Math.abs((xD - x)) > deslocamento) {
            return false;
        }
        if (Math.abs((yD - y)) > deslocamento) {
            return false;
        }


        return true;
    }


}