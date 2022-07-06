package stage09;

import java.util.Scanner;

public class baekjoon17478 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		repeat(num);
	}
	
	static void repeat(int n) {
		repeat_1(n-1);
		for(int i = 0; i < n*4; i++) System.out.print("_");
		System.out.println("\"����Լ��� ������?\"");
		for(int i = 0; i < n*4; i++) System.out.print("_");
		System.out.println("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
		repeat_2(n);
	}
	
	static void repeat_1(int n) {
		if(n == 0) {
			System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
			
			System.out.println("\"����Լ��� ������?\"");
			System.out.println("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
			System.out.println("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
			System.out.println("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
		}
		else {
			repeat_1(n-1);
			
			for(int i = 0; i < n*4; i++) System.out.print("_");
			System.out.println("\"����Լ��� ������?\"");
			for(int i = 0; i < n*4; i++) System.out.print("_");
			System.out.println("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
			for(int i = 0; i < n*4; i++) System.out.print("_");
			System.out.println("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
			for(int i = 0; i < n*4; i++) System.out.print("_");
			System.out.println("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
		}
		
	}
	static void repeat_2(int n) {
		if(n == 0) {
			System.out.println("��� �亯�Ͽ���.");
		}
		else {
			for(int i = 0; i < n*4; i++) System.out.print("_");
			System.out.println("��� �亯�Ͽ���.");
			
			repeat_2(n-1);
		}
	}

}
