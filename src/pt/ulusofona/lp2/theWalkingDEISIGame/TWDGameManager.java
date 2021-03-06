package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TWDGameManager {
    int turno = 0;
    int inicialTeam;
    int diasDiaNoite = 2;
    boolean isDay = true;
    int numeroColunas;
    int numeroLinhas;
    int equipaAtual;
    int turnosSemTransformacao;
    ArrayList<Equipamento> equipamentos;
    ArrayList<Creature> criaturas;
    ArrayList<SafeHaven> houses;
    ArrayList<Creature> entrouSafeHeaven;

    public TWDGameManager() {
        this.criaturas = new ArrayList<>();
        this.equipamentos = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.entrouSafeHeaven = new ArrayList<>();
    }

    public void startGame(File ficheiroInicial) throws InvalidTWDInitialFileException, FileNotFoundException {
        String nomeFicheiro = "Ficheiro.txt";
        int numeroDaLinha = 0;

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
                if (numeroCriaturas < 2) {
                    throw new InvalidTWDInitialFileException(false, false, "");
                }
                for (int i = 0; i < numeroCriaturas; i++) {
                    String linha3 = leitorFicheiro.nextLine();
                    String dados1[] = linha3.split(" : ");
                    if (dados1.length != 5) {
                        throw new InvalidTWDInitialFileException(true, false, linha3);
                    }
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
                    Equipamento e = new EscudoDeMadeira(idEquipamento, idTipo
                            , x, y, "Defensivo", "equipment_0.png", "Escudo de Madeira");
                    equipamentos.add(e);
                } else if (idTipo == 1) {
                    Equipamento e = new EspadaHattoriHanzo(idEquipamento, idTipo
                            , x, y, "Ofensivo", "equipment_1.png", "Espada Hattori Hanzo");
                    equipamentos.add(e);
                } else if (idTipo == 2) {
                    Equipamento e = new PistolaWaltherPPK(idEquipamento, idTipo
                            , x, y, "Ofensivo", "gun.png", "Pistola Walther PPK");
                    equipamentos.add(e);
                } else if (idTipo == 3) {
                    Equipamento e = new EscudoTactico(idEquipamento, idTipo
                            , x, y, "Defensivo", "tactical_shield.png", "Escudo Táctico");
                    equipamentos.add(e);
                } else if (idTipo == 4) {
                    Equipamento e = new RevistaMaria(idEquipamento, idTipo
                            , x, y, "Defensivo", "rolled_magazine.png", "Revista Maria");
                    equipamentos.add(e);
                } else if (idTipo == 5) {
                    Equipamento e = new CabecadeAlho(idEquipamento, idTipo
                            , x, y, "Defensivo", "garlic.png", "Cabeça de Alho");
                    equipamentos.add(e);
                } else if (idTipo == 6) {
                    Equipamento e = new EstacaDeMadeira(idEquipamento, idTipo
                            , x, y, "Ofensivo", "steak.png", "Estaca de Madeira");
                    equipamentos.add(e);
                } else if (idTipo == 7) {
                    Equipamento e = new GarrafaDeLixivia(idEquipamento, idTipo
                            , x, y, "Defensivo", "bleach.png", "Garrafa de Lixívia (1 litro)");
                    equipamentos.add(e);
                } else if (idTipo == 8) {
                    Equipamento e = new Veneno(idEquipamento, idTipo
                            , x, y, "Defensivo", "poison.png", "Veneno");
                    equipamentos.add(e);
                } else if (idTipo == 9) {
                    Equipamento e = new Antidoto(idEquipamento, idTipo
                            , x, y, "Defensivo", "antidote.png", "Antídoto");
                    equipamentos.add(e);
                } else if (idTipo == 10) {
                    Equipamento e = new BeskarHelmet(idEquipamento, idTipo
                            , x, y, "Defensivo/Ofensivo", "beskar_helmet.png", "Beskar Helmet");
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


    }

    private void verficarSeHumanoTemEquip() {
        ArrayList<Equipamento> paraRemover = new ArrayList<>();
        for (Equipamento equip : equipamentos) {
            for (Creature criatura : criaturas) {
                if (criatura.getTipo() >= 5 && criatura.getTipo() <= 9) {
                    if (equip.x == criatura.x && equip.y == criatura.y) {
                        ((Humano) criatura).equipamento = equip;
                        paraRemover.add(equip);
                    }
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
        return inicialTeam;
    }

    public List<Creature> getCreatures() {
        return criaturas;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        /*
        for (Creature idoso : criaturas) {

            if (idoso.getTipo() == 8 && !(idoso.morta)) {
                if (idoso.x == xO && idoso.y == yO) {
                    Equipamento equip = ((Humano) idoso).getEquipamento();
                    equip.coordenadaVertical(xO);
                    equip.coordenadaHorizontal(yO);
                    ((Humano) idoso).setEquipamento(null);
                }
            }
        }
        
         */


        if (!(validarCoordenadas(xO, yO, xD, yD))) {
            return false;
        }
        if (!(validaEquipaAtual(xO, yO))) {
            return false;
        }
        if (validaSobreposicao(xO, yO, xD, yD)) {
            return false;
        }
        Creature cOrigem = getCreature(xO, yO);
        Creature cDestino = getCreature(xD, yD);
        boolean isSafeHaven = getSafeHaven(xD, yD) != null;

        if (!(cOrigem.comportamentos(xD, yD, isDay, isSafeHaven))) {
            return false;
        }
        if (equipaAtual == 10) {
            if (cDestino != null && cDestino.getNomeEquipa().equals("Os Vivos")) {
                return false;
            }
        }
        if (equipaAtual == 20) {
            if (cDestino != null && cDestino.getNomeEquipa().equals("Os Outros")) {
                return false;
            }
            if (cDestino instanceof Cao) {
                return false;
            }

        }


        Equipamento equip = buscarEquipamento(xD, yD);
        if (isSafeHaven) {
            cOrigem.passouSafeHaven = true;
            cOrigem.coordenadaHorizontal(-1);
            cOrigem.coordenadaVertical(-1);
            entrouSafeHeaven.add(cOrigem);
            turnosSemTransformacao++;
        } else if (equip != null) {
            if (equipaAtual == 10) {
                for (Creature vivo : criaturas) {
                    if ((vivo.getTipo() >= 5 && vivo.getTipo() <= 9) && !(((Humano) vivo).transformado)) {
                        if (vivo.x == xO && vivo.y == yO) {
                            if (((Humano) vivo).getEquipamento() == null) {
                                if (equip.getIdTipo() != 9 && !(((Humano) vivo).getEnvenenado())) {
                                    ((Humano) vivo).setEquipamento(equip);
                                    ((Humano) vivo).setUsados(((Humano) vivo).getUsados() + 1);
                                    equip.coordenadaVertical(-1);
                                    equip.coordenadaHorizontal(-1);
                                    if (equip.getIdTipo() == 9 && ((Humano) vivo).envenenado) {
                                        ((Antidoto) equip).usar();
                                        ((Humano) vivo).curar();
                                    }
                                    if (equip.getIdTipo() == 8) {
                                        ((Veneno) equip).usar();
                                        ((Humano) vivo).envenenar();
                                    }
                                    if (vivo.getTipo() == 7 && equip.getIdTipo() == 0 &&
                                            !(((EscudoDeMadeira) equip).usoMilitar)) {
                                        ((EscudoDeMadeira) equip).setDurabilidadeMilitar();
                                    }
                                    mudarPosicaoCriatura(xO, yO, xD, yD);
                                    turnosSemTransformacao++;
                                } else {
                                    return false;
                                }
                            } else {
                                if (equip.getIdTipo() != 9 && !(((Humano) vivo).getEnvenenado())) {
                                    ((Humano) vivo).setUsados(((Humano) vivo).getUsados() + 1);
                                    equip.coordenadaVertical(-1);
                                    equip.coordenadaHorizontal(-1);
                                    Equipamento equipamentoAntigo = ((Humano) vivo).getEquipamento();
                                    equipamentoAntigo.coordenadaVertical(xO);
                                    equipamentoAntigo.coordenadaHorizontal(yO);
                                    ((Humano) vivo).setEquipamento(equip);
                                    if (equip.getIdTipo() == 9 && ((Humano) vivo).envenenado) {
                                        ((Antidoto) equip).usar();
                                        ((Humano) vivo).curar();
                                    }
                                    if (equip.getIdTipo() == 8) {
                                        ((Veneno) equip).usar();
                                        ((Humano) vivo).envenenar();
                                    }
                                    if (vivo.getTipo() == 7 && equip.getIdTipo() == 0 &&
                                            !(((EscudoDeMadeira) equip).usoMilitar)) {
                                        ((EscudoDeMadeira) equip).setDurabilidadeMilitar();
                                    }
                                } else {
                                    return false;
                                }
                                mudarPosicaoCriatura(xO, yO, xD, yD);
                                turnosSemTransformacao++;
                            }
                        }
                    }
                }
            } else {
                for (Creature z : criaturas) {
                    if ((z.getTipo() >= 0 && z.getTipo() <= 4) || ((Humano) z).transformado) {
                        if (z.x == xO && z.y == yO) {
                            if ((z.getTipo() != 4 || equip.getIdTipo() != 5) && equip.getIdTipo() != 8) {
                                destruirEquipamento(equip.id);
                                z.aumentarToolsDestruidas();
                                mudarPosicaoCriatura(xO, yO, xD, yD);
                            } else {
                                return false;
                            }
                        }
                    }
                }
                turnosSemTransformacao++;
            }

        } else if (cDestino != null) {
            if ((cOrigem.getTipo() >= 5 && cOrigem.getTipo() <= 9) && !(((Humano) cOrigem).transformado)) {
                if (cOrigem.getTipo() == 5 && ((Humano) cOrigem).getEquipamento() != null) {
                    Equipamento arma = ((Humano) cOrigem).getEquipamento();
                    if (arma.getIdTipo() == 1 && cDestino.getTipo() == 0) {
                        cDestino.matarCriatura();
                        ((Humano) cOrigem).zombiesDestruidos++;
                        arma.equipamentoSalvou++;
                        cDestino.coordenadaVertical(-1);
                        cDestino.coordenadaHorizontal(-1);
                        mudarPosicaoCriatura(xO, yO, xD, yD);
                        turnosSemTransformacao++;
                    } else if (arma.getIdTipo() == 2 && ((PistolaWaltherPPK) arma).getBalas() != 0 && cDestino.getTipo() != 4) {
                        ((PistolaWaltherPPK) arma).gastarBalas();
                        cDestino.matarCriatura();
                        ((Humano) cOrigem).zombiesDestruidos++;
                        arma.equipamentoSalvou++;
                        cDestino.coordenadaVertical(-1);
                        cDestino.coordenadaHorizontal(-1);
                        mudarPosicaoCriatura(xO, yO, xD, yD);
                        turnosSemTransformacao++;
                    } else if (arma.getIdTipo() == 6 || arma.getIdTipo() == 10) {
                        cDestino.matarCriatura();
                        ((Humano) cOrigem).zombiesDestruidos++;
                        arma.equipamentoSalvou++;
                        cDestino.coordenadaVertical(-1);
                        cDestino.coordenadaHorizontal(-1);
                        mudarPosicaoCriatura(xO, yO, xD, yD);
                        turnosSemTransformacao++;
                    } else {
                        return false;
                    }
                } else if (cOrigem.getTipo() > 5 && cOrigem.getTipo() <= 9 && ((Humano) cOrigem).getEquipamento() != null) {
                    if (cOrigem.getTipo() != 8) {
                        Equipamento arma = ((Humano) cOrigem).getEquipamento();
                        if (arma.getIdTipo() == 1 || arma.getIdTipo() == 6 || arma.getIdTipo() == 10) {
                            cDestino.matarCriatura();
                            ((Humano) cOrigem).zombiesDestruidos++;
                            arma.equipamentoSalvou++;
                            cDestino.coordenadaVertical(-1);
                            cDestino.coordenadaHorizontal(-1);
                            mudarPosicaoCriatura(xO, yO, xD, yD);
                            turnosSemTransformacao++;
                        } else if (arma.getIdTipo() == 2 && ((PistolaWaltherPPK) arma).getBalas() != 0 && cDestino.getTipo() != 4) {
                            ((PistolaWaltherPPK) arma).gastarBalas();
                            cDestino.matarCriatura();
                            ((Humano) cOrigem).zombiesDestruidos++;
                            arma.equipamentoSalvou++;
                            cDestino.coordenadaVertical(-1);
                            cDestino.coordenadaHorizontal(-1);
                            mudarPosicaoCriatura(xO, yO, xD, yD);
                            turnosSemTransformacao++;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            if ((cOrigem.getTipo() >= 0 && cOrigem.getTipo() <= 4) || ((Humano) cOrigem).transformado) {
                if (!(((Humano) cDestino).transformado)) {
                    if (cDestino.getTipo() >= 5 && cDestino.getTipo() < 9 && ((Humano) cDestino).getEquipamento() != null) {
                        Equipamento defesa = ((Humano) cDestino).getEquipamento();
                        if (defesa.getIdTipo() == 0 && ((EscudoDeMadeira) defesa).getDurabilidade() != 0) {
                            ((EscudoDeMadeira) defesa).tirarDurabilidade();
                            defesa.equipamentoSalvou++;
                            mudarPosicaoCriatura(xO, yO, xO, yO);
                            turnosSemTransformacao++;
                        } else if (defesa.getIdTipo() == 4 && (cOrigem.getTipo() == 3 || cOrigem.getTipo() == 6)) {
                            ((RevistaMaria) defesa).aumentarDentaduras();
                            defesa.equipamentoSalvou++;
                            mudarPosicaoCriatura(xO, yO, xO, yO);
                            turnosSemTransformacao++;
                        } else if (defesa.getIdTipo() == 3) {
                            ((EscudoTactico) defesa).aumentarHits();
                            defesa.equipamentoSalvou++;
                            mudarPosicaoCriatura(xO, yO, xO, yO);
                            turnosSemTransformacao++;
                        } else if (defesa.getIdTipo() == 5 && cOrigem.getTipo() == 4) {
                            ((CabecadeAlho) defesa).aumentarInseticida();
                            defesa.equipamentoSalvou++;
                            mudarPosicaoCriatura(xO, yO, xO, yO);
                            turnosSemTransformacao++;
                        } else if (defesa.getIdTipo() == 7) {
                            if (!(((GarrafaDeLixivia) defesa).inutil)) {
                                ((GarrafaDeLixivia) defesa).diminuirLitos();
                                defesa.equipamentoSalvou++;
                                mudarPosicaoCriatura(xO, yO, xO, yO);
                                turnosSemTransformacao++;
                            }
                        } else if (defesa.getIdTipo() == 8) {
                            mudarPosicaoCriatura(xO, yO, xO, yO);
                            turnosSemTransformacao++;
                        } else if (defesa.getIdTipo() == 2 && ((PistolaWaltherPPK) defesa).getBalas() != 0 && cOrigem.getTipo() != 4) {
                            ((PistolaWaltherPPK) defesa).gastarBalas();
                            cOrigem.matarCriatura();
                            defesa.equipamentoSalvou++;
                            cOrigem.coordenadaVertical(-1);
                            cOrigem.coordenadaHorizontal(-1);
                            turnosSemTransformacao++;
                        } else {
                            ((Humano) cDestino).transformar();
                            ((Zombie) cOrigem).numeroVivosTransformados++;
                            if (((Humano) cDestino).getEquipamento() != null) {
                                Equipamento equipamento = ((Humano) cDestino).getEquipamento();
                                equipamento.coordenadaHorizontal(-1);
                                equipamento.coordenadaVertical(-1);
                                ((Humano) cDestino).setEquipamento(null);
                            }
                            mudarPosicaoCriatura(xO, yO, xO, yO);
                            turnosSemTransformacao = 0;
                        }
                    } else {
                        ((Humano) cDestino).transformar();
                        mudarPosicaoCriatura(xO, yO, xO, yO);
                        turnosSemTransformacao = 0;
                    }
                } else {
                    return false;
                }
            }

        } else {
            turnosSemTransformacao++;
            mudarPosicaoCriatura(xO, yO, xD, yD);
        }

        mudarEquipaAtual();
        mudarDiaNoite();
        turno++;

        for (Creature envenenados : criaturas) {
            if (envenenados.getTipo() >= 5 && envenenados.getTipo() <= 9) {
                if (((Humano) envenenados).getEnvenenado()) {
                    Equipamento veneno = ((Humano) envenenados).getEquipamento();
                    if (veneno.getIdTipo() == 8) {
                        if (((Veneno) veneno).getTurnoAtivo() == ((Veneno) veneno).maxTurno) {
                            envenenados.matarCriatura();
                            envenenados.coordenadaHorizontal(-1);
                            envenenados.coordenadaVertical(-1);
                        } else {
                            ((Veneno) veneno).aumentarTurno();
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
        private boolean validaSobreposicao(int xO, int yO, int xD, int yD) {
            boolean eDiagonal = false;
            if (xO != xD && yO != yD) {
                eDiagonal = true;
            }
            for (int i = 0; i < criaturas.size(); i++) {
                if (yO == yD && criaturas.get(i).x > xO && criaturas.get(i).x < xD) {
                    return true;
                }
                if (xO == xD && criaturas.get(i).y > yO && criaturas.get(i).y < yD) {
                    return true;
                }

                //     if (xO == yO && xD == yD) {
                int xAux = criaturas.get(i).x;
                int yAux = criaturas.get(i).y;

                if (xAux == yAux && (xAux > xO && xAux < xD || yAux > yO && yAux < yD)) {

                    return true;
                }
                //}

            }

            return false;
        }


     */
    private boolean validaSobreposicao(int xO, int yO, int xD, int yD) {
        String esquerdaDireita = "";
        String cimaBaixo = "";

        boolean eDiagonal = false;
        if (xO != xD && yO != yD) {
            eDiagonal = true;
            if (yO > yD) {
                cimaBaixo = "cima";
            } else {
                cimaBaixo = "baixo";
            }
            if (xO > xD) {
                esquerdaDireita = "esquerda";
            } else {
                esquerdaDireita = "direita";
            }
        }

        if (xO == xD) {
            // o movimento é cima/baixo
            if (yO > yD) {
                // movimento é para cima
                cimaBaixo = "cima";
            } else {
                // movimento é para baixo
                cimaBaixo = "baixo";
            }
        } else if (yO == yD) {
            // o moviento é esquerda/direita
            if (xO > xD) {
                esquerdaDireita = "esquerda";
            } else {
                esquerdaDireita = "direita";
            }
        }

        if (eDiagonal == false && cimaBaixo.equals("cima")) {
            // movimento para cima -> y vai diminuir
            int auxY = yO;
            while (auxY != yD) {
                --auxY;
                int finalAuxY = auxY;
                Creature a = criaturas
                        .stream()
                        .filter(c -> c.x == xO && c.y == finalAuxY)
                        .findFirst()
                        .orElse(null);
                if (a != null) {
                    return true;
                }
            }

            return false;
        } else if (eDiagonal == false && cimaBaixo.equals("baixo")) {
            // movimento para baixo -> y vai aumentar
            int auxY = yO;
            while (auxY != yD) {
                ++auxY;
                int finalAuxY = auxY;
                Creature a = criaturas
                        .stream()
                        .filter(c -> c.x == xO && c.y == finalAuxY)
                        .findFirst()
                        .orElse(null);
                if (a != null) {
                    return true;
                }
            }

            return false;
        } else if (eDiagonal == false && esquerdaDireita.equals("esquerda")) {
            // movimento para esquerda -> x vai diminuir
            int auxX = xO;
            while (auxX != xD) {
                --auxX;
                int finalAuxX = auxX;
                Creature a = criaturas
                        .stream()
                        .filter(c -> c.x == finalAuxX && c.y == yO)
                        .findFirst()
                        .orElse(null);
                if (a != null) {
                    return true;
                }
            }

            return false;
        } else if (eDiagonal == false && esquerdaDireita.equals("direita")) {
            // movimento para direira -> x vai aumentar
            int auxX = xO;
            while (auxX != xD) {
                ++auxX;
                int finalAuxX = auxX;
                Creature a = criaturas
                        .stream()
                        .filter(c -> c.x == finalAuxX && c.y == yO)
                        .findFirst()
                        .orElse(null);
                if (a != null) {
                    return true;
                }
            }

            return false;
        } else {
            // é diagonal
            int auxY = yO;
            int auxX = xO;
            if (cimaBaixo.equals("cima")) {
                if (esquerdaDireita.equals("esquerda")) {
                    while (auxX != xD && auxY != yD) {
                        --auxX;
                        --auxY;
                        int finalAuxX = auxX;
                        int finalAuxY = auxY;
                        Creature a = criaturas
                                .stream()
                                .filter(c -> c.x == finalAuxX && c.y == finalAuxY)
                                .findFirst()
                                .orElse(null);
                        if (a != null) {
                            return true;
                        }
                    }

                    return false;
                } else if (esquerdaDireita.equals("direita")) {
                    while (auxX != xD && auxY != yD) {
                        ++auxX;
                        --auxY;
                        int finalAuxX = auxX;
                        int finalAuxY = auxY;
                        Creature a = criaturas
                                .stream()
                                .filter(c -> c.x == finalAuxX && c.y == finalAuxY)
                                .findFirst()
                                .orElse(null);
                        if (a != null) {
                            return true;
                        }
                    }

                    return false;
                }
            } else if (cimaBaixo.equals("baixo")) {
                if (esquerdaDireita.equals("esquerda")) {
                    while (auxX != xD && auxY != yD) {
                        --auxX;
                        ++auxY;
                        int finalAuxX = auxX;
                        int finalAuxY = auxY;
                        Creature a = criaturas
                                .stream()
                                .filter(c -> c.x == finalAuxX && c.y == finalAuxY)
                                .findFirst()
                                .orElse(null);
                        if (a != null) {
                            return true;
                        }
                    }

                    return false;
                } else if (esquerdaDireita.equals("direita")) {
                    while (auxX != xD && auxY != yD) {
                        ++auxX;
                        ++auxY;
                        int finalAuxX = auxX;
                        int finalAuxY = auxY;
                        Creature a = criaturas
                                .stream()
                                .filter(c -> c.x == finalAuxX && c.y == finalAuxY)
                                .findFirst()
                                .orElse(null);
                        if (a != null) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }

        return false;
    }

    private void mudarDiaNoite() {
        --diasDiaNoite;
        if (diasDiaNoite == 0) {
            isDay = !isDay;
            diasDiaNoite = 2;
        }
    }

    private SafeHaven getSafeHaven(int xD, int yD) {
        for (SafeHaven casa : houses) {
            if (casa.getX() == xD && casa.getY() == yD) {
                return casa;
            }
        }
        return null;
    }

    private Creature getCreature(int xO, int yO) {
        for (Creature criatura : criaturas) {
            if (criatura.x == xO && criatura.y == yO) {
                return criatura;
            }
        }
        return null;
    }

    private void largarEquipamento(int xO, int yO, Equipamento newEquip, Humano humano) {
        Equipamento equipamentoAntigo = humano.getEquipamento();
        equipamentoAntigo.coordenadaVertical(xO);
        equipamentoAntigo.coordenadaHorizontal(yO);
        humano.setEquipamento(newEquip);
        if (newEquip.getIdTipo() != 9 && !(humano.getEnvenenado())) {
            humano.setUsados(humano.getUsados() + 1);
            newEquip.coordenadaVertical(-1);
            newEquip.coordenadaHorizontal(-1);
            if (newEquip.getIdTipo() == 9 && humano.envenenado) {
                ((Antidoto) newEquip).usar();
                humano.curar();
            }
            if (newEquip.getIdTipo() == 8) {
                ((Veneno) newEquip).usar();
                humano.envenenar();
            }
            if (humano.getTipo() == 7 && newEquip.getIdTipo() == 0 && !(((EscudoDeMadeira) newEquip).usoMilitar)) {
                ((EscudoDeMadeira) newEquip).setDurabilidadeMilitar();
            }
        }
    }

    private boolean validaEquipaAtual(int xO, int yO) {
        Creature c = getCreature(xO, yO);
        if (equipaAtual == 10 && c.getNomeEquipa().equals("Os Outros")) {
            return false;
        }
        if (equipaAtual == 20 && c.getNomeEquipa().equals("Os Vivos")) {
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

    private boolean mudarPosicaoCriatura(int xO, int yO, int xD, int yD) {
        for (Creature criatura : criaturas) {
            if (criatura.x == xO && criatura.y == yO) {
                criatura.coordenadaHorizontal(yD);
                criatura.coordenadaVertical(xD);
                return true;
            }
        }
        return false;
    }

    private void mudarEquipaAtual() {
        if (equipaAtual == 10) {
            equipaAtual = 20;
        } else {
            equipaAtual = 10;
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
        int naoBairro = 0;
        int allHumans = 0;
        if (turnosSemTransformacao == 6) {
            return true;
        }
        for (Creature person : criaturas) {
            if (person.getTipo() >= 5 && person.getTipo() <= 9) {
                if (person.passouSafeHaven || ((Humano) person).transformado || person.morta) {
                    naoBairro++;
                }
                allHumans++;
            }
        }
        return naoBairro == allHumans;
    }

    public List<String> getAuthors() {
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Rodrigo Sousa");
        return autores;
    }

    public int getCurrentTeamId() {
        return this.equipaAtual;
    }

    public int getElementId(int x, int y) {
        for (Creature criatura : criaturas) {
            if (criatura.x == x && criatura.y == y) {
                return criatura.getId();
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
        ArrayList<String> resultados = new ArrayList<>();
        String linha1 = "Nr. de turnos terminados:";
        String linha2 = Integer.toString(turno);
        String linha3 = "Ainda pelo bairo:";
        String linha4 = "OS VIVOS";
        resultados.add(linha1);
        resultados.add(linha2);
        resultados.add(linha3);
        resultados.add(linha4);
        for (Creature criatura : criaturas) {
            if ((criatura.idTipo >= 5 && criatura.idTipo <= 9) && !(((Humano) criatura).transformado)) {
                if ((criatura.passouSafeHaven) && (criatura.morta)) {
                    resultados.add(criatura.id + " " + criatura.nome);
                }
            }
        }
        String linha6 = "OS OUTROS";
        resultados.add(linha6);
        for (Creature criatura : criaturas) {
            if ((criatura.idTipo >= 0 && criatura.idTipo <= 4) || ((Humano) criatura).transformado) {
                if (!(criatura.morta)) {
                    resultados.add(criatura.id + " " + "(antigamente conhecido como " + criatura.nome + ")");
                }
            }
        }
        String linha8 = "Num safe haven:";
        resultados.add(linha8);
        String linha9 = "OS VIVOS";
        resultados.add(linha9);
        for (Creature criatura : criaturas) {
            if (criatura.idTipo >= 5 && criatura.idTipo <= 9) {
                if (criatura.passouSafeHaven) {
                    resultados.add(criatura.id + " " + criatura.nome);
                }
            }
        }
        String linha11 = "Envenenados / Destruidos";
        resultados.add(linha11);
        String linha12 = "OS VIVOS";
        resultados.add(linha12);
        for (Creature criatura : criaturas) {
            if (criatura.idTipo >= 5 && criatura.idTipo <= 9) {
                if ((((Humano) criatura).envenenado) && (criatura.morta)) {
                    resultados.add(criatura.id + " " + criatura.nome);
                }
            }
        }
        String linha14 = "OS OUTROS";
        resultados.add(linha14);
        for (Creature criatura : criaturas) {
            if ((criatura.idTipo >= 0 && criatura.idTipo <= 4) || ((Humano) criatura).transformado) {
                if (criatura.morta) {
                    resultados.add(criatura.id + " " + "(antigamente conhecido como " + criatura.nome + ")");
                }
            }
        }
        return resultados;
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


    public List<Integer> getIdsInSafeHaven() {
        return entrouSafeHeaven.stream()
                .map(creature -> creature.id)
                .collect(Collectors.toList());
    }

    public int getEquipmentId(int creatureId) {
        for (Creature criatura : criaturas) {
            if (criatura.id == creatureId) {
                if (criatura.idTipo <= 9 && criatura.idTipo >= 5) {
                    if (((Humano) criatura).getEquipamento() != null) {
                        return ((Humano) criatura).equipamento.id;
                    }
                }
            }
        }
        return 0;
    }

    public String getEquipmentInfo(int equipmentId) {
        Equipamento aux = new Equipamento();
        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).id == equipmentId) {
                aux = equipamentos.get(i);
            }
        }
        if (aux.idTipo == 0) {
            return aux.nome + " | " + ((EscudoDeMadeira) aux).getDurabilidade();
        }
        if (aux.idTipo == 2) {
            return aux.nome + " | " + ((PistolaWaltherPPK) aux).getBalas();
        }
        if (aux.idTipo == 7) {
            return aux.nome + " | " + ((GarrafaDeLixivia) aux).getUsos();
        }
        if (aux.idTipo == 8) {
            if (((Veneno) aux).getUsado()) {
                return aux.nome + " | Vazio";
            }
            return aux.nome + " | Cheio";
        }
        if (aux.idTipo == 9) {
            if (((Antidoto) aux).getUsado()) {
                return aux.nome + " | Vazio";
            }
            return aux.nome + " | Cheio";
        }
        return aux.nome;
    }

    public boolean saveGame(File fich) {
        try {
            FileWriter ficheiro = new FileWriter(fich);
            ficheiro.write(numeroLinhas + " " + numeroColunas + '\n');
            ficheiro.write(equipaAtual + '\n');
            ficheiro.write(criaturas.size() + '\n');
            for (Creature criatura : criaturas) {
                String linha = criatura.id + " : " + criatura.idTipo + " : " + criatura.nome + " : ";
                ficheiro.write(linha + criatura.x + " : " + criatura.y + '\n');
            }
            ficheiro.write(equipamentos.size() + '\n');
            for (Equipamento equip : equipamentos) {
                String linha = equip.id + " : " + equip.idTipo + " : ";
                ficheiro.write(linha + equip.x + " : " + equip.y + '\n');
            }
            ficheiro.write(houses.size() + '\n');
            for (SafeHaven safe : houses) {
                ficheiro.write(safe.getX() + " : " + safe.getY() + '\n');
            }

            ficheiro.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadGame(File fich) {
        int numeroDaLinha = 0;
        try {
            Scanner leitorFicheiro = new Scanner(fich);
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
                            Zombie z = new CriancaZombie(idCriatura, idTipo
                                    , nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z);
                        } else if (idTipo == 1) {
                            Zombie z1 = new AdultoZombie(idCriatura, idTipo
                                    , nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z1);
                        } else if (idTipo == 2) {
                            Zombie z2 = new MilitarZombie(idCriatura, idTipo
                                    , nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z2);
                        } else if (idTipo == 3) {
                            Zombie z3 = new IdosoZombie(idCriatura, idTipo
                                    , nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z3);
                        } else if (idTipo == 4) {
                            Zombie z4 = new ZombieVampiro(idCriatura, idTipo
                                    , nomeCriatura, x, y, "zombie.png");
                            criaturas.add(z4);
                        } else if (idTipo == 5) {
                            Humano a = new CriancaVivo(idCriatura, idTipo
                                    , nomeCriatura, x, y, "human.png");
                            criaturas.add(a);
                        } else if (idTipo == 6) {
                            Humano a1 = new AdultoVivo(idCriatura, idTipo
                                    , nomeCriatura, x, y, "human.png");
                            criaturas.add(a1);
                        } else if (idTipo == 7) {
                            Humano a2 = new MilitarVivo(idCriatura, idTipo
                                    , nomeCriatura, x, y, "human.png");
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
                        Equipamento e = new EscudoDeMadeira(idEquipamento, idTipo
                                , x, y, "Defensivo", "equipment_0.png", "Escudo de Madeira");
                        equipamentos.add(e);
                    } else if (idTipo == 1) {
                        Equipamento e = new EspadaHattoriHanzo(idEquipamento, idTipo
                                , x, y, "Ofensivo", "equipment_1.png", "Espada Hattori Hanzo");
                        equipamentos.add(e);
                    } else if (idTipo == 2) {
                        Equipamento e = new PistolaWaltherPPK(idEquipamento, idTipo
                                , x, y, "Ofensivo", "gun.png", "Pistola Walther PPK");
                        equipamentos.add(e);
                    } else if (idTipo == 3) {
                        Equipamento e = new EscudoTactico(idEquipamento, idTipo
                                , x, y, "Defensivo", "tactical_shield.png", "Escudo Táctico");
                        equipamentos.add(e);
                    } else if (idTipo == 4) {
                        Equipamento e = new RevistaMaria(idEquipamento, idTipo
                                , x, y, "Defensivo", "rolled_magazine.png", "Revista Maria");
                        equipamentos.add(e);
                    } else if (idTipo == 5) {
                        Equipamento e = new CabecadeAlho(idEquipamento, idTipo
                                , x, y, "Defensivo", "garlic.png", "Cabeça de Alho");
                        equipamentos.add(e);
                    } else if (idTipo == 6) {
                        Equipamento e = new EstacaDeMadeira(idEquipamento, idTipo
                                , x, y, "Ofensivo", "steak.png", "Estaca de Madeira");
                        equipamentos.add(e);
                    } else if (idTipo == 7) {
                        Equipamento e = new GarrafaDeLixivia(idEquipamento, idTipo
                                , x, y, "Defensivo", "bleach.png", "Garrafa de Lixívia (1 litro)");
                        equipamentos.add(e);
                    } else if (idTipo == 8) {
                        Equipamento e = new Veneno(idEquipamento, idTipo
                                , x, y, "Defensivo", "poison.png", "Veneno");
                        equipamentos.add(e);
                    } else if (idTipo == 9) {
                        Equipamento e = new Antidoto(idEquipamento, idTipo
                                , x, y, "Defensivo", "antidote.png", "Antídoto");
                        equipamentos.add(e);
                    } else if (idTipo == 10) {
                        Equipamento e = new BeskarHelmet(idEquipamento, idTipo
                                , x, y, "Defensivo/Ofensivo", "beskar_helmet.png", "Beskar Helmet");
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
            String mensagem = "Erro: o ficheiro " + fich + " nao foi encontrado.";
            System.out.println(mensagem);
            return false;
        }
        return true;
    }

    public String[] popCultureExtravaganza() {
        String[] respostas = new String[14];
        respostas[0] = "Resident Evil";
        respostas[1] = "Evil Dead";
        respostas[2] = "I Am Legend";
        respostas[3] = "I Am Legend";
        respostas[4] = "Dragon Ball";
        respostas[5] = "World War Z";
        respostas[6] = "Mandalorians";
        respostas[7] = "1972";
        respostas[8] = "Kill Bill";
        respostas[9] = "1978";
        respostas[10] = "Bond, James Bond.";
        respostas[11] = "Lost";
        respostas[12] = "Chocho";
        respostas[13] = "Farrokh Bulsara";
        return respostas;
    }


    public Map<String, List<String>> getGameStatistics() {
        Map<String, List<String>> stats = new HashMap<>();
        stats.put("os3ZombiesMaisTramados", getOs3ZombiesMaisTramados());
        stats.put("os3VivosMaisDuros", getOs3VivosMaisDuros());
        stats.put("tiposDeEquipamentoMaisUteis", getTiposDeEquipamentoMaisUteis());
        stats.put("tiposDeZombieESeusEquipamentosDestruidos", getTiposDeZombieESeusEquipamentosDestruidos());
        stats.put("criaturasMaisEquipadas", getCriaturasMaisEquipadas());
        return stats;
    }


    private List<String> getOs3ZombiesMaisTramados() {
        return criaturas.stream()
                .filter(creature -> creature.getTipo() >= 0 && creature.getTipo() <= 4)
                .sorted((c1, c2) -> ((Zombie) c2).numeroVivosTransformados - ((Zombie) c1).numeroVivosTransformados)
                .filter(creature -> ((Zombie) creature).numeroVivosTransformados > 0)
                .limit(3)
                .map(creature -> creature.id + ":" + creature.nome + ":" + ((Zombie) creature).numeroVivosTransformados)
                .collect(Collectors.toList());
    }


    private List<String> getOs3VivosMaisDuros() {
        return criaturas.stream()
                .filter(creature -> creature.getTipo() >= 5 && creature.getTipo() <= 9)
                .sorted((c1, c2) -> ((Humano) c2).zombiesDestruidos - ((Humano) c1).zombiesDestruidos)
                .filter(creature -> ((Humano) creature).zombiesDestruidos > 0)
                .limit(3)
                .map(creature -> creature.id + ":" + creature.nome + ":" + ((Humano) creature).zombiesDestruidos)
                .collect(Collectors.toList());
    }

    private List<String> getTiposDeEquipamentoMaisUteis() {
        return equipamentos.stream()
                .sorted((e1, e2) -> e1.equipamentoSalvou - e2.equipamentoSalvou)
                .filter(e -> e.equipamentoSalvou > 0)
                .map(e -> e.idTipo + ":" + e.equipamentoSalvou)
                .collect(Collectors.toList());
    }

    private List<String> getTiposDeZombieESeusEquipamentosDestruidos() {

        //criaturas.stream().collect(
        //      Collectors.groupingBy(Creature::getTipo,Collectors.summingInt(c->((Zombie)c).totalEquipDestrui)));
        return new ArrayList<>();
    }

    private List<String> getCriaturasMaisEquipadas() {
        return criaturas.stream()
                .filter(creature -> !creature.morta || !creature.passouSafeHaven)
                .sorted((c1, c2) -> {
                    if ((c1.getTipo() >= 0 && c1.getTipo() <= 4) && (c2.getTipo() >= 0 && c2.getTipo() <= 4)) {
                        return (c2.getToolsDestroy() - c1.getToolsDestroy());
                    } else if ((c1.getTipo() >= 5 && c1.getTipo() <= 9) && (c2.getTipo() >= 5 && c2.getTipo() <= 9)) {
                        return ((Humano) c2).usados - ((Humano) c1).usados;
                    } else {
                        return 0;
                    }
                })
                .limit(5)
                .map(c -> {
                    int numeroEquip;
                    if ((c.getTipo() >= 0 && c.getTipo() <= 4)) {
                        numeroEquip = c.getToolsDestroy();
                    } else {
                        numeroEquip = ((Humano) c).usados;
                    }
                    return c.id + ":" + c.nome + ":" + numeroEquip;
                }).collect(Collectors.toList());
    }

}