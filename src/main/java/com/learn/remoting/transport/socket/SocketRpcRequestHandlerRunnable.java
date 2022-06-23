package com.learn.remoting.transport.socket;

import java.net.Socket;

public class SocketRpcRequestHandlerRunnable implements Runnable{
    private final Socket socket;
    public SocketRpcRequestHandlerRunnable(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {


    }
}
