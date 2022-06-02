package clientPanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class WelcomePanel extends  JPanel{
	public LoadingPanel pnLoading;
	private Graphics screenGraphic;
	private Image panelImage;
	private Image selectedImage ;
	private Image backgroundImage = new ImageIcon(getClass().getResource("../Images2/LoginBackGround.jpg")).getImage();
	public WelcomePanel() {
		setSize(1280, 720);
		setLayout(null);
		pnLoading = new LoadingPanel();
		pnLoading.setOpaque(false);
		pnLoading.setSize(1280, 720);
		pnLoading.setLocation(0, 200);
		add(pnLoading);
		
	
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, this);
		this.repaint();
	}
/*
public void paint(Graphics g) {
      panelImage = createImage(this.getWidth(), this.getHeight());
      screenGraphic= panelImage.getGraphics();
      screenDraw(screenGraphic);
      g.drawImage(panelImage, 0, 0, null);
}
public void screenDraw(Graphics g) {
       g.drawImage(backgroundImage, 0, 0, null);
       paintComponents(g);
       this.repaint();
}
*/
}
