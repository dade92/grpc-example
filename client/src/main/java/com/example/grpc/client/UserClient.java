package com.example.grpc.client;

import com.example.grpc.GetUserRequest;
import com.example.grpc.User;
import com.example.grpc.UserServiceGrpc;
import io.grpc.Channel;

public class UserClient {

    private final Channel channel;

    public UserClient(Channel channel) {
        this.channel = channel;
    }

    public User retrieve(int id) {
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        GetUserRequest request = GetUserRequest.newBuilder().setId(id).build();

        return stub.getUser(request);
    }

}
