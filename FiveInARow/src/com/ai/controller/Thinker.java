package com.ai.controller;

import com.ai.view.MyJFrame;

public class Thinker {

	public static int round = 1;
	public final static int SIZE = MyJFrame.SIZE;
	public static int Chessboard[][] = new int[20][20];
	public final static int INF = 10000;

	public static int count=0;
	public static void copy(int a[][], int b[][]) {
		for (int i = 0; i <= SIZE; i++) {
			for (int j = 0; j <= SIZE; j++) {
				b[i][j] = a[i][j];
			}
		}
	}

	// �ڷ�1���׷�-1��other:0
	public static int judgeOutcome(int Chessboard[][]) {
		for (int i = 0; i <= SIZE; i++) {
			int continuous_num = 0;
			int current_role = 0; 
			// ɨ��ÿ��
			
			for (int j = 0; j <= SIZE; j++) {
				if (Chessboard[i][j] != current_role) {
					current_role = Chessboard[i][j];
					continuous_num = 1;
				} else {
					if (current_role != 0)
						continuous_num++;
				}
				if (continuous_num == 5) {
					return current_role;
				}
			}
			continuous_num = 0;
			current_role = 0; 
			// ɨ��ÿ��
			for (int j = 0; j <= SIZE; j++) {
				if (Chessboard[j][i] != current_role) {
					current_role = Chessboard[j][i];
					continuous_num = 1;
				} else {
					if (current_role != 0)
						continuous_num++;
				}
				if (continuous_num == 5) {
					return current_role;
				}
			}
			continuous_num = 0;
			current_role = 0;
			//  /�°벿��
			for (int j = 0; i + j <= SIZE; j++) {
				if (Chessboard[i + j][j] != current_role) {
					current_role = Chessboard[i + j][j];
					continuous_num = 1;
				} else {
					if (current_role != 0)
						continuous_num++;
				}
				if (continuous_num == 5) {
					return current_role;
				}
			}
			
			//  /�ϰ벿��
			continuous_num = 0;
			current_role = 0;
			for (int j = 0; i + j <= SIZE; j++) {
				if (Chessboard[j][i + j] != current_role) {
					current_role = Chessboard[j][i + j];
					continuous_num = 1;
				} else {
					if (current_role != 0)
						continuous_num++;
				}
				if (continuous_num == 5) {
					return current_role;
				}
			}
			
			// \�ϰ벿��
			continuous_num = 0;
			current_role = 0;
			for (int j = 0; i - j >= 0; j++) {
				if (Chessboard[j][i - j] != current_role) {
					current_role = Chessboard[j][i - j];
					continuous_num = 1;
				} else {
					if (current_role != 0)
						continuous_num++;
				}
				if (continuous_num == 5) {
					return current_role;
				}
			}
			
			// \�°벿��
			continuous_num = 0;
			current_role = 0;
			for (int j = 0; i + j <= SIZE; j++) {
				if (Chessboard[i + j][SIZE - j] != current_role) {
					current_role = Chessboard[i + j][SIZE - j];
					continuous_num = 1;
				} else {
					if (current_role != 0)
						continuous_num++;
				}
				if (continuous_num == 5) {
					return current_role;
				}
			}
		}
		return 0;
	}

	public static int getMax(int a, int b) {
		if (a > b)
			return a;
		return b;
	}

	public static int getMin(int a, int b) {
		if (a < b)
			return a;
		return b;
	}

	public static int alphabetaScore(int ChessBoard[][], int player, int alpha, int beta, int depth) {
		if (depth == 2)
			return evaluate(ChessBoard);

		for (int i = 0; i <= SIZE; i++) {
			for (int j = 0; j <= SIZE; j++) {
				if (ChessBoard[i][j] != 0)
					continue;
				//�����ǰλ��Ϊ��
				int current_board[][] = new int[20][20];
				copy(ChessBoard, current_board);
				
				//���λ��������˱���Ҫ�µ�����
				current_board[i][j] = player;
				
				//�ݹ飬�������
				int v = alphabetaScore(current_board, player * -1, alpha, beta, depth + 1);
				
				if (player == -1)                                                                                                          
					alpha = getMax(alpha, v);
				else
					beta = getMin(beta, v);
				
				//��֦����max��     ���㵽��ǰ�ĵ㣬������Ƚ�С�������ڵ��ֵ����Сֵ��Ϊ����
				//��ʱ���ڵ������ͬ���ڵ����֪������ֵС����ô��ʱû�б�Ҫ�ٶ�������ڵ�������������������
				if (beta <= alpha){    
					break;
				}
			}
		}
		if (player == -1)
			return alpha;
		count++;
		return beta;
	}

