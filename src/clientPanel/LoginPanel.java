package clientPanel;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import CPA_TEAM_11.Main;
import client.ClientUI;
import handler.BtSingnUpHandler;

public class LoginPanel extends JPanel {
	public JTextField tfid;
	public JButton btlogin;
	public JButton btsign;
	public JPasswordField tfpw;
	public JLabel logo;
	private Graphics screenGraphic;
	private Image panelImage;
	private Image selectedImage ;
	private Image backgroundImage = new ImageIcon(getClass().getResource("../Images2/LoginBackGround.jpg")).getImage();

	
	
	public LoginPanel() {
		init();
	}
	
	private void init() {
		setSize(1280, 720);
		setLayout(null);
		
		
		tfid = new JTextField();
		tfid.setBounds(480,200,310,30);
		add(tfid);
		tfid.setColumns(10);
		
		tfpw = new JPasswordField();
		tfpw.setBounds(480,270,310,30);
		add(tfpw);
		
//		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514 : ");
//		lblNewLabel_1.setForeground(Color.WHITE);
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_1.setFont(new Font("함초롬바탕", Font.BOLD | Font.ITALIC, 15));
//		lblNewLabel_1.setBounds(167, 455, 62, 18);
//		add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_2 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC : ");
//		lblNewLabel_2.setForeground(Color.WHITE);
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_2.setFont(new Font("함초롬바탕", Font.BOLD | Font.ITALIC, 15));
//		lblNewLabel_2.setBounds(138, 510, 91, 18);
//		add(lblNewLabel_2);
		
		btlogin = new JButton("");
		URL url1 = getClass().getResource("../Images2/login.png");
		btlogin.setBounds(490,400,300,65);
		btlogin.setIcon(new ImageIcon(url1));
		btlogin.setBorderPainted(false);
		btlogin.setFocusPainted(false);
		btlogin.setBackground(new Color(255, 0, 0, 0));
		add(btlogin);
		
		btsign = new JButton("");
		URL url2 = getClass().getResource("../Images2/adduser.png");
		btsign.setBounds(490,500,300,65);
		btsign.setIcon(new ImageIcon(url2));
		btsign.setBorderPainted(false);
		btsign.setFocusPainted(false);
		btsign.setBackground(new Color(255, 0, 0, 0));
		add(btsign);
	
//		btnNewC.setBounds(490,400,300,65);
//		btnNewC.setBorderPainted(false);
//		btnNewC.setContentAreaFilled(false);
//		btnNewC.setFocusPainted(false);
//		
//		add(btnNewC);
//		
//		btnLogin.setBounds(490,500,300,65);
//		btnLogin.setBorderPainted(false);
//		btnLogin.setContentAreaFilled(false);
//		btnLogin.setFocusPainted(false);
//		
//		add(btnLogin);
//		
//		btnInit.setBounds(490,600,300,65);
//		btnInit.setBorderPainted(false);
//		btnInit.setContentAreaFilled(false);
//		btnInit.setFocusPainted(false);
//		
//		add(btnInit);
//        
		
		
		
		
	}
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
}
