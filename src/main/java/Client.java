import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        // Đọc giá trị từ server chuyển về
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        // Đẩy dữ liệu từ client lên server
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        // In giá trị nhập vào từ bàn phím
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Đọc giá trị nhập vào từ bàn phím
            String str = scanner.nextLine();
            // Truyền dữ liệu lên server
            dataOutputStream.writeUTF(str);
            dataOutputStream.flush(); // Đẩy giá trị dữ liệu lên server
            if (str.equals("q")) {
                break;
            }
            // Đọc dữ liệu về từ server
            String str1 = dataInputStream.readUTF();
            System.out.println("Server said: " + str1);
        }
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }
}
