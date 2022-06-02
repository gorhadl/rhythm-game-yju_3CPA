package CPA_TEAM_11;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../Images2/note3.png")).getImage();
	private Image noteBasicImage2 = new ImageIcon(Main.class.getResource("../Images2/note2.png")).getImage();
	private int x , y = 660 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("D")) {
			x=343;
		} else if(noteType.equals("F")) {
			x=493;
		} else if(noteType.equals("J")) {
			x=643;
		} else if(noteType.equals("K")) {
			x=793;
		}
		this.noteType = noteType;
	}
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("D") || noteType.equals("J")) {
			g.drawImage(noteBasicImage,x,y,null);
		} else {
			g.drawImage(noteBasicImage2,x,y,null);
		}
	}
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 660) {
			close();
		}
	}
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
				Thread.sleep(Main.SLEEP_TIME);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public String judge() {
		if(y >= 613) {
			close();
			return "Good";
		} else if ( y >= 590) {
			close();
			return "Perfect";
		} else if ( y >= 578) {
			close();
			return "Great";
		} else if ( y >= 566) {
			close();
			return "Not bad";
		} else if ( y >= 530) {
			close();
			return "Bad";
		}
		return "None";	
	}
	public int getY() {
		return y;
	}
}
