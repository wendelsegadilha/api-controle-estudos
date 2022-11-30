package br.com.wendelsegadilha.ace.expetion;

public class ViolacaoIntegridadeDadosException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ViolacaoIntegridadeDadosException(String msg) {
        super(msg);
    }

    public ViolacaoIntegridadeDadosException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
