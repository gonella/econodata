package com.econodata.ui.config;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChartConfigDialog extends Dialog {

	private ChartConfigDialogAction action;

	protected Object result;
	private Shell shell;

	private List listColumn;
	ArrayList<String> stockSelected;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChartConfigDialog(Shell parent) {
		super(parent,SWT.CLOSE);//, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		setText("Selecionar...");
		this.stockSelected=stockSelected;

	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		getShell().open();
		getShell().layout();
		Display display = getParent().getDisplay();
		while (!getShell().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		setShell(new Shell(getParent(), getStyle()));
		int sizeX = 196;
		int sizeY = 300;
		getShell().setSize(sizeX, sizeY);
		getShell().setText(getText());
		getShell().setLayout(new FormLayout());
		
		Rectangle bounds = getParent().getBounds();
		Rectangle rect = getShell().getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		getShell().setLocation (x, y);
		
		/*
		 *NO MEIO DA TELA
		 * Monitor primary = getParent().getDisplay().getPrimaryMonitor ();
		Rectangle bounds = primary.getBounds ();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		*/
		
		setListColumn(new List(getShell(), SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI));
		FormData fd_list = new FormData();
		fd_list.left = new FormAttachment(0, 10);
		fd_list.right = new FormAttachment(0, 176);
		fd_list.bottom = new FormAttachment(100, -44);
		getListColumn().setLayoutData(fd_list);
		
		Label lblSelecionarColuna = new Label(getShell(), SWT.NONE);
		fd_list.top = new FormAttachment(lblSelecionarColuna, 6);
		FormData fd_lblSelecionarColuna = new FormData();
		fd_lblSelecionarColuna.left = new FormAttachment(0, 10);
		fd_lblSelecionarColuna.top = new FormAttachment(0, 10);
		lblSelecionarColuna.setLayoutData(fd_lblSelecionarColuna);
		lblSelecionarColuna.setText("Selecionar coluna");
		
		Button btnApresentar = new Button(getShell(), SWT.NONE);
		btnApresentar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				action.doChart();
			}
		});
		FormData fd_btnApresentar = new FormData();
		fd_btnApresentar.right = new FormAttachment(getListColumn(), 0, SWT.RIGHT);
		fd_btnApresentar.top = new FormAttachment(getListColumn(), 6);
		fd_btnApresentar.left = new FormAttachment(getListColumn(), 0, SWT.LEFT);
		btnApresentar.setLayoutData(fd_btnApresentar);
		btnApresentar.setText("Apresentar");

		action=new ChartConfigDialogAction(this);
	}

	public void setListColumn(List listColumn) {
		this.listColumn = listColumn;
	}

	public List getListColumn() {
		return listColumn;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public Shell getShell() {
		return shell;
	}

	

}
