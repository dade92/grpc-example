package com.example.grpc;

import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(GetUserRequest request, StreamObserver<User> responseObserver) {
        User user = User.newBuilder()
            .setId(request.getId())
            .setName("Ciccio " + request.getId())
            .build();

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }
}
