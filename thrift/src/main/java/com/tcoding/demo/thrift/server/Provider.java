package com.tcoding.demo.thrift.server;

import com.tcoding.demo.thrift.DemoService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class Provider {

    public static void main(String[] args) {
        Provider server = new Provider();
        server.startServer();
    }

    private void startServer() {
        DemoService.Processor<DemoService.Iface> processor = new DemoService.Processor<>(new DemoServiceImpl());
        try {
            TServerTransport transport = new TServerSocket(2345);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TTransportFactory());
            tArgs.minWorkerThreads(10);
            tArgs.maxWorkerThreads(20);
            TServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

