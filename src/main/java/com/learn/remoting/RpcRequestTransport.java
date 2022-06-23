package com.learn.remoting;

import com.learn.remoting.dto.RpcRequest;

public interface RpcRequestTransport {
    Object sendRpcRequest(RpcRequest request);
}
