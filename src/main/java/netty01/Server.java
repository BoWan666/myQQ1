package netty01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private static int DEFALUT_PORT1=1234;
    private static int DEFALUT_PORT2=1235;
    private static int DEFALUT_PORT3=1236;

    public static void main(String[] args) throws IOException {
        Socket socket=null;
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            ServerSocket serverSocket=new ServerSocket(DEFALUT_PORT1);
            System.out.println("1234启动成功");
            while(true){
                socket=serverSocket.accept();
                Future<Boolean> future=executorService.submit(new ServerHanderler(socket));
                boolean isSuccess=future.get();
                if(isSuccess) {
                    System.out.print("消息回复成功");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                socket.close();
            }
        }

    }
}
