package convertnumbers.core;

import convertnumbers.core.LinkedList.*;
import java.util.Random;

public class InitData {
	public static final String FILE_NAME = "data.dat";

	public void insertData() {
		StringBuilder data = new StringBuilder();
		Random rd = new Random();
		for (int i = 0; i < 999999; i++) {
			data.append(rd.nextInt(100000000) + " ");
		}
		ReadWriteFile.Write(FILE_NAME, data);
	}

	public static String getData() {
		return ReadWriteFile.Read(FILE_NAME);
	}

	public static void main(String[] args) {
		InitData init = new InitData();
		init.insertData();
		String data = init.getData();
		//System.out.println(data);
		// System.out.println(data.length());
		String s[] = data.split(" ");
		long startTime = System.currentTimeMillis();
		for (String num : s) {
			//System.out.println(ConvertNumberTypeWithLinkedList.convertNumber(num, 10, 2)); 
		}
		long finishTime = System.currentTimeMillis();
		long runtime = finishTime - startTime;
		System.out.println("Run time : " + runtime + "ms");
//		System.out.println(s.length);
	}
}
