package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {
    int turno = 0;
    int inicialTeam;
    int diasDiaNoite = 2;
    boolean isDay = true;
    int numeroColunas;
    int numeroLinhas;
    int equipaAtual;
    //ArrayList<Zombie> zombies;
    //ArrayList<Humano> humanos;
    ArrayList<Equipamento> equipamentos;
    ArrayList<Creature> criaturas;
    ArrayList<SafeHaven> houses;

    public TWDGameManager() {
        //  this.humanos = new ArrayList<>();
        this.criaturas = new ArrayList<>();
        this.equipamentos = new ArrayList<>();
        this.houses = new ArrayList<>();
        // this.zombies = new ArrayList<>();
    }

    public boolean startGame(File ficheiroInicial) {
        String nomeFicheiro = "Ficheiro.txt";
        int numeroDaLinha = 0;
        try {
            Scanner leitorFicheiro = new Scanner(ficheiroInicial);
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
                    inicialTeam = Integer.parseInt(linha1);
                    equipaAtual = Integer.parseInt(linha1);
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
                            Zombie z = new CriancaZombie(idCriatura, idTipo, nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z);
                        } else if (idTipo == 1) {
                            Zombie z1 = new AdultoZombie(idCriatura, idTipo, nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z1);
                        } else if (idTipo == 2) {
                            Zombie z2 = new MilitarZombie(idCriatura, idTipo, nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z2);
                        } else if (idTipo == 3) {
                            Zombie z3 = new IdosoZombie(idCriatura, idTipo, nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z3);
                        } else if (idTipo == 4) {
                            Zombie z4 = new ZombieVampiro(idCriatura, idTipo, nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z4);
                        } else if (idTipo == 5) {
                            Humano a = new CriancaVivo(idCriatura, idTipo, nomeCriatura, x, y, "human.png");
                            criaturas.add(a);
                        } else if (idTipo == 6) {
                            Humano a1 = new AdultoVivo(idCriatura, idTipo, nomeCriatura, x, y, "human.png");
                            criaturas.add(a1);
                        } else if (idTipo == 7) {
                            Humano a2 = new MilitarVivo(idCriatura, idTipo, nomeCriatura, x, y, "human.png");
                            criaturas.add(a2);
                        } else if (idTipo == 8) {
                            Humano a3 = new IdosoVivo(idCriatura, idTipo, nomeCriatura, x, y, "human.png");
                            criaturas.add(a3);
                        } else if (idTipo == 9) {
                            Humano a4 = new Cao(idCriatura, idTipo, nomeCriatura, x, y, "human.png");
                            criaturas.add(a4);
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


                    if (idTipo == 0) {
                        Equipamento e = new EscudoDeMadeira(idEquipamento, idTipo, x, y, "Defensivo", "equipment_0.png");
                        equipamentos.add(e);
                    } else if (idTipo == 1) {
                        Equipamento e = new EspadaHattoriHanzo(idEquipamento, idTipo, x, y, "Ofensivo", "equipment_1.png");
                        equipamentos.add(e);
                    } else if (idTipo == 2) {
                        Equipamento e = new PistolaWaltherPPK(idEquipamento, idTipo, x, y, "Ofensivo", "gun.png");
                        equipamentos.add(e);
                    } else if (idTipo == 3) {
                        Equipamento e = new EscudoTactico(idEquipamento, idTipo, x, y, "Defensivo", "tactical_shield.png");
                        equipamentos.add(e);
                    } else if (idTipo == 4) {
                        Equipamento e = new RevistaMaria(idEquipamento, idTipo, x, y, "Defensivo", "rolled_magazine.png");
                        equipamentos.add(e);
                    } else if (idTipo == 5) {
                        Equipamento e = new CabecadeAlho(idEquipamento, idTipo, x, y, "Defensivo", "garlic.png");
                        equipamentos.add(e);
                    } else if (idTipo == 6) {
                        Equipamento e = new EstacaDeMadeira(idEquipamento, idTipo, x, y, "Ofensivo", "steak.png");
                        equipamentos.add(e);
                    } else if (idTipo == 7) {
                        Equipamento e = new GarrafaDeLixivia(idEquipamento, idTipo, x, y, "Defensivo", "bleach.png");
                        equipamentos.add(e);
                    } else if (idTipo == 8) {
                        Equipamento e = new Veneno(idEquipamento, idTipo, x, y, "Defensivo", "poison.png");
                        equipamentos.add(e);
                    } else if (idTipo == 9) {
                        Equipamento e = new Antidoto(idEquipamento, idTipo, x, y, "Defensivo", "antidote.png");
                        equipamentos.add(e);
                    } else if (idTipo == 10) {
                        Equipamento e = new BeskarHelmet(idEquipamento, idTipo, x, y, "Defensivo/Ofensivo", "beskar_helmet.png");
                        equipamentos.add(e);
                    }
                    numeroDaLinha++;
                }

                int numerHouses = Integer.parseInt(leitorFicheiro.nextLine());

                for (int i = 0; i < numerHouses; i++) {
                    String linha4 = leitorFicheiro.nextLine();
                    String dados0[] = linha4.split(" : ");
                    int x = Integer.parseInt(dados0[0]);
                    int y = Integer.parseInt(dados0[1]);
                    SafeHaven s = new SafeHaven(x, y);
                    houses.add(s);
                }

            }


            leitorFicheiro.close();
            verficarSeHumanoTemEquip();
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";
            System.out.println(mensagem);
            return false;
        }
        return true;
    }

    private void verficarSeHumanoTemEquip() {
        /*
        ArrayList<Equipamento> paraRemover = new ArrayList<>();
        for (Equipamento equip : equipamentos) {
            for (Humano h : humanos) {
                if (equip.x == h.x && equip.y == h.y) {
                    h.equipamento = equip;
                    paraRemover.add(equip);
                }
            }
        }
        equipamentos.removeAll(paraRemover);
        */

    }

    public int[] getWorldSize() {
        int[] arr = new int[2];
        arr[0] = numeroLinhas;
        arr[1] = numeroColunas;
        return arr;
    }

    public int getInitialTeam() {
        return inicialTeam;
    }

   /* public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
*/
    public List<Creature> getCreatures() {
        return criaturas;
    }

    public boolean move(int xO, int yO, int xD, int yD) {
        if (!(validarCoordenadas(xO, yO, xD, yD))) {
            return false;
        }
        if (!(validaEquipaAtual(xO, yO))) {
            return false;
        }
        if (verificarCriaturaDestino(xD, yD)) {
            return false;
        }
        Equipamento equip = buscarEquipamento(xD, yD);

        if (equip == null) {
            mudarPosicaoCriatura(xO, yO, xD, yD);
        } else {
            if (equipaAtual == 0) {
                //equipa humano
                Humano humano = getHumano(xO, yO);
                if (humano.getEquipamento() == null) {
                    humano.setEquipamento(equip);
                    humano.setUsados(humano.getUsados() + 1);
                    equipamentos.remove(equip);
                } else {
                    largarEquipamento(xO, yO, equip, humano);
                }
            } else {
                destruirEquipamento(equip.id);
                Zombie morto = getZombie(xO, yO);
                morto.toolsDestroy++;
            }
            mudarPosicaoCriatura(xO, yO, xD, yD);
        }
        mudarEquipaAtual();
        mudarDiaNoite();
        turno++;
        return true;
    }

    private void mudarDiaNoite() {
        --diasDiaNoite;
        if (diasDiaNoite == 0) {
            isDay = !isDay;
            diasDiaNoite = 2;
        }
    }

    private Humano getHumano(int xO, int yO) {
        /*
        for (int b = 0; b < humanos.size(); b++) {
            if (humanos.get(b).x == xO && humanos.get(b).y == yO) {
                return humanos.get(b);
            }
        }

         */
        return null;
    }

    private Zombie getZombie(int xO, int yO) {
        /*
        for (int b = 0; b < zombies.size(); b++) {
            if (zombies.get(b).x == xO && zombies.get(b).y == yO) {
                return zombies.get(b);
            }
        }

         */
        return null;
    }

    private void largarEquipamento(int xO, int yO, Equipamento novoEquipamento, Humano humano) {
        Equipamento equipamentoAntigo = humano.getEquipamento();
        equipamentoAntigo.coordenadaVertical(xO);
        equipamentoAntigo.coordenadaHorizontal(yO);
        equipamentos.add(equipamentoAntigo);
        humano.setEquipamento(novoEquipamento);
        equipamentos.remove(novoEquipamento);
    }

    private boolean validaEquipaAtual(int xO, int yO) {
        Humano h = getHumano(xO, yO);
        Zombie z = getZombie(xO, yO);
        if (equipaAtual == 0 && h == null) {
            return false;
        }
        if (equipaAtual == 1 && z == null) {
            return false;
        }
        return true;
    }

    private void destruirEquipamento(int idEquipamento) {
        int index = 0;
        for (int a = 0; a < equipamentos.size(); a++) {
            if (equipamentos.get(a).id == idEquipamento) {
                index = a;
            }
        }
        equipamentos.remove(index);
    }

    private void mudarPosicaoCriatura(int xO, int yO, int xD, int yD) {

        for (int a = 0; a < criaturas.size(); a++) {
            if (criaturas.get(a).x == xO && criaturas.get(a).y == yO) {
                criaturas.get(a).coordenadaVertical(xD);
                criaturas.get(a).coordenadaHorizontal(yD);
            }
        }
    }


    private boolean verificarCriaturaDestino(int xD, int yD) {
        for (int c = 0; c < criaturas.size(); c++) {
            if (criaturas.get(c).x == xD && criaturas.get(c).y == yD) {
                return true;
            }
        }
        return false;
    }

    private void mudarEquipaAtual() {
        if (equipaAtual == 0) {
            equipaAtual = 1;
        } else {
            equipaAtual = 0;
        }
    }

    private boolean validarCoordenadas(int xO, int yO, int xD, int yD) {
        if (xO < 0 || yO < 0 || xD < 0 || yD < 0) {
            return false;
        }
        if (xD > (numeroLinhas) || xO > (numeroLinhas)) {
            return false;
        }
        if ((yD > numeroColunas) || yO > (numeroColunas)) {
            return false;
        }
        if (xO != xD && yO != yD) {
            return false;
        }
        if (Math.abs((xD - xO)) != 1 && Math.abs((xD - xO)) != 0) {
            return false;
        }
        if (Math.abs((yD - yO)) != 1 && Math.abs((yD - yO)) != 0) {
            return false;
        }
        return true;
    }

    private Equipamento buscarEquipamento(int xD, int yD) {
        for (int a = 0; a < equipamentos.size(); a++) {
            if (equipamentos.get(a).x == xD && equipamentos.get(a).y == yD) {
                return equipamentos.get(a);
            }
        }
        return null;
    }

    public boolean gameIsOver() {
        return this.turno == 12;
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
        for (Creature c : this.criaturas) {
            if (c.x == x && c.y == y) {
                return c.id;
            }
        }

        for (Equipamento ferramenta : this.equipamentos) {
            if (ferramenta.x == x && ferramenta.y == y) {
                return ferramenta.id;
            }
        }
        return 0;
    }


    public boolean isDay() {
        return this.isDay;
    }


    public List<String> getGameResults() {
        return null;
    }

    public boolean isDoorToSafeHaven(int x, int y) {


        for (int i = 0; i < houses.size(); i++) {
            if (houses.get(i).getX() == x && houses.get(i).getY() == y) {

                return true;
            }
        }

        return false;
    }


    public int getEquipmentTypeId(int equipmentId) {

        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).id == equipmentId) {

                return equipamentos.get(i).idTipo;
            }
        }
        return -1;
    }


    public List<Integer> getIdsInSaveHaven() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < criaturas.size(); i++) {
            if (criaturas.get(i).passouSafeHaven == true) {
                ids.add(criaturas.get(i).id);
            }
        }
        return ids;
    }


}