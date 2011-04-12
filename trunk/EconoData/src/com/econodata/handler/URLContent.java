package com.econodata.handler;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class URLContent {

	private final static Logger logger = Logger
	.getLogger(URLContent.class);
	
	public static byte[] getBinaryURLContent(URL url) throws IOException {
		URLConnection conn = url.openConnection();
		InputStream in = new BufferedInputStream(conn.getInputStream());
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream(10000);
			int b;
			while ((b = in.read()) != -1) {
				bout.write(b);
			}
			return bout.toByteArray();
		} finally {
			in.close();
		}
	}

	
	public static ByteBuffer getAsByteArray(URL url) throws IOException {
	    URLConnection connection = url.openConnection();
	    // Since you get a URLConnection, use it to get the InputStream
	    InputStream in = connection.getInputStream();
	    // Now that the InputStream is open, get the content length
	    int contentLength = connection.getContentLength();

	    // To avoid having to resize the array over and over and over as
	    // bytes are written to the array, provide an accurate estimate of
	    // the ultimate size of the byte array
	    ByteArrayOutputStream tmpOut;
	    if (contentLength != -1) {
	        tmpOut = new ByteArrayOutputStream(contentLength);
	    } else {
	        tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate size
	    }

	    byte[] buf = new byte[512];
	    while (true) {
	        int len = in.read(buf);
	        if (len == -1) {
	            break;
	        }
	        tmpOut.write(buf, 0, len);
	    }
	    in.close();
	    tmpOut.close(); // No effect, but good to do anyway to keep the metaphor alive

	    byte[] array = tmpOut.toByteArray();

	    //Lines below used to test if file is corrupt
	    //FileOutputStream fos = new FileOutputStream("C:\\abc.pdf");
	    //fos.write(array);
	    //fos.close();

	    return ByteBuffer.wrap(array);
	}
	
	public static BufferedImage getImageByURL(byte[] bytes) throws IOException {
		
		
		BufferedImage img = null;
		try {
			String nomeDaImagem = "img" + System.currentTimeMillis() + ".jpg";
			// recupera a imagem do banco usando a entidade usuario, poderia ser qualquer byte para a classe ImageIO ler.
			img = ImageIO.read(new ByteArrayInputStream(bytes));
			ImageIO.write(img, "JPG", new File(nomeDaImagem));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
