package convertnumbers.core;
import java.util.Random;

public class LinkedList {
	Node pHead; // Head là phần tử đầu tiên của danh sách liên kết
	Node pTail; // Tail là phần tử cuối của danh sách liên kết

	public void insertatTail(String data) {
		if (pHead == null) {
			pHead = new Node();
			pHead.data = data;
			pTail =pHead;
		} else {
			Node newnode = new Node();
			newnode.data = data;
			pTail.nextNode = newnode;
			pTail = newnode;
		}
	}

	public void insertatHead(String data) {
		if (pHead == null) {
			pHead = new Node();
			pHead.data = data;
			pTail =pHead;
		} else {
			Node newnode = new Node();
			newnode.data = data;
			newnode.nextNode =pHead;
			pHead = newnode;
		}
	}

	public StringBuilder print_out() {
		StringBuilder stringBuilder = new StringBuilder();
		Node pTemp = pHead;
		while (pTemp != null) {
			stringBuilder.append(pTemp.data);
			pTemp = pTemp.nextNode;
		}
		return stringBuilder;
	}
	
	public static void main(String[] args) {
		LinkedList lst = new LinkedList();
		Random rd = new Random();
		for (int i = 0; i < 9999999; i++) {
			lst.insertatHead(rd.nextInt(999999999)+"\n");
		}
		lst.print_out();
		
				
	}

}
