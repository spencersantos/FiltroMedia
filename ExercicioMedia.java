import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Principal {

	public static void main(String[] args) {

		BufferedImage buf = null;
		Raster raster = null;
		int[] pixel = new int[1];
		int media = 0;

		try {
			buf = ImageIO.read(new File("lena.png"));
			raster = buf.getRaster();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		WritableRaster wRaster = buf.getRaster();

		for (int i = 0; i < buf.getWidth(); i++) {
			for (int j = 0; j < buf.getHeight(); j++) {

				media = (((i == 0 || j == 0) ? 0 : wRaster.getPixel(i - 1, j - 1, pixel)[0])
						+ ((j > 0) ? wRaster.getPixel(i, j - 1, pixel)[0] : 0)
						+ ((j == 0 || i == wRaster.getWidth() - 1) ? 0 : wRaster.getPixel(i + 1, j - 1, pixel)[0])
						+ ((i > 0) ? wRaster.getPixel(i - 1, j, pixel)[0] : 0) 
						+ wRaster.getPixel(i, j, pixel)[0]
						+ ((i < wRaster.getWidth() - 1) ? wRaster.getPixel(i + 1, j, pixel)[0] : 0)
						+ ((i == 0 || j == wRaster.getHeight() - 1) ? 0 : wRaster.getPixel(i - 1, j + 1, pixel)[0])
						+ ((j < wRaster.getHeight() - 1) ? wRaster.getPixel(i, j + 1, pixel)[0] : 0)
						+ ((i == wRaster.getWidth() - 1 || j == wRaster.getHeight() - 1) ? 0
								: wRaster.getPixel(i + 1, j + 1, pixel)[0]))
						/ 9;

				System.out.println("i = " + i + "    j = " + j + "   media = " + media);
				int[] insert = { media };
				wRaster.setPixel(i, j, insert);
			}
		}
		try {
			ImageIO.write(buf, "PNG", new File("newLena.png"));
			System.out.println("Rotina executada.");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}