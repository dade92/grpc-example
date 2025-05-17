package com.example.grpc.client;

import com.example.grpc.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClientApp {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext() // No TLS
            .build();

        UserClient client = new UserClient(channel);

        User user = client.retrieve(-1);

        System.out.println("Received User: ID = " + user.getId() + ", Name = " + user.getName());

        // Shutdown the channel
        channel.shutdown();
    }
}
