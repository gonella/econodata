package com.econodata.ui.common;

public enum ConfigurationGroupType {

	
	GENERAL("Geral"),
	NETWORK("Conectividade"),
	USER("Usu�rio"),
	BROKER("Corretoras"),
	COMMENTS("Padr�o de Coment�rios"),
	FEEDER("Feeds");
	
	private String Type;
	
	private ConfigurationGroupType(String Type) {
		this.Type = Type;
	}
	
	public String toString(){
		return this.Type;
	}
}
