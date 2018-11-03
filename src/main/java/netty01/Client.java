package netty01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client {
    private static int DEFALUT_PORT=1234;
    private static Socket socket=null;

    public static void send(int port){
        BufferedReader reader=null;
        PrintWriter writer=null;
        try {
            socket=new Socket("127.0.0.1",port);
            System.out.println("启动成功！");
            Scanner scanner=new Scanner(System.in);
            while(true){
                System.out.println("请输入您要发送的消息：");
                String sendMsg=scanner.next();
                //发送信息
                writer=new PrintWriter(socket.getOutputStream(),true);
                writer.println(sendMsg);
                System.out.println("消息发送成功！");

                String returninfo="";
                while(true) {
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    if ((returninfo = reader.readLine()) != null) {
                        break;
                    }
                }
                System.out.println("收到消息："+returninfo);
                if("exit".equals(returninfo)){
                    reader.close();
                    writer.close();
                    socket.close();
                    System.out.println("连接关闭成功！");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer!=null){
                writer.close();
            }
        }

    }
}
