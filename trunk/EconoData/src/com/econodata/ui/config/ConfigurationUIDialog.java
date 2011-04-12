package com.econodata.ui.config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import swing2swt.layout.BorderLayout;

import com.econodata.main.EconoMain;
import com.econodata.ui.resources.UIMessages;

public class ConfigurationUIDialog extends Dialog {

	private Label header;
	private Tree configurationTree;
	protected Object result;
	protected Shell shell;
	private final Shell parent;
	private ConfigurationAction action;
	private Composite configurationPanelComposite;
	private Composite configCompositeBody;
	private Composite composite_1;
	private Button btnOk;
	private Button btnCancel;
	private EconoMain econoMain=null;



	public Composite getConfigCompositeBody() {
		return configCompositeBody;
	}

	public Shell getShell() {
		return shell;
	}

	public Tree getConfigurationTree() {
		return configurationTree;
	}

	public Composite getConfigurationPanelComposite() {
		return configurationPanelComposite;
	}

	public ConfigurationAction getAction() {
		return action;
	}

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public ConfigurationUIDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public ConfigurationUIDialog(Shell parent,EconoMain econoMain) {
		this(parent, SWT.NONE);
		this.econoMain = econoMain;
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setLayout(new BorderLayout(0, 0));
		shell.setSize(700, 550);
		shell.setText("Configurações");
		
		Rectangle bounds = getParent().getBounds();
		Rectangle rect = getShell().getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		getShell().setLocation (x, y);
		
		configurationTree = new Tree(shell, SWT.BORDER);
		configurationTree.setLayoutData(BorderLayout.WEST);

		configurationTree.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {				
				displayConfigurationSelected((TreeItem)(e.item));				
			}
		});

		final Composite composite = new Composite(shell, SWT.BORDER);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new BorderLayout(0, 0));
		
		composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.EAST);
		composite_1.setLayout(new GridLayout(2, false));
		
		btnOk = new Button(composite_1, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ok();
			}

			private void ok() {
				dispatchOkForConfiguration();				
			}
		});
		btnOk.setText("    Ok    ");
		
		btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancel();
			}

			private void cancel() {
				dispatchCancelForConfiguration();
				
			}
		});
		btnCancel.setText("Cancel");
		
		
		rebuildCompositePanel();
		
		initComponents();
				
		//
	}

	public Composite rebuildCompositePanel(){
		configurationPanelComposite = new Composite(shell, SWT.NONE);
		configurationPanelComposite.setLayout(new BorderLayout(0, 0));
		configurationPanelComposite.setLayoutData(BorderLayout.CENTER);

		final Composite configCompositeHeader = new Composite(configurationPanelComposite, SWT.BORDER);
		configCompositeHeader.setLayoutData(BorderLayout.NORTH);
		configCompositeHeader.setLayout(new BorderLayout(0, 0));

		header = new Label(configCompositeHeader, SWT.NONE);
		//header.setFont(SWTResourceManager.getFont("Verdana", 12, SWT.BOLD));
		header.setLayoutData(BorderLayout.NORTH);
		header.setText(UIMessages.getMessage("PANEL_CONFIGURATION"));
		
		configCompositeBody = new Composite(configurationPanelComposite, SWT.NONE);
		configCompositeBody.setLayout(new BorderLayout(0, 0));
		configCompositeBody.setLayoutData(BorderLayout.CENTER);
		
		return configCompositeBody;
	}
	


	protected void dispatchCancelForConfiguration() {
		getAction().dispatchCancelForConfiguration();
		econoMain.refresh();
	}

	protected void dispatchOkForConfiguration() {
		getAction().dispatchOkForConfiguration();
		econoMain.refresh();
	}


	protected void displayConfigurationSelected(TreeItem treeItem) {
		getAction().displaySelected(treeItem);
		
	}

	private void initComponents() {
		action = new ConfigurationAction(this);
	}
	
	public Label getHeader() {
		return header;
	}

}
