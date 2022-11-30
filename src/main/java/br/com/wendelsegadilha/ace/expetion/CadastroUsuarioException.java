package br.com.wendelsegadilha.ace.expetion;

public class CadastroUsuarioException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CadastroUsuarioException(String msg) {
        super(msg);
    }

    public CadastroUsuarioException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
