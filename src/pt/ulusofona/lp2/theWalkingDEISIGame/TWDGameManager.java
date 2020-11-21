package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {
    int turno = 1;
    int diasDiaNoite = 2;
    boolean isDay = true;
    int numeroColunas;
    int numeroLinhas;
    int equipaAtual;
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
                            Zombie z = new Zombie(idCriatura, nomeCriatura, x, y);
                            zombies.add(z);
                        } else if (idTipo == 1) {
                            Humano humano = new Humano(idCriatura, nomeCriatura, x, y);
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
            verficarSeHumanoTemEquip();
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";
            System.out.println(mensagem);
            return false;
        }
        return true;
    }

    private void verficarSeHumanoTemEquip() {
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
    }

    public int[] getWorldSize() {
        int[] arr = new int[2];
        arr[0] = numeroLinhas;
        arr[1] = numeroColunas;
        return arr;
    }

    public int getInitialTeam() {
        return equipaAtual;
    }

    public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
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
        Equipamento equip = buscaEquipamento(xD, yD);

        if (equip == null) {
            mudarPosicaoCriatura(xO, yO, xD, yD);
        } else {
            if (equipaAtual == 0) {
                //equipa humano
                Humano humano = getHumano(xO, yO);
                if (humano.equipamento == null) {
                    humano.equipamento = equip;
                    humano.usados++;
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
        for (int b = 0; b < humanos.size(); b++) {
            if (humanos.get(b).x == xO && humanos.get(b).y == yO) {
                return humanos.get(b);
            }
        }
        return null;
    }

    private Zombie getZombie(int xO, int yO) {
        for (int b = 0; b < zombies.size(); b++) {
            if (zombies.get(b).x == xO && zombies.get(b).y == yO) {
                return zombies.get(b);
            }
        }
        return null;
    }

    private void largarEquipamento(int xO, int yO, Equipamento novoEquipamento, Humano humano) {
        Equipamento equipamentoAntigo = humano.equipamento;
        equipamentoAntigo.x = xO;
        equipamentoAntigo.y = yO;
        equipamentos.add(equipamentoAntigo);
        humano.equipamento = novoEquipamento;
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
        if (equipaAtual == 0) {
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
        if (Math.abs((xD - xO))  != 1 && Math.abs((xD - xO)) != 0){
            return false;
        }
        if (Math.abs((yD - yO))  != 1 &&  Math.abs((yD - yO)) != 0){
            return false;
        }
        return true;
    }

    private Equipamento buscaEquipamento(int xD, int yD) {
        for (int a = 0; a < equipamentos.size(); a++) {
            if (equipamentos.get(a).x == xD && equipamentos.get(a).y == yD) {
                return equipamentos.get(a);
            }
        }
        return null;
    }

    public boolean gameIsOver() {
        return this.turno == 11;
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
                return morto.id;
            }
        }
        for (Humano vivo : this.humanos) {
            if (vivo.x == x && vivo.y == y) {
                return vivo.id;
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
        return this.isDay;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        for (Humano vivo : humanos) {
            if (creatureId == vivo.id) {
                if (vivo.equipamento != null && vivo.equipamento.idTipo == equipmentTypeId) {
                    return true;
                }
            }
        }
        return false;
    }
}