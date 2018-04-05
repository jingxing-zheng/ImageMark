import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ImageMark {
	public static void MarkByImage(String markImage, String sourceImage, String out, String imageType) {
		try {
			File source = new File(sourceImage);
			File mark = new File(markImage);
			Image s = ImageIO.read(source);
			Image m = ImageIO.read(mark);
			int m_height = m.getHeight(null);
			int m_width = m.getWidth(null);
			int s_width = s.getWidth(null);
			int s_height = s.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(s_width, s_height, TYPE_INT_RGB);
			Graphics2D graphics2D = bufferedImage.createGraphics();
			// 水印坐标位于右下角
			int x = s_width - m_width;
			int y = s_height - m_height;
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(s.getScaledInstance(s_width, s_height, Image.SCALE_SMOOTH), 0, 0, null);
			ImageIcon imageIcon = new ImageIcon(markImage);
			Image icon = imageIcon.getImage();
			graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));
			graphics2D.drawImage(icon, x, y, null);
			graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			graphics2D.dispose();
			File file = new File(out, "markByImage." + imageType);
			ImageIO.write(bufferedImage, imageType, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Finish");
	}

	public static void markByText(String sourceImage, String out, String imageType, String text) {
		try {
			File source = new File(sourceImage);
			Image s = ImageIO.read(source);
			int s_width = s.getWidth(null);
			int s_heigth = s.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(s_width, s_heigth, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d = bufferedImage.createGraphics();
			graphics2d.drawImage(s, 0, 0,s_width,s_heigth, null);
			int fontSize=20;
			Font font = new Font("宋体", Font.PLAIN, fontSize);
			graphics2d.setColor(Color.BLACK);
			graphics2d.setFont(font);
			int x=s_width/2;
			int y=s_heigth-fontSize;
			graphics2d.drawString(text, x, y);
			graphics2d.dispose();
			File file=new File(out,"markByText."+imageType);
			ImageIO.write(bufferedImage, imageType, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finish Mark");
	}
}
