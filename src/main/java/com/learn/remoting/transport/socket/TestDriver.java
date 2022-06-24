package com.learn.remoting.transport.socket;

public class TestDriver {
    public static void main(String[] args) {
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        socketRpcServer.start(8080);
    }
}
