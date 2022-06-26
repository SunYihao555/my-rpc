package com.learn.remoting.transport.socket;

import com.learn.remoting.dto.RpcRequest;
import com.learn.remoting.dto.RpcResponse;
import com.learn.remoting.handler.RequestHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketRpcRequestHandlerRunnable implements Runnable{
    private final Socket socket;
    public SocketRpcRequestHandlerRunnable(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始处理");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            RpcRequest request = (RpcRequest) objectInputStream.readObject();
            System.out.println(request.getRequestId());
            RpcResponse handle = RequestHandler.handle(request);
            objectOutputStream.writeObject(handle);
            objectOutputStream.flush();
            System.out.println("处理完毕");
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
