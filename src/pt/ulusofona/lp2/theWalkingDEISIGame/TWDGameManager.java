package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {
    int numeroLinhas;
    int dias = 0;
    int numeroColunas;
    int idEquipaAtual;
    ArrayList<Zombie> zombies;
    ArrayList<Humano> humanos;
    ArrayList<Equipamento> equipamentos;

    public TWDGameManager() {

    }


    public boolean startGame(File ficheiroInicial) {
        String nomeFicheiro = "Ficheiro.txt";
        int numeroDaLinha = 0;
        try {

            Scanner leitorFicheiro = new Scanner(ficheiroInicial);

// enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {
                if (numeroDaLinha == 0) {
                    String linha0 = leitorFicheiro.nextLine();

                    String dados[] = linha0.split(" ");

                    numeroLinhas = Integer.parseInt(dados[0]);
                    numeroColunas = Integer.parseInt(dados[1]);
                    numeroDaLinha++;
                    continue;
                }


                if (numeroDaLinha == 1) {
                    String linha1 = leitorFicheiro.nextLine();

                    idEquipaAtual = Integer.parseInt(linha1);
                    numeroDaLinha++;
                    continue;

                }


                if (numeroDaLinha == 2) {

                    String linha2 = leitorFicheiro.nextLine();

                    int numeroCriaturas = Integer.parseInt(linha2);

                    for (int i = 0; i < numeroCriaturas; i++) {

                        String linha3 = leitorFicheiro.nextLine();

                        String dados1[] = linha3.split(" : ");

                        int idCriatura = Integer.parseInt(dados1[0]);
                        int idTipo = Integer.parseInt(dados1[1]);
                        String nomeCriatura = dados1[2];
                        int x = Integer.parseInt(dados1[3]);
                        int y = Integer.parseInt(dados1[4]);

                        if (idTipo == 0) {

                            Zombie z = new Zombie(idCriatura, idTipo, nomeCriatura, x, y);
                            zombies.add(z);

                        } else if (idTipo == 1) {
                            Humano humano = new Humano(idCriatura, idTipo, nomeCriatura, x, y,0);
                            humanos.add(humano);
                        }


                        numeroDaLinha++;


                    }
                    continue;


                }

                int numeroEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());


                for (int i = 0; i < numeroEquipamentos; i++) {

                    String linha3 = leitorFicheiro.nextLine();

                    String dados1[] = linha3.split(" : ");

                    int idEquipamento = Integer.parseInt(dados1[0]);
                    int idTipo = Integer.parseInt(dados1[1]);
                    int x = Integer.parseInt(dados1[2]);
                    int y = Integer.parseInt(dados1[3]);


                    Equipamento equipamento = new Equipamento(idEquipamento, idTipo, x, y);
                    equipamentos.add(equipamento);

                    numeroDaLinha++;


                }


            }
            leitorFicheiro.close();
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";
            System.out.println(mensagem);
            return false;
        }
//percorrer a lista com os equipamentos e percorrer a lista com humanos-- isto por causa da func move
        return true;
    }

    public int[] getWorldSize() {

        int[] arr = new int[2];
        arr[0] = numeroLinhas;
        arr[1] = numeroColunas;
        return arr;
    }

    public int getInitialTeam() {
        return idEquipaAtual;
    }

    public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
/*
    public boolean move(int xO, int yO, int xD, int yD) {

        if (idTipo==0) {


            if (xD < numeroLinhas && yD < numeroColunas) {
                for (int i = 0; i < zombies.size(); i++) {

                }

            } else {
                return false;
            }
//xd e yd sao validos dentro do mapa(if e verificar se o tamanh do xd ou yd  sao maiores que o numero de linhas e colunas returnando falso)
            // depois de ser valida(verificar se existe alguma criatura no xd e yd ,(percorrendo a lista de zombie e humano  atraves do for e verificar se existe ) sendo a jogada invalida return falso
            //Agora a jogada já é valida
            // se xo e yo for as coordendas de zombie e se o equipamento for xd yd  elimino o equipamento fazendo remove. na lista do equipamento. o zombie passa  a ter as coordenadas do equipamento removido


            //Agora tenho de fazer para o caso do humano
            // se o idequipamento da classe humano for diefrente de zreo quer dizer que tem, se tiverr tem que largar o equipamento(guardar o equipamento na casa de origem xo yo)
            //se nao tiver idequipamento vai ficar na sua posse

            return true;
        }

    }

 */
public boolean gameIsOver(){
    return this.dias == 12;
}

public List<String> getAuthors(){
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Rodrigo Sousa");
        autores.add("Tomás Maia");
        return autores;
    }
    public int getCurrentTeamId(){
        return this.idEquipaAtual;
    }

    public int getElementId(int x, int y) {
        for (Zombie morto : this.zombies){
            if (morto.x == x && morto.y == y){
                return morto.idCriatura;
            }
        }
        for (Humano vivo : this.humanos){
            if (vivo.x == x && vivo.y == y){
                return vivo.idCriatura;
            }
        }
        for (Equipamento ferramenta : this.equipamentos) {
            if (ferramenta.x == x && ferramenta.y == y) {
                    return ferramenta.id;
                }
            }
        return 0;
        }

    public List<String> getSurvivors(){
        ArrayList<String> survivors = new ArrayList<>();
        return survivors;
    }

    public boolean isDay(){
        return this.dias == 1 || this.dias % 2 == 0;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId){
        for (Zombie morto : this.zombies){
            if (creatureId == morto.idCriatura && morto.idCriatura == equipmentTypeId){
                return true;
            }
        }
        for (Humano vivo : this.humanos){
            if (creatureId == vivo.idCriatura && vivo.idCriatura == equipmentTypeId){
                return true;
            }
        }
        return false;
    }
}
