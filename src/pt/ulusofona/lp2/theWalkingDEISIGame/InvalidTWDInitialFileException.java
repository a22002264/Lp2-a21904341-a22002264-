package pt.ulusofona.lp2.theWalkingDEISIGame;

public class InvalidTWDInitialFileException extends Exception {
    private boolean validoCreatures;
    private boolean validDefinition;
    private String mensagemException;

    public InvalidTWDInitialFileException(boolean validoCreatures, boolean validDefinition, String mensagemException) {
        this.validoCreatures = validoCreatures;
        this.validDefinition = validDefinition;
        this.mensagemException = mensagemException;
    }

    public boolean validNrOfCreatures() {
        return validoCreatures;
    }

    public boolean validCreatureDefinition() {
        return validDefinition;
    }

    public String getErroneousLine() {
        return mensagemException;
    }


}
