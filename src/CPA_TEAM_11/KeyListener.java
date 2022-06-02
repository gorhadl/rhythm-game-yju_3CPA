package CPA_TEAM_11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

public class KeyListener extends KeyAdapter {
	
	  
	    
	    public static String getCurrentTime(String timeFormat){
	        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
	    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(DynamicBeat.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
//			System.out.println(getCurrentTime("ss.SSS")); 노트 타임 구할때 썻던 메소드임
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressK();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(DynamicBeat.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
		}
	}
}
