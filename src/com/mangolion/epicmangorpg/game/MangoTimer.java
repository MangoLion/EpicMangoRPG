package com.mangolion.epicmangorpg.game;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.mangolion.epicmangorpg.frames.FrameGame;

public class MangoTimer extends Timer{

	public MangoTimer(int delay, ActionListener listener) {
		super(delay, listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		FrameGame.instance.btnNextTick.setText("Cancel");
		FrameGame.instance.paneCommand.setVisible(false);
		super.start();
	}
	
	@Override
	public void stop() {
		FrameGame.instance.btnNextTick.setText("Go");
		FrameGame.instance.paneCommand.setVisible(true);
		super.stop();
	}
}
