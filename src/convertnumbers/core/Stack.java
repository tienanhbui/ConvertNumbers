package convertnumbers.core;



public class Stack {

	public static final int CAPACITY = 1000; // size mặc định cho stack
	public int capacity; // size hiện tại được khởi tạo
	public Object[] obj; // kiểu dữ liệu của stack
	private int top = -1; // khởi tạo vị trí đầu của stack.

	public Stack() {
		// nếu khởi tạo mặc định stack sẽ có size mặc định là CAPACITY
		this(CAPACITY);
	}

	public Stack(int CAP) {
		capacity = CAP;
		obj = new Object[capacity];
	}

	// trả về size hiện tại stack đã chứa
	public int findSize() {
		return (top + 1);
	}

	//trả về phần tử đầu tiên của stack
	public Object top() {
		return obj[top];
	}

	// kiểm tra stack rỗng ?
	public boolean isEmpty() {
		return (top < 0);
	}

	// kiểm tra stack đầy
	public boolean isFull() {
		return (findSize() == capacity);
	}

	// tạo phương thức thêm phần tử vào stack
	public void push(Object o) {
		if (isFull()) {
			System.out.println("stack đầy");
			return;
		}
		//gán giá trị tiếp theo vào mảng obj
		obj[++top] = o;
	}

	public Object pop() {
		Object x;
		if (isEmpty()) {
			System.out.println("stack rỗng");
			return null;
		}

		//lấy phần tử đầu của stack
		x = obj[top];
		//giảm index phần tử đầu của stack sau khi lấy ra
		top--;
		return x;
	}
}
