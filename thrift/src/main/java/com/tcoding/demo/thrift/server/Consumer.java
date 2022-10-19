package com.tcoding.demo.thrift.server;

import com.tcoding.demo.thrift.DemoService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class Consumer {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 2345;//Thrift server listening port
    private static final int TIMEOUT = 3000;

    private void startClient(String userName) {
        try (TTransport transport = new TSocket(SERVER_IP, SERVER_PORT)) {
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            DemoService.Client client = new DemoService.Client(protocol);
            transport.open();
            long start = System.currentTimeMillis();
            System.out.println(client.getName(1));
            System.out.println(System.currentTimeMillis() - start);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Consumer client = new Consumer();
        client.startClient("Tom");
    }
}

