package com.ai.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ai.GameMain;
import com.ai.controller.Thinker;
import com.ai.listener.MyMouseListener;

import model.PiecePoint;



public class MyJFrame extends JFrame {
	//黑棋:1 白：-1
	public final static int SIZE = 15;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	
	static int r = 23;
	static final int GRID_WIDTH = WIDTH / SIZE;
	static final int GRID_HEIGHT = HEIGHT / SIZE, beg = 60;
	ArrayList<Point> mPoints = null;

	public MyJFrame() {
		mPoints = new ArrayList<Point>();
		this.setTitle("五子棋");
		this.setSize(WIDTH + 100, HEIGHT + 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.addMouseListener(new MyMouseListener());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics graphic) {
		super.paint(graphic);
		
		//绘制棋格
		graphic.setColor(Color.GRAY);
		for (int i = 0; i <= SIZE; i++) {
			graphic.drawLine(beg + i * GRID_WIDTH, beg, beg + i * GRID_WIDTH, beg + SIZE * GRID_HEIGHT);
		}
		for (int i = 0; i <= SIZE; i++) {
			graphic.drawLine(beg, beg + i * GRID_HEIGHT, beg + SIZE * GRID_WIDTH, beg + i * GRID_HEIGHT);
		}

		//绘制棋子
		Iterator<Point> it = mPoints.iterator();
		Color color = graphic.getColor();
		while (it.hasNext()) {
			PiecePoint point = (PiecePoint) it.next();
			if (point.color == 1)
				graphic.setColor(Color.black);
			else
				graphic.setColor(Color.white);
			graphic.fillOval(point.x - r, point.y - r, 2 * r, 2 * r);
		}
		graphic.setColor(Color.black);
		System.out.println();

		int outcome = Thinker.judgeOutcome(Thinker.Chessboard);
		
		
		
		if (outcome != 0) {
			mPoints.clear();
			for (int i = 0; i <= SIZE; i++) {
				for (int j = 0; j <= SIZE; j++) {
					Thinker.Chessboard[i][j] = 0;
				}
			}
			Thinker.round = 1;
			GameMain.total_steps = 0;
		}
		
		if (outcome == 1) {
			try{
				JOptionPane.showMessageDialog(null, "黑方获胜，重新开始！");
				//return;
			}catch(Exception e){
				
			}

		} else if (outcome == -1) {
			try{
				JOptionPane.showMessageDialog(null, "白方获胜，重新开始！");
				
			}catch(Exception e){
				
			}
			

		}
	}

	public double dis(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	public void addPoint(Point point) {
		int flag = 0;
		for (int i = 0; i <= SIZE; i++) {
			for (int j = 0; j <= SIZE; j++) {
				if (dis(beg + i * GRID_WIDTH, beg + j * GRID_HEIGHT, point.x, point.y) < 25) {
					flag = 1;
					point.x = beg + i * GRID_WIDTH;
					point.y = beg + j * GRID_HEIGHT;
					break;
				}
			}
			if (flag == 1)
				break;
		}
		if (flag == 0)
			return;
		Iterator<Point> i = mPoints.iterator();
		while (i.hasNext()) {
			PiecePoint k = (PiecePoint) i.next();
			if (k.x == point.x && k.y == point.y) {
				JOptionPane.showMessageDialog(null, "该位置已经有棋子，请选择另一个位置");
				return;
			}
		}

		PiecePoint temp = new PiecePoint(point.x, point.y);
		if (Thinker.round % 2 == 1) {
			temp.color = 1;
		} else {
			temp.color = -1;
		}
		Thinker.Chessboard[(temp.y - beg) / GRID_HEIGHT][(temp.x - beg) / GRID_WIDTH] = temp.color;
		Thinker.round++;
		mPoints.add(temp);
	}

	public static void aiOperate() {
		if (Thinker.judgeOutcome(Thinker.Chessboard) != 0)
			return;
		MyJFrame gobangJFrame = GameMain.gameFrame;
		Point aipoint = new Point(Thinker.round / 2 % (Thinker.SIZE + 1), Thinker.round / 2 / (Thinker.SIZE + 1));
		int current_max = -Thinker.INF;
		for (int i = 0; i <= Thinker.SIZE; i++) {
			for (int j = 0; j <= Thinker.SIZE; j++) {
				if (Thinker.Chessboard[i][j] != 0)
					continue;
				if (Thinker.round <= 4) {
					int flag = 1;
					if (i >= 1 && i <= SIZE - 1 && j >= 1 && j <= SIZE - 1) {
						for (int k = i - 1; k <= i + 1; k++) {
							for (int l = j - 1; l <= j + 1; l++) {
								if (Thinker.Chessboard[k][l] == -1 || Thinker.Chessboard[k][l] == 1)
									flag = 0;
							}
							if (flag == 0)
								break;
						}
					}
					if (flag == 1)
						continue;
				}
				int current_Chessborad[][] = new int[20][20];
				Thinker.copy(Thinker.Chessboard, current_Chessborad);
				current_Chessborad[i][j] = -1;
				int current_score = Thinker.alphabetaScore(current_Chessborad, 1, -Thinker.INF, Thinker.INF, 1);
				// System.out.print(k+" ");
				if (current_score > current_max) {
					current_max = current_score;
					aipoint.x = j;
					aipoint.y = i;
				}
			}
		}
		aipoint.x *= MyJFrame.GRID_WIDTH;
		aipoint.x += MyJFrame.beg;
		aipoint.y *= MyJFrame.GRID_HEIGHT;
		aipoint.y += MyJFrame.beg;
		gobangJFrame.addPoint(aipoint);
		gobangJFrame.repaint();
	}

}

