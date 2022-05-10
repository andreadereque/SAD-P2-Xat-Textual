

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
public class Server implements Runnable {

  public static ConcurrentHashMap<String, MySocket> llista = new ConcurrentHashMap<>();  
  MySocket mysck;
  String nickname;
  public static boolean next = false; 

  
  public Server(String nickname, MySocket mysck) throws IOException {
    this.mysck = mysck;
    this.nickname = nickname;
  }

  public static void main(String[] args) throws Exception {
    MyServerSocket serversocket = new MyServerSocket(5000);	
    System.out.println("Servidor inicialitzat. Esperant als usuaris...");

    while(true){
      MySocket mysocket = serversocket.accept();
      while (!next) { 
        mysocket.printLine("Introdueixi el seu nou nom d'usuari: ");
        String nickname = mysocket.readLine();  
 
        if (llista.containsKey(nickname)) {   
          mysocket.printLine("El nom d'usuari " + nickname + " ja esta enregistrat.");
          
        }else {
          System.out.println(nickname + " esta en linia");
	         llista.put(nickname, mysocket);
          new Thread(new Server(nickname, mysocket)).start(); 
          next = true; 
        }
      }
      next = false; 
    }
  }

  @Override
  public void run() {
      String linia;

      while ((linia = mysck.readLine()) != null) {
          boolean actiu = actiu(nickname, linia); 
          if (actiu) {    
              for (MySocket ms : llista.values()) {
                  if (ms != mysck) {
                      ms.printLine(nickname + ": " + linia);
                      System.out.println(nickname + " ha escrit: " + linia);
                  }
              }
          }
      }
  }

  public boolean actiu(String nickname, String linia) {     
      boolean actiu = true;
      if (linia.equals("Desconnectar-se")) {    //Si es posa Marxo es desconecta
          llista.remove(nickname); 

          for (MySocket ms : llista.values()) {
              if (ms != mysck) {                    
		              ms.printLine(nickname + ": Desconnectar-se");
                  ms.printLine(nickname + ": : ha abandonat la conversa.");
                  System.out.println(nickname + " ha escrit: Desconnectar-se");
                  System.out.println(nickname + " es troba desconnectat.");
              }

          }
          actiu = false;   
      }
      return actiu; 
  }



}
           