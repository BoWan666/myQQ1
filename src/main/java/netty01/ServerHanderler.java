package netty01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ServerHanderler implements Callable {
    private Socket socket;

    public ServerHanderler(Socket socket) {
        this.socket=socket;
    }

    public Boolean call() throws IOException {
        BufferedReader reader=null;
        PrintWriter writer=null;

        //循环读取
            try {
                String resultinfo="";
                writer=new PrintWriter(socket.getOutputStream(),true);
                while(true){
                    while(true) {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        if ((resultinfo = reader.readLine()) != null) {
                            break;
                        }
                    }

                    if("exit".equals(resultinfo)){
                        System.out.print("客户端"+socket.getPort()+"断开连接" );
                        writer.println("exit");

                        reader.close();
                        writer.close();
                        socket.close();
                        System.out.println("连接关闭成功！");
                        System.out.println("###############################################################################");
                        return false;
                    }
                    System.out.println("服务端收到"+socket.getPort()+"端口消息：" +resultinfo);
                    String backinfo = cal();
                    writer.println(backinfo);
                    System.out.println("消息回复成功！");
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
                if (socket!=null){
                    socket.close();
                }
            }

            return true;
    }

    public String cal(){
        System.out.println("请回复：");
        Scanner scanner=new Scanner(System.in);
        String info=scanner.next();
        return info;
    }
}
