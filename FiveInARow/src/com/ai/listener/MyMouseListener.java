package com.ai.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.ai.GameMain;
import com.ai.controller.Thinker;
import com.ai.view.MyJFrame;

public class MyMouseListener extends MouseAdapter {
	public void mouseClicked(MouseEvent e) {
		if (Thinker.round % 2 == 0)
			return;
		MyJFrame gobangJFrame = (MyJFrame) e.getSource();
		int before = Thinker.round;
		gobangJFrame.addPoint((new Point(e.getX(), e.getY())));
		gobangJFrame.repaint();
		if (before == Thinker.round)
			return;
		GameMain.total_steps++;
	}

}