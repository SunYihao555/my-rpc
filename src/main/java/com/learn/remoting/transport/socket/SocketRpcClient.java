package com.learn.remoting.transport.socket;

import com.learn.remoting.dto.RpcRequest;
import com.learn.remoting.dto.RpcResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketRpcClient {
    public static void main(String[] args) {
        RpcRequest request = new RpcRequest();
        request.setRequestId("测试请求");
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcResponse o = (RpcResponse)objectInputStream.readObject();
            System.out.println(o.getDate());



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
