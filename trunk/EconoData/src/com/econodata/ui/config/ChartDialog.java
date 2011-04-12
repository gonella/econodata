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

public class ChartDialog extends Dialog {

	private ChartConfigDialogAction action;

	protected Object result;
	protected Shell shell;

	private final int x;

	private final int y;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChartDialog(Shell parent,String title,int x,int y) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.x = x;
		this.y = y;
		setText(title);

	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), getStyle());
		int sizeX = x;
		int sizeY = y;
		shell.setSize(sizeX, sizeY);
		shell.setText(getText());
		shell.setLayout(new FormLayout());
		
		Rectangle bounds = getParent().getBounds();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);

	}


}
