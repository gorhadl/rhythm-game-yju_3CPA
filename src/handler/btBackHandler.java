package handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientUI;

public class btBackHandler implements ActionListener {
	ClientUI ui;
	public btBackHandler(ClientUI c) {
		ui = c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ui.b.setVisible(true);
		ui.setVisible(false);
	}
}
