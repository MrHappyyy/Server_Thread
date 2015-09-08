package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final int PORT = 1234;
    private static final String password = "123456789";
    private static final String file = "C:\\programing\\Java\\card\\14-crosses.jpg";
    private  static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        while (true) {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("run: " + server);

            try {
                Socket socket = server.accept();

                try {
                    System.out.println("Open connection: " + socket);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    while (true) {
                        String str = in.readLine();

                        if (str.equals(password)) {
                            String next = "Correct password";
                            out.println(next);

                            BufferedInputStream bufferedInputStrem = new BufferedInputStream(new FileInputStream(file));
                            DataOutputStream dataOuputStrem = new DataOutputStream(socket.getOutputStream());
                            byte[] byt = new byte[bufferedInputStrem.available()];
                            int len = byt.length;
                            System.out.println("len = " + len);
                            bufferedInputStrem.read(byt, 0, byt.length);
                            dataOuputStrem.write(byt);
                            dataOuputStrem.flush();
                            break;

                        } else {
                            String next = "Wrong password";
                            out.println(next);
                        }
                    }
                } finally {
                    System.out.println("clossing...");
                    socket.close();
                }
            } finally {
                server.close();
            }
        }
    }
}
