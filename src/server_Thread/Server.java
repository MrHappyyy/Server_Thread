package server_Thread;

import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.Scanner;

public class Server {

    public boolean stop = false;
    public final int PORT = 8800;
    private Scanner scanner = new Scanner(System.in);

    public void serverStart() {
        try {
            ServerSocket server = new ServerSocket(PORT);

            while (!stop) {
                Socket socket = server.accept();
                new Server_Thread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.serverStart();
    }
}
