package com.learn.remoting.transport.socket;

import com.learn.remoting.RpcRequestTransport;
import com.learn.remoting.dto.RpcRequest;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketRpcServer implements RpcRequestTransport {
    private ExecutorService threadPool;
    public SocketRpcServer(){

    }



    @Override
    public Object sendRpcRequest(RpcRequest request) {
        return null;
    }

    public void start(int port){
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(port));
            Socket socket;
            while((socket = serverSocket.accept())!=null){
                threadPool.execute();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
