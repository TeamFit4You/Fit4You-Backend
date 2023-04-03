package Fit4You.Fit4YouBackend.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientExample {
    public static void main(String[] args) throws IOException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Socket socket = new Socket(host.getHostAddress(), 10000);
        BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        PrintStream out = new PrintStream(socket.getOutputStream());

        out.println("Start Training");
        String temp;
        System.out.println(">>>"+in.readLine());
        while(true){
            System.out.print("입력해주세요 : ");
            String data = br.readLine();
            out.println(data);//"End Training" 입력시 통신종료
            if(data.equals("End Training")){
                break;
            }
        }
        out.close();
        br.close();
        socket.close();
    }
}
