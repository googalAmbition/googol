package com.tcoding.demo.grpc;

import com.tcoding.demo.grpc.server.HelloReply;
import com.tcoding.demo.grpc.server.HelloRequest;
import com.tcoding.demo.grpc.server.SimpleGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class HelloGrpcImpl extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("hello " + request.getName()).build();
        // 调用onNext()方法来通知gRPC框架把reply 从server端 发送回 client端
        responseObserver.onNext(reply);
        // 表示完成调用
        responseObserver.onCompleted();
    }
}

