package convertnumbers.core;

import java.util.Scanner;

public class ConvertNumberTypeWithLinkedList {

	public static String convertNumber(String number, int fromType, int toType) {
		if (fromType == toType)
			return number;
		switch (toType) {
		case 10:
			return convertToDecimal(number, fromType);
		default:
			String s = convertToDecimal(number, fromType);
			return convertToBinary_Octal_Hexadecimal_Type(Integer.parseInt(s), toType);
		}

	}

	public static String convertToDecimal(String number, int fromType) {
		if (fromType == 10)
			return number;
		int x = 0;
		int j = 0;
		char[] arr = number.toCharArray();
		for (int i = arr.length - 1; i >= 0; i--) {
			if (fromType == 16) {
				if (arr[i] == 'A' || arr[i] == 'B' || arr[i] == 'C' || arr[i] == 'D' || arr[i] == 'E'
						|| arr[i] == 'F') {
					x += (int) Math.pow(fromType, j) * (arr[i] - '0' - 7);
				} else {
					x += (int) Math.pow(fromType, j) * (arr[i] - '0');
				}
			} else {
				x += (int) Math.pow(fromType, j) * (arr[i] - '0');
			}
			j++;
		}
		return String.valueOf(x);
	}

	public static String convertToBinary_Octal_Hexadecimal_Type(int number, int toType) {
		if(toType == 16 && number <10 && number >15) {
			if (number <toType)
				return String.valueOf(number);
		}

		LinkedList list = new LinkedList();
		while (number != 0) {
			switch (number % toType) {
			case 10:
				list.insertatHead("A");
				break;
			case 11:
				list.insertatHead("B");
				break;
			case 12:
				list.insertatHead("C");
				break;
			case 13:
				list.insertatHead("D");
				break;
			case 14:
				list.insertatHead("E");
				break;
			case 15:
				list.insertatHead("F");
				break;
			default:
				list.insertatHead(String.valueOf(number % toType));
				break;
			}

			number = number / toType;
			if (number >0 &&number < toType) {
				switch (number) {
				case 10:
					list.insertatHead("A");
					break;
				case 11:
					list.insertatHead("B");
					break;
				case 12:
					list.insertatHead("C");
					break;
				case 13:
					list.insertatHead("D");
					break;
				case 14:
					list.insertatHead("E");
					break;
				case 15:
					list.insertatHead("F");
					break;
				default:
					list.insertatHead(String.valueOf(number));
					break;
				}
				break;
			}

		}
		return list.print_out().toString();

	}

}
