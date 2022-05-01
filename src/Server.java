
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author entel
 */
public class Server {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Insert port number: ");
            String port = in.readLine();

            MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(port));            

            System.out.println("Server is listening...");
           

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
