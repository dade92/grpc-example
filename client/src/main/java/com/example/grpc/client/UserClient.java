package com.example.grpc.client;

import com.example.grpc.User;
import com.example.grpc.UserRequest;
import com.example.grpc.UserServiceGrpc;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;

public class UserClient {

    private final Channel channel;

    public UserClient(Channel channel) {
        this.channel = channel;
    }

    public User retrieve(int id) {
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        UserRequest request = UserRequest.newBuilder().setId(id).build();

        try {
            return stub.getUser(request);
        } catch (StatusRuntimeException e) {
            switch (e.getStatus().getCode()) {
                case INVALID_ARGUMENT:
                    System.err.println("Invalid input: " + e.getStatus().getDescription());
                    break;
                case NOT_FOUND:
                    System.err.println("User not found.");
                    break;
                case INTERNAL:
                    System.err.println("Internal server error: " + e.getStatus().getDescription());
                    break;
                default:
                    System.err.println("Unhandled gRPC error: " + e.getStatus());
            }

            throw new UserRetrieveException("Error retrieving user with id " + id, e);
        }
    }

}
