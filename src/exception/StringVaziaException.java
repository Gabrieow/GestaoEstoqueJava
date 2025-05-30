package exception;

public class StringVaziaException extends Exception {
    private int codigo;
    public StringVaziaException(String mensagem, int codigo){
        super("SVE01 - " + mensagem);
        this.codigo = codigo;
    }

    public int getCodigo(){
        return codigo;
    }
}
