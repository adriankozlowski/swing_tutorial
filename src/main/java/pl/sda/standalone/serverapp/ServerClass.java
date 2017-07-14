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
            
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
