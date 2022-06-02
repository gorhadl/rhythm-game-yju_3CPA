package game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import client.ClientUI;
import clientPanel.Music;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameFrame.game == null)
			return;
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			GameFrame.game.pressD();
			
			new Music("drumSmall1.mp3", false).start();
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			GameFrame.game.pressF();
			
			new Music("drumSmall1.mp3", false).start();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_J) {
			GameFrame.game.pressJ();
			
			new Music("drumSmall1.mp3", false).start();
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			GameFrame.game.pressK();
			
			new Music("drumSmall1.mp3", false).start();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (GameFrame.game == null)
			return;

		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			GameFrame.game.releaseD();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			GameFrame.game.releaseF();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_J) {
			GameFrame.game.releaseJ();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			GameFrame.game.releaseK();
			
		}
	}

}
