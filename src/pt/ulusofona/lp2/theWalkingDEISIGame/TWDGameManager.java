package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {
    int numeroLinhas;
    int numeroColunas;
    int idEquipa;
    int equipaAtual;
    int dias = 0;

    ArrayList<Zombie> zombies;
    ArrayList<Humano> humanos;
    ArrayList<Equipamento> equipamentos;

    public TWDGameManager() {
        this.humanos = new ArrayList<>();
        this.equipamentos = new ArrayList<>();
        this.zombies = new ArrayList<>();
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

                    idEquipa = Integer.parseInt(linha1);
                    equipaAtual = idEquipa;
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

                            Zombie z = new Zombie(idCriatura, idTipo, nomeCriatura, x, y);//colocar diretamente na classe humano e zombie epor causado x e y
                            zombies.add(z);

                        } else if (idTipo == 1) {
                            Humano humano = new Humano(idCriatura, idTipo, nomeCriatura, x, y, 0);
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
        return idEquipa;
    }

    public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean move(int xO, int yO, int xD, int yD) {
        if (validarCoordenadas(xO, yO, xD, yD) == false) {
            return false;
        }
        if (verificarCriaturaDestino(xD, yD)) {
            return false;
        }

        Equipamento equip = eEquipamento(xD, yD);
        //nao é uma criatura o destino
        if (equip == null) {
            //nao é um equipamento e nem é uma criatura logo é um espaço vazio
            mudarPosicaoCriatura(xO, yO, xD, yD);
        } else {
//logo é um equipamento
            if (equipaAtual == 0) {
                //equipa humano
                Humano h = getHumano(xO, yO);
                if (h.idEquipamento == 0) {
                    h.idEquipamento = equip.getId();
                } else {
                    largarEquipamento(xO, yO, equip, h);
                }
            } else {
                //equipa zombie
                destruirEquipamento(equip.id);
            }
        }

        mudarEquipaAtual();
        return true;
    }


    private Humano getHumano(int xO, int yO) {
        for (int b = 0; b < humanos.size(); b++) {
            if (humanos.get(b).x == xO && humanos.get(b).y == yO) {
                return humanos.get(b);
            }
        }
        return null;
    }


    private void largarEquipamento(int xO, int yO, Equipamento novoEquipamento, Humano h) {
        int equipamentoAntigo = h.idEquipamento;

        for (int a = 0; a < equipamentos.size(); a++) {
            if (equipamentos.get(a).id == equipamentoAntigo) {
                equipamentos.get(a).x = xO;
                equipamentos.get(a).y = yO;
            }
        }
        h.idEquipamento = novoEquipamento.id;

    }


    private void destruirEquipamento(int idEquipamento) {
        for (int a = 0; a < equipamentos.size(); a++) {
            if (equipamentos.get(a).id == idEquipamento && equipamentos.get(a).id == idEquipamento) {

                equipamentos.remove(idEquipamento);

            }
        }

    }


    private void mudarPosicaoCriatura(int xO, int yO, int xD, int yD) {
        if (idEquipa == 0) {
            for (int a = 0; a < humanos.size(); a++) {
                if (humanos.get(a).x == xO && humanos.get(a).y == yO) {
                    humanos.get(a).x = xD;
                    humanos.get(a).y = yD;
                }
            }
        } else {

            for (int a = 0; a < zombies.size(); a++) {
                if (zombies.get(a).x == xO && zombies.get(a).y == yO) {
                    zombies.get(a).x = xD;
                    zombies.get(a).y = yD;
                }
            }
        }


//Saber equipa que ezstá a joagar se for hiumano tenho de mudar a posiçao do humano se for zombie tenho de mudar a posição do zombie( if else). humano.x=xD 66 humano.y=yD   . else zombie igual como no humano.
// fazer o ciclo for()  humanos,get(a)=XO &&yo    er chamar o get() Caso do zombie igual

    }

    private boolean verificarCriaturaDestino(int xD, int yD) {

        for (int c = 0; c < zombies.size(); c++) {

            if (zombies.get(c).x == xD && zombies.get(c).y == yD) {
                return true;
            }
        }

        for (int c = 0; c < humanos.size(); c++) {

            if (humanos.get(c).x == xD && humanos.get(c).y == yD) {
                return true;
            }
        }
        return false;
    }


    private void mudarEquipaAtual() {
        if (idEquipa == 0) {
            idEquipa = 1;
        } else {
            idEquipa = 0;
        }

    }

    private boolean validarCoordenadas(int xO, int yO, int xD, int yD) {
        if (xO < 0 || yO < 0 || xD < 0 || yD < 0) {
            return false;
        }
        if (xD > (numeroLinhas - 1) || xO > (numeroLinhas - 1)) {
            return false;
        }
        if ((yD > numeroColunas - 1) || yO > (numeroColunas - 1)) {
            return false;
        }
        return true;
    }

    private Equipamento eEquipamento(int xD, int yD) {
        for (int a = 0; a < equipamentos.size(); a++) {
            if (equipamentos.get(a).x == xD && equipamentos.get(a).y == yD) {
                return equipamentos.get(a);
            }
        }
        return null;
    }


    public boolean gameIsOver() {
        return this.dias == 12;
    }

    public List<String> getAuthors() {
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Rodrigo Sousa");
        autores.add("Tomás Maia");
        return autores;
    }

    public int getCurrentTeamId() {
        return this.equipaAtual;
    }

    public int getElementId(int x, int y) {
        for (Zombie morto : this.zombies) {
            if (morto.x == x && morto.y == y) {
                return morto.idCriatura;
            }
        }
        for (Humano vivo : this.humanos) {
            if (vivo.x == x && vivo.y == y) {
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

    public List<String> getSurvivors() {
        ArrayList<String> survivors = new ArrayList<>();
        return survivors;
    }

    public boolean isDay() {
        return this.dias == 1 || this.dias % 2 == 0;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        for (Zombie morto : this.zombies) {
            if (creatureId == morto.idCriatura && morto.idCriatura == equipmentTypeId) {
                return true;
            }
        }
        for (Humano vivo : this.humanos) {
            if (creatureId == vivo.idCriatura && vivo.idCriatura == equipmentTypeId) {
                return true;
            }
        }
        return false;
    }

}

