package CPA_TEAM_11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread { //쓰레드는 하나의 작은 프로그램 프로그램안에 있는 프로그램임

	private Player player; //우리가받은 라이브러리 
	private boolean isLoop; // 현재곡이 무한반복인지 아닌지 에대한 설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) { //곡의제목, 반복여부
		try { //try 구문에서 오류가발생하면 catch문으로감
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI()); //해당파일실행
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //버퍼에 담아서 읽어올수있게
			player = new Player(bis); //플레이어는 해당파일을 담을수있도록해줌
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	public int getTime() { //현재 실행되고 있는 음악이 어떤위치에 실행 되고있는지 알려줌
		if (player == null)
			return 0;
		return player.getPosition();
	}
	public void close() { //음악이 언제 실행되고있던간에 항상종료할수있게
		isLoop = false;
		player.close();
		this.interrupt(); //해당 쓰레드를 중지상태로 만듬 곡을 실행하는 프로그램을 종료
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); //버퍼에 담아서 읽어올수있게
				player = new Player(bis); //플레이어는 해당파일을 담을수있도록해줌
			} while(isLoop);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
