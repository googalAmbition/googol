package com.tcoding.demo.grpc;

import com.tcoding.demo.grpc.server.HelloReply;
import com.tcoding.demo.grpc.server.HelloRequest;
import com.tcoding.demo.grpc.server.SimpleGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class Consumer {

    public static void main(String[] args) {
        HelloRequest request = HelloRequest.newBuilder().setName("grpc").build();
        Channel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 10086).usePlaintext().build();
        SimpleGrpc.SimpleBlockingStub blockingStub = SimpleGrpc.newBlockingStub(channel);
        HelloReply reply = blockingStub.sayHello(request);
        System.out.println(reply.getMessage());
    }
}

