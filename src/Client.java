
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

  public static final String HOST = "localhost";
  public static final int PORT = 5000;

  public static void main(String[] args) throws IOException {
    MySocket mysocket = new MySocket(HOST, PORT);
    new Thread() {
      public void run() {
        String liniaInput;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
          try {
            while ((liniaInput = in.readLine()) != null) {
              mysocket.printLine(liniaInput);
          }

          mysocket.close(); 
        }catch(IOException e) {
              System.out.println("No s'ha pogut enviar el missatge correctament.\n");
	             
        }
      }
    }.start();
    new Thread() {
      public void run() {
        String liniaOutput;
        while ((liniaOutput = mysocket.readLine()) != null) {  
          System.out.println(liniaOutput);
      	}
      }
    }.start();

  }
}