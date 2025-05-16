package com.example.grpc.client;

import com.example.grpc.GetUserRequest;
import com.example.grpc.User;
import com.example.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClientApp {

    public static void main(String[] args) {
        // Connect to the gRPC server
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext() // No TLS
            .build();

        // Create a blocking stub
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        // Build the request
        GetUserRequest request = GetUserRequest.newBuilder().setId(1).build();

        // Call the service
        User user = stub.getUser(request);

        System.out.println("Received User: ID = " + user.getId() + ", Name = " + user.getName());

        // Shutdown the channel
        channel.shutdown();
    }
}
