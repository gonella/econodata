package com.econodata.web;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class JavascriptExec {
  public static void main(String[] args) {
    final String html = "<html><title>Snippet</title><body><p id='myid'>Best Friends</p><p id='myid2'>Cat and Dog</p></body></html>";
    Display display = new Display();
    final Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    final Browser browser = new Browser(shell, SWT.BORDER);
    Composite comp = new Composite(shell, SWT.NONE);
    comp.setLayout(new FillLayout(SWT.VERTICAL));
    final Text text = new Text(comp, SWT.MULTI);
    String string = "var _gaq = _gaq || []; _gaq.push(['_setAccount', 'UA-3944455-4']); _gaq.push(['_trackPageview']); (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();  ";
    
    text.setText(string);
    final Button button = new Button(comp, SWT.PUSH);
    button.setText("Execute Script");
    button.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        boolean result = browser.execute(text.getText());
        if (!result) {
          /* Script may fail or may not be supported on certain platforms. */
          System.out.println("Script was not executed.");
        }
      }
    });
    browser.setText(html);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}