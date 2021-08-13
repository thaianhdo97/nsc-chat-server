import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        // Tạo server cổng 8088
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        // Đọc dữ liệu từ client
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        // Đẩy dữ liệu từ server về client
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        // In giá trị nhập vào từ bàn phím
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // đọc dữ liệu từ client truyền lên
            String str = dataInputStream.readUTF();
            if (str.equals("q")) {
                break;
            }
            System.out.println("ClientIP said: " + str);
            // Truyền dữ liệu từ server về
            String str1 = scanner.nextLine();
            dataOutputStream.writeUTF(str1);
            dataOutputStream.flush();
        }
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
        serverSocket.close(); // đóng kết nối server
    }
}
