package com.econodata.ui.common;

import org.eclipse.swt.widgets.Composite;

import com.econodata.ui.config.StockConfigurationAction;

public abstract class PanelCompositeBase extends Composite{

	private final String title;
	private final int config;
		
	public int getConfig() {
		return config;
	}

	public String getTitle() {
		return title;
	}

	public PanelCompositeBase(Composite composite, int config,String title) {
		super(composite,config);
		this.config = config;
		this.title = title;
	
		//initAction();
	}
	
	public abstract PanelCompositeBase rebuild(Composite parent);
	
	//public abstract void createComponents(Composite parent);
	//public abstract void initAction();
}
