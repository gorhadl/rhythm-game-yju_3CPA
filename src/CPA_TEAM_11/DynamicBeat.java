package CPA_TEAM_11;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.ClientNetwork;
import client.ClientUI;
import clientPanel.PnInfoPanel;

public class DynamicBeat extends JFrame {

	private int a = 0;
	private Image screenImage;// 두가지의 변수는 더블버퍼링을 위해서 전체화면에 대한 이미지를 담는 변수
	private Graphics screenGraphic; // """

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../Images2/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../Images2/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../ButtonImg/singlebutton.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../ButtonImg/singlebutton.png")); //basic으로 바꿔
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../ButtonImg/Multibutton.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../ButtonImg/Multibutton.png"));
	private ImageIcon exit2ButtonEnteredImage = new ImageIcon(Main.class.getResource("../ButtonImg/exitbutton.png"));
	private ImageIcon exit2ButtonBasicImage = new ImageIcon(Main.class.getResource("../ButtonImg/exitbutton.png"));
	
	private ImageIcon SelectedButtonBasic1 = new ImageIcon(Main.class.getResource("../SelectedSongImg/selectedbutton1.png"));
	private ImageIcon SelectedButtonBasic2 = new ImageIcon(Main.class.getResource("../SelectedSongImg/selectedbutton2.png"));
	private ImageIcon SelectedButtonBasic3 = new ImageIcon(Main.class.getResource("../SelectedSongImg/selectedbutton3.png"));
	
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../Images2/backButton2.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../Images2/backButton.png"));
	
	private ImageIcon bang1 = new ImageIcon(Main.class.getResource("../SelectedSongImg/bang1.png"));
	private ImageIcon bang2 = new ImageIcon(Main.class.getResource("../SelectedSongImg/bang2.png"));
	
	private Image background = new ImageIcon(Main.class.getResource("../Images2/bag.png")).getImage(); //지금 현 클래스의 위치에서 리소스를 가져온다음 jpgs폴더안에있는 jpg파일을 가져오겠다 그것의 이미지 인스턴스를 이미지 변수에 초기화 ;
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../Images2/menuBar.jpg")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton MultiButton = new JButton(quitButtonBasicImage);
	private JButton exit2Button = new JButton(exit2ButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private ImageIcon moonSelected = new ImageIcon(Main.class.getResource("../SelectedSongImg/moon.png"));
	private JLabel l = new JLabel(moonSelected);
	
	private ImageIcon titleImage1 = new ImageIcon(Main.class.getResource("../SelectedSongImg/font1.png"));
	private ImageIcon titleImage2 = new ImageIcon(Main.class.getResource("../SelectedSongImg/font2.png"));
	private ImageIcon titleImage3 = new ImageIcon(Main.class.getResource("../SelectedSongImg/font3.png"));

	private JLabel font = new JLabel(titleImage1);
	private JLabel font2 = new JLabel(titleImage2);
	
	private ImageIcon songImage1 = new ImageIcon(Main.class.getResource("../SelectedSongImg/songImage1.jpg"));
	private ImageIcon songImage2 = new ImageIcon(Main.class.getResource("../SelectedSongImg/songImage2.png"));
	private ImageIcon songImage3 = new ImageIcon(Main.class.getResource("../SelectedSongImg/songImage3.jpg"));
	
//	private ImageIcon rank = new ImageIcon(Main.class.getResource("../Images2/rank.png"));
//	private JLabel ranking = new JLabel(rank);
	
	private JLabel songImage = new JLabel(songImage1);
	
	private int listcount;
	private int backcount;
	
	private JButton Selected1 = new JButton(SelectedButtonBasic1);
	private JButton Selected11 = new JButton(SelectedButtonBasic1);
	private JButton Selected2 = new JButton(SelectedButtonBasic2);
	private JButton Selected3 = new JButton(SelectedButtonBasic3);
	
	private JButton Selected1ex = new JButton(SelectedButtonBasic1);
	private JButton Selected2ex = new JButton(SelectedButtonBasic2);
	private JButton Selected3ex = new JButton(SelectedButtonBasic3);
	
	private JButton bang1button = new JButton(bang1);
	private JButton bang2button = new JButton(bang2);
	
	private JLabel Selected4ex = new JLabel(SelectedButtonBasic3);
	private JLabel Selected5ex = new JLabel(SelectedButtonBasic3);
	
	private int mouseX, mouseY;
	
	private Music[] selectedMusic2 = new Music[3]; // private로바꿔라잉
	private Music introMusic = new Music("introMusic.mp3", true);
	private Music introAgain;
	
	private boolean isMainScreen = false; // 2의 코드
	private boolean isStartScreen = false;
	
	public static Game game;
	public boolean isKeyOk = false;
	
	private ClientUI ui;
	ClientNetwork nt;
	public PnInfoPanel pnInfo;
	
	public DynamicBeat() throws UnknownHostException, IOException {
		
		
		setUndecorated(true); // 기본적으로 보이는 메뉴바가 안보이게됨
		setTitle("CPA_STUDIO");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //게임창은 사용자가 인위적으로 줄이거나 늘릴수없음
		setLocationRelativeTo(null); //게임창이 컴퓨터의 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //이걸 안해주면 게임창을 끄더라도 컴퓨터 내부에서 게임이 돌아감
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null); // 나중에 뒤로 옮기자
	
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
//		ranking.setBounds(500,50,700,660);
//		add(ranking);
//		ranking.setVisible(true);
		
		exitButton.setBounds(1245,0,30,30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); //음악이 나오자마자 바로 종료가되서 버튼이 눌렸을때 음악이 들리지않음
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});

		add(exitButton);
