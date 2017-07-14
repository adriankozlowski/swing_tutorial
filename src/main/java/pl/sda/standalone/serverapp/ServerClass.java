/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.standalone.serverapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class ServerClass {
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(10000);
            Socket accept = server.accept();
            MyThread thread = new MyThread(accept);
            thread.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

class MyThread extends Thread {
    
    private Socket client;
    
    public MyThread(Socket socket) {
        this.client = socket;
    }
    
    @Override
    public void run() {
        try {
            OutputStream outputStream = this.client.getOutputStream();
            outputStream.write("Połaczony".getBytes());
            InputStream inputStream = this.client.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = null;
            int state = 1;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(new String(s.getBytes("UTF-8")));
                if (state == 1 && s.equalsIgnoreCase("HI")) {
                    outputStream.write("HI\r".getBytes());
                    outputStream.write("HI\r".getBytes());
                    state++;
                } else if (state == 2 && s.equalsIgnoreCase("SEND")) {
                    outputStream.write("OK\r".getBytes());
                    state++;
                } else if (state == 3 && s.startsWith("SIZE:")) {
                    String[] split = s.split(":");
                    String sendPart = split[0];
                    String sizePart = split[1];
                    try {
                        Integer integer = Integer.valueOf(sizePart);
                    } catch (NumberFormatException e) {
                        outputStream.write("NO\r".getBytes());
                    }
                    outputStream.write("OK\r".getBytes());
                    state++;
                } else if (state == 4 && s.equalsIgnoreCase("")) {
                    System.out.println("koniec połączenia");
                }
            }
            bufferedReader.close();
            outputStream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
