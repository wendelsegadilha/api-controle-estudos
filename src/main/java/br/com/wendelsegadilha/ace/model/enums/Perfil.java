package br.com.wendelsegadilha.ace.model.enums;

public enum Perfil {

    ASSINANTE(1, "ROLE_ASSINANTE"), TESTE(2, "ROLE_TESTE");
    private final int codigo;
    private final String descricao;
    
	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
    
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
