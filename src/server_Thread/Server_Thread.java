package server_Thread;

import java.io.*;
import java.lang.*;
import java.net.*;

public class Server_Thread extends Thread{

    private Socket socket;
    private static final String password = "123456789";
    private static final String file = "C:\\programing\\Java\\card\\14-crosses.jpg";

    public Server_Thread(Socket socket) {
        this.socket = socket;
        this.start();
    }

    @Override
    public void run() {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
