package Java;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	public static int i = 1;

	public static void addI() {
		if (i < 7) {
			i++;
		} else {
			i = 1;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(".\\background\\background" + i + ".jpg");
		// 图片随窗体大小而变化
		g.drawImage(icon.getImage(), 0, 0, getSize().width, getSize().height, this);

	}
}
