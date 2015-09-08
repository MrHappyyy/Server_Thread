package server_Thread;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    private static String password;
    private static Scanner scn = new Scanner(System.in);
    private static final String fileName = "C:\\Users\\vyur4\\Desktop\\12.jpg";

    public static void main(String[] args) throws IOException {

        InetAddress add = InetAddress.getByName(null);
        System.out.println("add: " + add);
        Socket socket = new Socket(add, 8800);
        System.out.println("Socket: " + socket);

        try {

            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                System.out.println("Enter password");
                password = scn.next();
                pout.println(password);
                String str = in.readLine();

                if (password.equals("close")){
                    break;
                }

                if (str.equals("Correct password")) {
                    System.out.println(str);
                    File file = new File(fileName);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    byte[] byt = new byte[76635];
                    dataInputStream.read(byt);
                    bufferedOutputStream.write(byt);
                    bufferedOutputStream.flush();
                    break;
                }else{
                    System.out.println(str);
                }
            }
        }finally {
            System.out.println("Clossing...");
            socket.close();
        }
    }
}
