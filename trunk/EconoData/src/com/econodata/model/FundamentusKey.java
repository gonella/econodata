package com.econodata.model;

public enum FundamentusKey implements java.io.Serializable{

	Nome("BODY[0].DIV[0].DIV[0].DIV[1].P[0].STRONG[0].CONTENT[0]"),
	Tipo("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[1].TD[1].SPAN[0].CONTENT[0]"),
	Empresa("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[2].TD[1].SPAN[0].CONTENT[0]"),
	Setor("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[3].TD[1].SPAN[0].A[0].CONTENT[0]"),
	Subsetor("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[4].TD[1].SPAN[0].A[0].CONTENT[0]"),	
	Valor_de_Firma("BODY[0].DIV[0].DIV[1].TABLE[1].TBODY[0].TR[0].TD[1].SPAN[0].CONTENT[0]"),
	Valor_da_Firma("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[4].TD[3].SPAN[0].CONTENT[0]"),
		
	Cotação("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[0].TD[3].SPAN[0].CONTENT[0]"),
	Data_da_Ultima_Cotação("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[1].TD[3].SPAN[0].CONTENT[0]"),
	Minima_em_52_Semanas("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[2].TD[3].SPAN[0].CONTENT[0]"),
	Maxima_em_52_Semanas("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	Volume_Financeiro_Medio_2_Meses("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[4].TD[3].SPAN[0].CONTENT[0]"),
	Ultimo_Balanço_Processado("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[1].TD[3].SPAN[0].CONTENT[0]"),
	Numero_de_Ações("BODY[0].DIV[0].DIV[1].TABLE[1].TBODY[0].TR[1].TD[3].SPAN[0].CONTENT[0]"),
	
	//INDICADORES FUNDAMENTALISTA
	
	Preço_Lucro("BODY[0].DIV[0].DIV[1].TABLE[1].TBODY[0].TR[1].TD[1].SPAN[0].CONTENT[0]"),
	P_VP("BODY[0].DIV[0].DIV[1].TABLE[1].TBODY[0].TR[0].TD[3].SPAN[0].CONTENT[0]"),
	P_EBIT("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[3].TD[1].SPAN[0].CONTENT[0]"),
	PSR("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[4].TD[1].SPAN[0].CONTENT[0]"),
	Preço_Ativos("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[5].TD[1].SPAN[0].CONTENT[0]"),
	Preço_Capital_de_Giro("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[2].TD[3].SPAN[0].CONTENT[0]"),
	Preço_de_Ativo_Circulante_Liquido("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[7].TD[1].SPAN[0].CONTENT[0]"),
	Dividend_Yield("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[2].TD[3].SPAN[0].CONTENT[0]"),
	EV_EBIT("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[9].TD[1].SPAN[0].CONTENT[0]"),
	Giro_Ativos("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[10].TD[1].SPAN[0].CONTENT[0]"),
	Crescimento_da_Receita_Liquida_no_Ultimos_5_Anos("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	
	LPA("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	VPA("BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR[0].TD[3].SPAN[0].CONTENT[0]"),
	Margem_Bruta("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	Margem_EBIT("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[4].TD[3].SPAN[0].CONTENT[0]"),
	Margem_Liquida("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[1].TD[3].SPAN[0].CONTENT[0]"),
	EBIT_Ativo("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[6].TD[3].SPAN[0].CONTENT[0]"),
	ROIC("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[7].TD[3].SPAN[0].CONTENT[0]"),
	ROE("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[5].TD[3].SPAN[0].CONTENT[0]"),
	Liquidez_Corrente("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[9].TD[3].SPAN[0].CONTENT[0]"),
	Divida_Bruta_Total_Divida_Pelo_Patrimonio("BODY[0].DIV[0].DIV[1].TABLE[2].TBODY[0].TR[10].TD[3].SPAN[0].CONTENT[0]"),
	
	//Dados Balanço Patrimonial

	Ativo("BODY[0].DIV[0].DIV[1].TABLE[3].TBODY[0].TR[1].TD[1].SPAN[0].CONTENT[0]"),
	Disponibilidade("BODY[0].DIV[0].DIV[1].TABLE[3].TBODY[0].TR[2].TD[1].SPAN[0].CONTENT[0]"),
	Ativo_Circulante("BODY[0].DIV[0].DIV[1].TABLE[3].TBODY[0].TR[3].TD[1].SPAN[0].CONTENT[0]"),
	Divida_Bruta("BODY[0].DIV[0].DIV[1].TABLE[3].TBODY[0].TR[1].TD[3].SPAN[0].CONTENT[0]"),
	Divida_Liquida("BODY[0].DIV[0].DIV[1].TABLE[3].TBODY[0].TR[2].TD[3].SPAN[0].CONTENT[0]"),	
	Patrimonio_Liquido("BODY[0].DIV[0].DIV[1].TABLE[3].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	
	//Dados demonstrativos de resultados

	Receita_Liquida_em_12_Meses("BODY[0].DIV[0].DIV[1].TABLE[4].TBODY[0].TR[2].TD[1].SPAN[0].CONTENT[0]"),
	EBIT_dos_Ultimos_12_Meses("BODY[0].DIV[0].DIV[1].TABLE[4].TBODY[0].TR[3].TD[1].SPAN[0].CONTENT[0]"),
	Lucro_Liquido_dos_Ultimos_12_Meses("BODY[0].DIV[0].DIV[1].TABLE[4].TBODY[0].TR[4].TD[1].SPAN[0].CONTENT[0]"),
	Receita_Liquida_dos_Ultimos_3_Meses("BODY[0].DIV[0].DIV[1].TABLE[4].TBODY[0].TR[2].TD[3].SPAN[0].CONTENT[0]"),
	EBIT_dos_Ultimos_3_Meses("BODY[0].DIV[0].DIV[1].TABLE[4].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	Lucro_Liquido_dos_Ultimos_3_Meses("BODY[0].DIV[0].DIV[1].TABLE[4].TBODY[0].TR[3].TD[3].SPAN[0].CONTENT[0]"),
	
	;

	private String html;
	private FundamentusKey(String c) {
		html = c;
	}

	public String getCode() {
		return html;
	}
	public String toString(){
		return getCode();
	}
	
	public String convert(){
		
		String str=name().replaceAll("_", " ");
		
		return str;
	}
	
	
}
