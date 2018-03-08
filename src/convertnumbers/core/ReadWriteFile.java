package convertnumbers.core;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class ReadWriteFile {
	public static void Write(String fileName, StringBuilder string) {
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName, false)));
			objectOutputStream.writeObject(string);
		} catch (IOException ex) {
			System.out.print("Lỗi : " + ex);
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException ex) {
				System.out.print("Lỗi : " + ex);
			}
		}
	}

	public static String Read(String fileName) {
		ObjectInputStream objectInputStream = null;
		StringBuilder str = new StringBuilder();
		try {
			objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			Object obj = objectInputStream.readObject();
			str = (StringBuilder) obj;

		} catch (Exception ex) {
                    System.out.print("Lỗi : "+ex);
		} finally {
			try {
				objectInputStream.close();
			} catch (Exception ex) {
				 JOptionPane.showMessageDialog(null, "File không hợp lệ!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
			}
		}
		return str.toString();
	}
}
