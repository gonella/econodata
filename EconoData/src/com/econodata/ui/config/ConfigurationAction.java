package com.econodata.ui.config;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import com.econodata.ui.common.CommomAction;
import com.econodata.ui.common.PanelCompositeBase;

public class ConfigurationAction extends CommomAction{
	
	private Logger logger = Logger.getLogger(ConfigurationAction.class);

	private ConfigurationUIDialog configurationUI;
	private PanelCompositeBase[] panelConfigurationComposite;

	public ConfigurationUIDialog getUI() {
		return configurationUI;
	}

	public ConfigurationAction(ConfigurationUIDialog configurationUI) {
		super();
		this.configurationUI=configurationUI;
		
		init();
	}
	
	public void init() {
	
		panelConfigurationComposite=new PanelCompositeBase[]{
				new StockConfiguration(getUI().getConfigurationPanelComposite()),
				new StockConfigurationBySector(getUI().getConfigurationPanelComposite()),
				new ColumnConfiguration(getUI().getConfigurationPanelComposite())
		};
		
		//getUI().getConfigurationPanelComposite().
				
		TreeItem tItem;
		for(int i=0;i<panelConfigurationComposite.length;i++){
				tItem=new TreeItem(getUI().getConfigurationTree(),0);
				tItem.setText(panelConfigurationComposite[i].getTitle());
				tItem.setData(panelConfigurationComposite[i]);
					
				panelConfigurationComposite[i].setVisible(false);
		}
		
	}

	
	public void refresh() {
		
	}
	
	public void displaySelected(TreeItem tItem) {
		PanelCompositeBase cSelected = getTreeItemSelected(tItem);
			
			
			if(cSelected!=null){
				logger.info("Configuration selected :"+cSelected.getTitle());
				
				if ((getUI().getConfigurationPanelComposite() != null) && (!getUI().getConfigurationPanelComposite().isDisposed())) {
					getUI().getConfigurationPanelComposite().dispose();
				}				

				Composite parent = getUI().rebuildCompositePanel();				
				cSelected=cSelected.rebuild(getUI().rebuildCompositePanel());
				
				//getUI().getHeader().setText(cSelected.getType().toString());
				
				cSelected.setVisible(true);
				getUI().getShell().layout(true);
				
				
				
			}
		
	}
	
	private PanelCompositeBase getTreeItemSelected(TreeItem tItem){
		
		PanelCompositeBase cSelected=null;
		
		if(tItem!=null && tItem.getData()!=null){
			
			if(tItem.getData() instanceof PanelCompositeBase){
				cSelected = (PanelCompositeBase)tItem.getData();
			}
		}
		return cSelected;
		
	}

	public void dispatchOkForConfiguration() {
		logger.debug("Applying the configuration on system");
		getUI().getShell().close();
	}

	public void dispatchCancelForConfiguration() {
		logger.debug("Canceling of apply configuration on system");
		getUI().getShell().close();
	}
	

}