	/*
	 * ���Ȼ�ȡ˫���������ͻ���������� 
	 * �Ժڰ׷���ͬ������Ȩ�ز�ͬ�� 
	 * ����û��Σ�գ���ֵ���Լ������������Ϊ��Ҫ������
	 * ����Է���ռ���ƣ������Է�������� 
	 * ����������Ļ���������Ƚ��з��ء�
	 * ����Щ��������Ȩֵ����Ϊ��ֵ������
	 */
	public static int evaluate(int Chessboard[][]) {
		int evaluate_value = 0;
		// [0]:computer
		//[2]:person
		int alive_two[] = new int[3];
		int alive_three[] = new int[3];
		int alive_four[] = new int[3];
		int weak_three[] = new int[3], weak_four[] = new int[3];
		int alive_five[] = new int[3];
		for (int i = 0; i <= SIZE; i++) {
			int temp_sum = 0;
			int current_role = 0; 
			// ����ÿ�У���������ɨ
			for (int j = 0; j <= SIZE; j++) {
				//��ʼ����ɫ hmj
				if (Chessboard[i][j] != current_role) {
					current_role = Chessboard[i][j];
					temp_sum = 1;
				} else {
					//����
					if (current_role != 0)
						temp_sum++;
				}
				if (temp_sum == 2) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)//�����������Χ��
						if (Chessboard[i][j - temp_sum] == 0 && Chessboard[i][j + 1] == 0)
							alive_two[current_role + 1]++;
				} 
				else if (temp_sum == 3) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[i][j - temp_sum] == 0 && Chessboard[i][j + 1] == 0)
							alive_three[current_role + 1]++;
					weak_three[current_role + 1]++;
				} 
				else if (temp_sum == 4) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[i][j - temp_sum] == 0 && Chessboard[i][j + 1] == 0)
							alive_four[current_role + 1]++;
					weak_four[current_role + 1]++;
				} else if (temp_sum == 5) {
					alive_five[current_role + 1]++;
				}
			}
			
			temp_sum = 0;
			current_role = 0; // ����ÿ�У���������ɨ
			for (int j = 0; j <= SIZE; j++) {
				if (Chessboard[j][i] != current_role) {
					current_role = Chessboard[j][i];
					temp_sum = 1;
				} else {
					if (current_role != 0)
						temp_sum++;
				}
				if (temp_sum == 2) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[j - temp_sum][i] == 0 && Chessboard[j + 1][i] == 0)
							alive_two[current_role + 1]++;
				} else if (temp_sum == 3) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[j - temp_sum][i] == 0 && Chessboard[j + 1][i] == 0)
							alive_three[current_role + 1]++;
					weak_three[current_role + 1]++;
				} else if (temp_sum == 4) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[j - temp_sum][i] == 0 && Chessboard[j + 1][i] == 0)
							alive_four[current_role + 1]++;
					weak_four[current_role + 1]++;
				} else if (temp_sum == 5) {
					alive_five[current_role + 1]++;
				}
			}
			
			
			temp_sum = 0;
			current_role = 0;
			for (int j = 0; i + j <= SIZE; j++) {
				if (Chessboard[i + j][j] != current_role) {
					current_role = Chessboard[i + j][j];
					temp_sum = 1;
				} else {
					if (current_role != 0)
						temp_sum++;
				}
				if (temp_sum == 2) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[i + j - temp_sum][j - temp_sum] == 0 && Chessboard[i + j + 1][j + 1] == 0)
							alive_two[current_role + 1]++;
				} else if (temp_sum == 3) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[i + j - temp_sum][j - temp_sum] == 0 && Chessboard[i + j + 1][j + 1] == 0)
							alive_three[current_role + 1]++;
					weak_three[current_role + 1]++;
				} else if (temp_sum == 4) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[i + j - temp_sum][j - temp_sum] == 0 && Chessboard[i + j + 1][j + 1] == 0)
							alive_four[current_role + 1]++;
					weak_four[current_role + 1]++;
				} else if (temp_sum == 5) {
					alive_five[current_role + 1]++;
				}
			}
			temp_sum = 0;
			current_role = 0;
			for (int j = 0; i + j <= SIZE; j++) {
				if (Chessboard[j][i + j] != current_role) {
					current_role = Chessboard[j][i + j];
					temp_sum = 1;
				} else {
					if (current_role != 0)
						temp_sum++;
				}
				if (temp_sum == 2) {
					if (j - temp_sum >= 0 && i + j + 1 <= SIZE)
						if (Chessboard[j - temp_sum][i + j - temp_sum] == 0 && Chessboard[j + 1][i + j + 1] == 0)
							alive_two[current_role + 1]++;
				} else if (temp_sum == 3) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[j - temp_sum][i + j - temp_sum] == 0 && Chessboard[j + 1][i + j + 1] == 0)
							alive_three[current_role + 1]++;
					weak_three[current_role + 1]++;
				} else if (temp_sum == 4) {
					if (j - temp_sum >= 0 && j + 1 <= SIZE)
						if (Chessboard[j - temp_sum][i + j - temp_sum] == 0 && Chessboard[j + 1][i + j + 1] == 0)
							alive_four[current_role + 1]++;
					weak_four[current_role + 1]++;
				} else if (temp_sum == 5) {
					alive_five[current_role + 1]++;
				}
			}
			temp_sum = 0;
			current_role = 0;
			for (int j = 0; i - j >= 0; j++) {
				if (Chessboard[j][i - j] != current_role) {
					current_role = Chessboard[j][i - j];
					temp_sum = 1;
				} else {
					if (current_role != 0)
						temp_sum++;
				}
				if (temp_sum == 2) {
					if (j - temp_sum >= 0 && i - j - 1 >= 0)
						if (Chessboard[j - temp_sum][i - j + temp_sum] == 0 && Chessboard[j + 1][i - j - 1] == 0)
							alive_two[current_role + 1]++;
				} else if (temp_sum == 3) {
					if (j - temp_sum >= 0 && i - j - 1 >= 0)
						if (Chessboard[j - temp_sum][i - j + temp_sum] == 0 && Chessboard[j + 1][i - j - 1] == 0)
							alive_three[current_role + 1]++;
					weak_three[current_role + 1]++;
				} else if (temp_sum == 4) {
					if (j - temp_sum >= 0 && i - j - 1 >= 0)
						if (Chessboard[j - temp_sum][i - j + temp_sum] == 0 && Chessboard[j + 1][i - j - 1] == 0)
							alive_four[current_role + 1]++;
					weak_four[current_role + 1]++;
				} else if (temp_sum == 5) {
					alive_five[current_role + 1]++;
				}
			}
			temp_sum = 0;
			current_role = 0;
			for (int j = 0; i + j <= SIZE; j++) {
				if (Chessboard[i + j][SIZE - j] != current_role) {
					current_role = Chessboard[i + j][SIZE - j];
					temp_sum = 1;
				} else {
					if (current_role != 0)
						temp_sum++;
				}
				if (temp_sum == 2) {
					if (i + j + 1 <= SIZE && SIZE - j + temp_sum <= SIZE)
						if (Chessboard[i + j - temp_sum][SIZE - j + temp_sum] == 0 && Chessboard[i + j + 1][SIZE - j - 1] == 0)
							alive_two[current_role + 1]++;
				} else if (temp_sum == 3) {
					if (i + j + 1 <= SIZE && SIZE - j + temp_sum <= SIZE)
						if (Chessboard[i + j - temp_sum][SIZE - j + temp_sum] == 0 && Chessboard[i + j + 1][SIZE - j - 1] == 0)
							alive_three[current_role + 1]++;
					weak_three[current_role + 1]++;
				} else if (temp_sum == 4) {
					if (i + j + 1 <= SIZE && SIZE - j + temp_sum <= SIZE)
						if (Chessboard[i + j - temp_sum][SIZE - j + temp_sum] == 0 && Chessboard[i + j + 1][SIZE - j - 1] == 0)
							alive_four[current_role + 1]++;
					weak_four[current_role + 1]++;
				} else if (temp_sum == 5) {
					alive_five[current_role + 1]++;
				}
			}
		}
		
		//������aiΪ��
		
		/*
		 * �����10����8
		 * ������100
		 * ���ģ�1000
		 * ���ģ�30
		 * ���壺5000
		 * ����������Ƶķ������ֿ�����
		 * 
		 */
		evaluate_value = alive_two[0] * 10 - alive_two[2] * 8 + alive_three[0] * 100 - alive_three[2] * 100 + weak_three[0] * 20 - weak_three[2] * 20;
		evaluate_value += alive_four[0] * 1000 - alive_four[2] * 1500;
		evaluate_value += weak_four[0] * 30 - weak_four[2] * 30;
		evaluate_value += alive_five[0] * 5000 - alive_five[2] * 5000;
		return evaluate_value;
	}
}