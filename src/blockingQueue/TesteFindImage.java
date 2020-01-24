package blockingQueue;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class TesteFindImage {

	/*
	 * The image function receives an image that will be compared with an already
	 * determined image, checking if there is one image inside the other, all behind
	 * rgb, returning true or false, for existence.
	 */
	public static void Vixi(BufferedImage find, BufferedImage image, String caminho, double angulo) throws IOException {
		System.out.println(angulo);
		BufferedImage encontrar = rotateImage(find, angulo);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {

				boolean invalid = false;
				int k = x, l = y;
				for (int a = 0; a < encontrar.getWidth(); a++) {
					l = y;
					for (int b = 0; b < encontrar.getHeight(); b++) {
						if (encontrar.getRGB(a, b) != image.getRGB(k, l)) {
							invalid = true;
							break;
						} else {
							l++;
						}
					}
					if (invalid) {
						break;
					} else if (!invalid) {
						drawRectangle(image, x, y, encontrar.getWidth(), encontrar.getHeight());
						save(image, caminho);
					} else {
						k++;
					}

				}
			}

		}
		System.out.println("Finish");
	}

	/*
	 * Draw rectangle function, receives the largest image, the value of x and y,
	 * the width and height of the smaller image, and returns the larger image,
	 * marking the smaller image with a rectangle.
	 */
	public static void drawRectangle(BufferedImage image, int x, int y, int w, int h) {
		Graphics2D g2d = image.createGraphics();

		g2d.setColor(Color.RED);

		g2d.drawRect(x, y, w, h);

		g2d.dispose();
	}

	/*
	 * Save function receives an image, which is converted to byte, to be saved in a
	 * default folder
	 */
	public static void save(BufferedImage image, String caminho) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ImageIO.write(image, "png", baos);

		baos.flush();

		byte[] img = baos.toByteArray();

		baos.close();

		InputStream in = new ByteArrayInputStream(img);

		BufferedImage bImageFromConvert = ImageIO.read(in);

		ImageIO.write(bImageFromConvert, "png", new File(caminho));

	}

	/*
	 * This function picks a folder and returns a list of all files in it.
	 */
	static String fileChooser() {
		JFileChooser jfc = new JFileChooser(".");

		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			File selectedFile = jfc.getSelectedFile();
			String selectedFileString = selectedFile.toString();
			return selectedFileString;

		}
		return null;
	}

	public static BufferedImage rotateImage(BufferedImage rotateImage, double angle) {
		angle %= 360;
		if (angle < 0)
			angle += 360;
		int quadrants = (int) angle / 90;
		double restAngle = angle % 90;
		if (restAngle < 0)
			restAngle += 90;

		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(restAngle), rotateImage.getWidth() / 2.0, rotateImage.getHeight() / 2.0);

		double ytrans = tx.transform(new Point2D.Double(0.0, 0.0), null).getY();
		double xtrans = tx.transform(new Point2D.Double(0, rotateImage.getHeight()), null).getX();

		AffineTransform translationTransform = new AffineTransform();
		translationTransform.translate(-xtrans, -ytrans);
		tx.preConcatenate(translationTransform);

		BufferedImage b2 = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(rotateImage, null);

		AffineTransform fix = AffineTransform.getQuadrantRotateInstance(quadrants, b2.getWidth() / 2.0,
				b2.getHeight() / 2.0);
		return new AffineTransformOp(fix, AffineTransformOp.TYPE_BILINEAR).filter(b2, null);
	}

}