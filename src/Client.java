
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author entel
 */
public class Client {
    public static MySocket mySocket;
    public static String nick;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Insert host: ");
        String host = in.readLine();
        System.out.print("Insert the port number: ");
        String port = in.readLine();
       
        mySocket = new MySocket(host, Integer.parseInt(port));

        
    }

}
