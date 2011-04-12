package com.econodata.model;

public enum SectorKey implements java.io.Serializable {

			Petroeo_e_Gás_e_Biocombustíeis(1),
			Mineração(2),
			Siderurgia_e_Metalurgia(3),
			Quimicos(4),
			Madeira_e_Papel(5),
			Embalagens(6),
			Materiais_Diversos(7),
			Material_de_Transporte(8),
			Equipamentos_Eletricos(9),
			Maquinas_e_Equipamentos(10),
			Serviços(11),
			Comercio_Bens_Industriais(12),
			Construção_e_Engenharia(13),
			Transporte(14),
			Alimentos(15),
			Bebidas(16),
			Fumo(17),
			Produtos_de_Uso_Pessoal_e_de_Limpeza(18),
			Saude(19),
			Comercio_e_Distribuição(20),
			Tecidos_e_Vestuario_e_Calçados(21),
			Utilidades_Dométicas(22),
			Mídia(23),
			Hotelaria(24),
			Lazer(25),
			Diversos(26),
			Comercio_Bens_de_Consumo(27),
			Computadores_e_Equipamentos(28),
			Programas_e_Serviços(29),
			Telefonia_Fixa(30),
			Telefonia_Movel(31),
			Energia_Elerica(32),
			Agua_e_Saneamento(33),
			Gás(34),
			Financeiros(35),
			Serviços_Financeiros_Diversos(37),
			Previdência_e_Seguros(38),
			Exploração_de_Imoveis(39),
			Holdings_Diversificadas(40),
			Outros(41),
			;

	private Integer index;
	private SectorKey(Integer c) {
		index = c;
	}

	public Integer getIndex() {
		return index;
	}
	public String toString(){
		return name();
	}
	
	public String convert(){
		
		String str=name().replaceAll("_", " ");
		
		return str;
	}
	
}
