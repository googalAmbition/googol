package com.tcoding.demo.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class Provider {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(10086)
            .addService(new HelloGrpcImpl())
            .build()
            .start();
        if (server != null) {
            server.awaitTermination();
        }
    }
}


