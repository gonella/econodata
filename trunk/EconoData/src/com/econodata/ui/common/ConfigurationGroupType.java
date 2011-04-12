package com.econodata.ui.common;

public enum ConfigurationGroupType {

	
	GENERAL("Geral"),
	NETWORK("Conectividade"),
	USER("Usuário"),
	BROKER("Corretoras"),
	COMMENTS("Padrão de Comentários"),
	FEEDER("Feeds");
	
	private String Type;
	
	private ConfigurationGroupType(String Type) {
		this.Type = Type;
	}
	
	public String toString(){
		return this.Type;
	}
}
