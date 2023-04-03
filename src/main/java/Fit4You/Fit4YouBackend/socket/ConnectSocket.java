package Fit4You.Fit4YouBackend.socket;

import Fit4You.Fit4YouBackend.exception.InvalidSignInInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConnectSocket implements Runnable{

    private Socket socket;

    public ConnectSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            System.out.println(">>>new Socket Connection Created");
            // Local reader from the client
            BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
            PrintStream out = new PrintStream(socket.getOutputStream());
            /**
             * "String Training" 으로 시작 구분
             * "End Training" 으로 시작 구분
             */
            String req = in.readLine();
            if(!req.equals("Start Training"))
            {
                throw new InvalidSignInInfo();//TODO 임시에러 수정
            }
            out.println("Start");
            String data;
            List<String> dataList = new ArrayList<>();
            while (true) {
                data = in.readLine();
                if (data.equals("End Training")) {
                    System.out.println(data);
                    out.println("End");
                    break;
                }
                System.out.println("From Client>>>"+data);
                dataList.add(data);
            }
            in.close();
            out.close();
            socket.close();
            //메인 서버와 연결
            System.out.println(">>>Close Socket Connection");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void get(String address, String[] headers) throws Exception {
//        //헤더값은 이런식으로 사용합니다(키, 값)headers => "Content-type","plain/text"
//        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
//        String result = client.sendAsync(
//                        HttpRequest.newBuilder(
//                                new URI(address)).GET().headers(headers).build(),  //GET방식 요청
//                        HttpResponse.BodyHandlers.ofString()  //응답은 문자형태
//                ).thenApply(HttpResponse::body)  //thenApply메소드로 응답body값만 받기
//                .get();  //get메소드로 body값의 문자를 확인
//        System.out.println(result);
//    }
//
//    public void post(List<String> data) throws Exception {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(params);
//
//        BodyPublisher body = HttpRequest.BodyPublishers.ofString(requestBody);  //post 파라미터 최종 모습 입니다.
//
//        String uri = "http://localhost:8080/workouts/record";
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(uri))
//                .timeout(Duration.ofSeconds(30))
//                .header("Content-Type", "application/json")
////                .header("Authorization", token)
//                .POST(HttpRequest.BodyPublishers.ofString(data))
//                .build();
//
//        HttpClient client = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .connectTimeout(Duration.ofMillis(1000*10))
//                .build();
//
//        String result = client
//                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
////                .thenApply(HttpResponse::statusCode)
//                .thenApply(HttpResponse::body)
//                .get();
//        System.out.println(result);
//    }

}
