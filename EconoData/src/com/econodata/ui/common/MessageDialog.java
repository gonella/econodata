package com.econodata.ui.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MessageDialog extends Dialog {
	Object result;

	public MessageDialog (Shell parent, int style) {
		super (parent, style);
	}
	public MessageDialog (Shell parent) {
		this (parent, 0); // your default style bits go here (not the Shell's style bits)
	}
	public Object open () {
		Shell parent = getParent();
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText(getText());
		shell.setSize(200,100);
		
		Rectangle bounds = getParent().getBounds();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		
		
		// Your code goes here (widget creation, set result, etc).
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		return result;
	}
}