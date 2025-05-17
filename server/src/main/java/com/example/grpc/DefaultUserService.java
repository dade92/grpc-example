package com.example.grpc;

import io.grpc.stub.StreamObserver;

import static io.grpc.Status.INTERNAL;
import static io.grpc.Status.INVALID_ARGUMENT;

public class DefaultUserService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(UserRequest request, StreamObserver<User> responseObserver) {
        try {
            if (request.getId() < 0) {
                throw new IllegalArgumentException("Invalid user ID: must be positive");
            }
            User user = User.newBuilder()
                .setId(request.getId())
                .setName("Ciccio " + request.getId())
                .build();

            responseObserver.onNext(user);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            responseObserver.onError(
                INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                INTERNAL
                    .withDescription("Unexpected error occurred")
                    .withCause(e)
                    .asRuntimeException()
            );
        }
    }
}
