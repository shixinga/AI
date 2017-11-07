package com.ai;

import java.awt.MouseInfo;
import java.awt.Point;

import com.ai.view.MyJFrame;

public class GameMain {
	public static MyJFrame gameFrame;
	public static int total_steps = 0;
	public static int game_over = 0;

	public static void main(String args[]) {
		gameFrame = new MyJFrame();

		while (true) {
			int index_last_steps = total_steps;
			@SuppressWarnings("unused")
			Point get_point = MouseInfo.getPointerInfo().getLocation();
			if (total_steps > index_last_steps)
				MyJFrame.aiOperate();

		}
	}
}