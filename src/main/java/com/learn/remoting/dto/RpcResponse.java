package com.learn.remoting.dto;

import com.learn.common.enums.RpcCommonEnum;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RpcResponse<T> implements Serializable {
    private static final long serialVersionUID = 6725367099941141738L;
    private String requestId;
    private Integer code;
    private String message;
    private T date;
    public static <T> RpcResponse<T> success(T data,String requestId){
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(RpcCommonEnum.SUCCESS.getCode());
        response.setMessage(RpcCommonEnum.SUCCESS.getMessage());
        response.setRequestId(requestId);
        if(null!=data){
            response.setDate(data);
        }
        return response;
    }
    public static <T> RpcResponse<T> fail(RpcCommonEnum rpcCommonEnum){
        RpcResponse<T> response = new RpcResponse<>();
        response.setMessage(rpcCommonEnum.getMessage());
        response.setCode(rpcCommonEnum.getCode());
        return response;

    }

}
