package Fit4You.Fit4YouBackend.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EmbeddedSocketServer implements Runnable{


    int port = 10000;
    int queueLen = 100;//큐에 쌓일수 있는 처리를 기다리는 요청의 갯수
    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(port, queueLen);
            System.out.println(">>>Socket Server is starting up, listening at port " + port + ".");
            System.out.println(">>>You can access http://localhost:"+port+" now.");


            while(true){
                // Make the server socket wait for the next client request
                Socket socket = serverSocket.accept();
                //새 쓰레드로 연결 실행
                new Thread(new ConnectSocket(socket)).start();
            }
        } catch(Exception e){
            //Handle the exception
            e.printStackTrace();}

        finally {
            System.out.println(">>>Server has been shutdown!");
        }
    }
}
