package com.learn.common.enums;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public enum RpcCommonEnum {
    SUCCESS(200,"RPC call is success"),
    FAIL(400,"RPC call is fail");
    private final int code;
    private final String message;


}
