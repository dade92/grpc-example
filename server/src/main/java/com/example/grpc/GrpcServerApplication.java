package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcServerApplication implements CommandLineRunner {

    private Server server;

    public static void main(String[] args) {
        SpringApplication.run(GrpcServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        server = ServerBuilder.forPort(9090)
            .addService(new DefaultUserService())
            .build()
            .start();

        System.out.println("gRPC Server started on port 9090");

        // Run awaitTermination() in a separate thread so it doesn't block Spring Boot startup
        new Thread(() -> {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @PreDestroy
    public void onDestroy() {
        if (server != null) {
            System.out.println("Shutting down gRPC server...");
            server.shutdown();
        }
    }
}