//		exitButton.setVisible(false); 

		
		//----------------------------------------------
		
				
		font.setBounds(450,360,400,73);
		add(font);
		add(font2);
		
		Selected1.setBounds(450,360,400,73);
		Selected1.setBorderPainted(false);
		Selected1.setContentAreaFilled(false);
		Selected1.setFocusPainted(false);
		
		Selected11.setBounds(450,360,400,73);
		Selected11.setBorderPainted(false);
		Selected11.setContentAreaFilled(false);
		Selected11.setFocusPainted(false);
		
		Selected2.setBounds(550,460,400,73);
		Selected2.setBorderPainted(false);
		Selected2.setContentAreaFilled(false);
		Selected2.setFocusPainted(false);
		
		Selected3.setBounds(650,560,400,73);
		Selected3.setBorderPainted(false);
		Selected3.setContentAreaFilled(false);
		Selected3.setFocusPainted(false);
		
		Selected2ex.setBounds(550,260,400,73);
		Selected2ex.setBorderPainted(false);
		Selected2ex.setContentAreaFilled(false);
		Selected2ex.setFocusPainted(false);
		
		Selected3ex.setBounds(650,160,400,73);
		Selected3ex.setBorderPainted(false);
		Selected3ex.setContentAreaFilled(false);
		Selected3ex.setFocusPainted(false);
		
		Selected4ex.setBounds(750,610,400,73);
		Selected4ex.setVisible(false);
		Selected5ex.setBounds(750,110,400,73);
		Selected5ex.setVisible(false);
		
		add(Selected1);
		add(Selected2);
		add(Selected3);
		add(Selected2ex);
		add(Selected3ex);
		add(Selected4ex);
		add(Selected5ex);
		
		l.setBounds(1000, 100, 600, 600);
		add(l);
		
		songImage.setBounds(30,110,400,500);
		add(songImage);
		
		add(bang1button);
		add(bang2button);
		
		
		Selected1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Selected1.setIcon(SelectedButtonBasic1);
				Selected1.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Selected1.setIcon(SelectedButtonBasic1);
				Selected1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				setFocusable(true);
				gameStart(listcount);
				
			}
		});
		
		bang1button.setVisible(false);
		bang1button.setBounds(430,475,100,100);
		bang1button.setBorderPainted(false);
		bang1button.setContentAreaFilled(false);
		bang1button.setFocusPainted(false);
		bang1button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bang1button.setIcon(bang1);
				bang1button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bang1button.setIcon(bang1);
				bang1button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			public void mouseReleased(MouseEvent e) {
				SelectedMusic(listcount);
				switch(listcount) {
				case 0 : 
					font.setIcon(titleImage1); 
					font2.setIcon(titleImage3);
					songImage.setIcon(songImage1);break;
				case 1 : 
					
					font.setIcon(titleImage2);
					font2.setIcon(titleImage1);
					songImage.setIcon(songImage2);break;
				case 2 : 
					font.setIcon(titleImage3); 
					font2.setIcon(titleImage2); 
					songImage.setIcon(songImage3); break;
				}
				
				font2.setVisible(false);
				font.setBounds(450,360,400,73);
				font.setBounds(450,360,400,73);
				
				Selected1.setBounds(450,360,400,73);
				Selected2.setBounds(550,460,400,73);
				Selected3.setBounds(650,560,400,73);
				Selected2ex.setBounds(550,260,400,73);
				Selected3ex.setBounds(650,160,400,73);
				Selected5ex.setVisible(false);
			}
			public void mousePressed(MouseEvent e) {
				
				SelectedMusicStop(listcount);
				listcount--;
				if (listcount == -1) 
					listcount =2;
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
			
					font2.setVisible(true);
					font2.setBounds(500,310,400,73);
					font.setBounds(500,410,400,73);
					Selected1.setBounds(500,310,400,73);
					Selected3ex.setBounds(600,210,400,73);
					Selected2ex.setBounds(500,410,400,73);
					Selected2.setBounds(600,510,400,73);
					Selected3.setBounds(700,610,400,73);
					Selected5ex.setVisible(true);
					
					
			}
		});
		
		bang2button.setVisible(false);
		bang2button.setBounds(430,225,100,100);
		bang2button.setBorderPainted(false);
		bang2button.setContentAreaFilled(false);
		bang2button.setFocusPainted(false);
		bang2button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bang2button.setIcon(bang2);
				bang2button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bang2button.setIcon(bang2);
				bang2button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			public void mouseReleased(MouseEvent e) {
				SelectedMusic(listcount);
				switch(listcount) {
				case 0 : 
					font.setIcon(titleImage1); 
					font2.setIcon(titleImage2);
					songImage.setIcon(songImage1); break;
				case 1 : 
					font.setIcon(titleImage2);
					font2.setIcon(titleImage3); 
					songImage.setIcon(songImage2); break;
				case 2 : 
					font.setIcon(titleImage3); 
					font2.setIcon(titleImage1); 
					songImage.setIcon(songImage3); break;
				}
				font2.setVisible(false);
				font.setBounds(450,360,400,73);
				Selected1.setBounds(450,360,400,73);
				Selected2.setBounds(550,460,400,73);
				Selected3.setBounds(650,560,400,73);
				Selected2ex.setBounds(550,260,400,73);
				Selected3ex.setBounds(650,160,400,73);
				Selected4ex.setVisible(false);
				
			}
			public void mousePressed(MouseEvent e) {
				SelectedMusicStop(listcount);
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
					font2.setVisible(true);
					font2.setBounds(500,410,400,73);
					font.setBounds(500,310,400,73);
					Selected2ex.setBounds(600,210,400,73);
					Selected3ex.setBounds(700,110,400,73);
					Selected2.setBounds(500,310,400,73);
					Selected1.setBounds(500,410,400,73);
					Selected3.setBounds(600,510,400,73);
					Selected4ex.setVisible(true);
					listcount++;
					if (listcount == 3) 
						listcount =0;
			}
		});

		Selected11.setVisible(false);
		Selected1.setVisible(false);
		Selected2.setVisible(false);
		Selected3.setVisible(false);
		Selected2ex.setVisible(false);
		Selected3ex.setVisible(false);
		Selected4ex.setVisible(false);
		Selected5ex.setVisible(false);
		font.setVisible(false);
		font2.setVisible(false);
		l.setVisible(false);
		songImage.setVisible(false);
		bang1button.setVisible(false);
		bang2button.setVisible(false);
		
		startButton.setBounds(40,200,300,98);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mousePressed(MouseEvent e) {
				if(introAgain != null)
					introAgainStop();
				isStartScreen = true;
				backButton.setVisible(true);
				font2.setVisible(false);
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				introMusic.close(); // 맨처음 배경음악 멈춤
				
				SelectedMusic(listcount);
				
				startButton.setVisible(false);
				MultiButton.setVisible(false);
				exit2Button.setVisible(false);
				Selected1.setVisible(true);
				Selected2.setVisible(true);
				Selected3.setVisible(true);
				Selected2ex.setVisible(true);
				Selected3ex.setVisible(true);
				bang1button.setVisible(true);
				bang2button.setVisible(true);
				l.setVisible(true);
				songImage.setVisible(true);
				font.setVisible(true);
				
				repaint();
					
			}
		});
		
		backButton.setVisible(false);
		backButton.setBounds(1210,0,30,30);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				if(isMainScreen) {
					backMain();
				} else if (isStartScreen) {
					backStart();
					
				}
			}
		});

		add(backButton);

		add(startButton);
		
		MultiButton.setBounds(40,330,300,98);
		MultiButton.setBorderPainted(false);
		MultiButton.setContentAreaFilled(false);
		MultiButton.setFocusPainted(false);
		MultiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MultiButton.setIcon(quitButtonEnteredImage);
				MultiButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MultiButton.setIcon(quitButtonBasicImage);
				MultiButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
					unvisible();
					MultiStart();
					ClientUI.net.LoginA();
				}
		});

		add(MultiButton);
		
		exit2Button.setBounds(40,460,300,98);
		exit2Button.setBorderPainted(false);
		exit2Button.setContentAreaFilled(false);
		exit2Button.setFocusPainted(false);
		exit2Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exit2Button.setIcon(exit2ButtonEnteredImage);
				exit2Button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라가면 손가락모양
				Music buttonEnteredMusic = new Music("ButtonENteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exit2Button.setIcon(exit2ButtonBasicImage);
				exit2Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); //음악이 나오자마자 바로 종료가되서 버튼이 눌렸을때 음악이 들리지않음
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});

		add(exit2Button);
		
		menuBar.setBounds(0,0,1280,30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter( ) {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); //현재 스크린에 X좌표
				int y = e.getYOnScreen(); // ""
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		
		
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //1280 720 의 이미지를 만든후 screenImage에 넣어줌
		screenGraphic = screenImage.getGraphics(); //스크린 이미지를 이용해서 그래픽 객체를 얻어옴
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage,0,0 ,null); //게임창에 screenImage가 그려짐
		
	}
	public void screenDraw(Graphics2D g) {
		g.drawImage(background,0,0,null); //background 같이 단순한 이미지 같은것들을 그려줌 (변수 안에 이미지를 한동안 그려주는 함수)
		
		if(isMainScreen) {	
			game.screenDraw(g);
		} //2의코드 main스크린이 싱글버튼을 눌렀을떼 true값으로 변하면 그려주게하는 메소드
		paintComponents(g); //JLabel같은 것들을 JFrame 안에 추가하면 그것을 그려줌, 항상 고정되기 때문에 그런것들은 전부 paintComponent로 구현 (JLabel같은걸 그려주는 부분)
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	public void SelectedMusic(int a) {
	
		for(int i=0; i<3; i++) 
			selectedMusic2[i] = new Music("Song" + (i+1) + ".mp3", true);
		selectedMusic2[a].start();
	}
	public void SelectedMusicStop(int a) {
		selectedMusic2[a].close();
		
	}
	public void introAgainStart() {
		introAgain = new Music("introMusic.mp3", true);
		introAgain.start();
	}
	public void introAgainStop() {
		introAgain.close();
	}
	public void gameStart(int nowSelected) {
		
		isMainScreen=true;
		if(selectedMusic2[listcount] != null)
			SelectedMusicStop(listcount);
		Selected1.setVisible(false);
		Selected2.setVisible(false);
		Selected3.setVisible(false);
		Selected2ex.setVisible(false);
		Selected3ex.setVisible(false);
		Selected4ex.setVisible(false);
		Selected5ex.setVisible(false);
		font.setVisible(false);
		font2.setVisible(false);
		l.setVisible(false);
		bang1button.setVisible(false);
		bang2button.setVisible(false);
		songImage.setVisible(false);
		String s = "background" + (nowSelected+1);
		background = new ImageIcon(Main.class.getResource("../Images2/" + s + ".jpg")).getImage();
		backButton.setVisible(true);
		setFocusable(true);
		game = new Game("Song" + (nowSelected +1), "Song" + (nowSelected + 1) + ".mp3");
		game.start();
		
	}
	

	public void backMain() {
		Selected1.setVisible(true);
		Selected2.setVisible(true);
		Selected3.setVisible(true);
		Selected2ex.setVisible(true);
		Selected3ex.setVisible(true);
		font.setVisible(true);
		l.setVisible(true);
		bang1button.setVisible(true);
		bang2button.setVisible(true);
		songImage.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../Images2/bag.png")).getImage();
		SelectedMusic(listcount);
		game.close();
		isMainScreen=false;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		backStart();
		}
	}
	public void backStart() {
		SelectedMusicStop(listcount);
		
		Selected1.setVisible(false);
		Selected2.setVisible(false);
		Selected3.setVisible(false);
		Selected2ex.setVisible(false);
		Selected3ex.setVisible(false);
		Selected4ex.setVisible(false);
		Selected5ex.setVisible(false);
		font.setVisible(false);
		font2.setVisible(false);
		l.setVisible(false);
		bang1button.setVisible(false);
		bang2button.setVisible(false);
		songImage.setVisible(false);
		startButton.setVisible(true);
		MultiButton.setVisible(true);
		exit2Button.setVisible(true);
		backButton.setVisible(false);
		isStartScreen=false;
		songImage.setIcon(songImage1);
		listcount =0;
		introAgainStart();
	}
	public void MultiStart() {
		this.setVisible(false);
		if(!(introMusic == null))
			introMusic.close();
		if(!(introAgain == null))
			introAgain.close();
	}
	public void unvisible() {
		setVisible(false);
	}
}