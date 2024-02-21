package com.thesis.bankingservice.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.0)",
    comments = "Source: banking.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BankingGrpc {

  private BankingGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.thesis.bankingservice.generated.Banking";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.thesis.bankingservice.generated.payRequest,
      com.thesis.bankingservice.generated.payResponse> getPaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "payment",
      requestType = com.thesis.bankingservice.generated.payRequest.class,
      responseType = com.thesis.bankingservice.generated.payResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.thesis.bankingservice.generated.payRequest,
      com.thesis.bankingservice.generated.payResponse> getPaymentMethod() {
    io.grpc.MethodDescriptor<com.thesis.bankingservice.generated.payRequest, com.thesis.bankingservice.generated.payResponse> getPaymentMethod;
    if ((getPaymentMethod = BankingGrpc.getPaymentMethod) == null) {
      synchronized (BankingGrpc.class) {
        if ((getPaymentMethod = BankingGrpc.getPaymentMethod) == null) {
          BankingGrpc.getPaymentMethod = getPaymentMethod =
              io.grpc.MethodDescriptor.<com.thesis.bankingservice.generated.payRequest, com.thesis.bankingservice.generated.payResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "payment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.thesis.bankingservice.generated.payRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.thesis.bankingservice.generated.payResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BankingMethodDescriptorSupplier("payment"))
              .build();
        }
      }
    }
    return getPaymentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BankingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BankingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BankingStub>() {
        @java.lang.Override
        public BankingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BankingStub(channel, callOptions);
        }
      };
    return BankingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BankingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BankingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BankingBlockingStub>() {
        @java.lang.Override
        public BankingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BankingBlockingStub(channel, callOptions);
        }
      };
    return BankingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BankingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BankingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BankingFutureStub>() {
        @java.lang.Override
        public BankingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BankingFutureStub(channel, callOptions);
        }
      };
    return BankingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void payment(com.thesis.bankingservice.generated.payRequest request,
        io.grpc.stub.StreamObserver<com.thesis.bankingservice.generated.payResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPaymentMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Banking.
   */
  public static abstract class BankingImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BankingGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Banking.
   */
  public static final class BankingStub
      extends io.grpc.stub.AbstractAsyncStub<BankingStub> {
    private BankingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BankingStub(channel, callOptions);
    }

    /**
     */
    public void payment(com.thesis.bankingservice.generated.payRequest request,
        io.grpc.stub.StreamObserver<com.thesis.bankingservice.generated.payResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPaymentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Banking.
   */
  public static final class BankingBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BankingBlockingStub> {
    private BankingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BankingBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.thesis.bankingservice.generated.payResponse payment(com.thesis.bankingservice.generated.payRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPaymentMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Banking.
   */
  public static final class BankingFutureStub
      extends io.grpc.stub.AbstractFutureStub<BankingFutureStub> {
    private BankingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BankingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.thesis.bankingservice.generated.payResponse> payment(
        com.thesis.bankingservice.generated.payRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPaymentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAYMENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PAYMENT:
          serviceImpl.payment((com.thesis.bankingservice.generated.payRequest) request,
              (io.grpc.stub.StreamObserver<com.thesis.bankingservice.generated.payResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPaymentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.thesis.bankingservice.generated.payRequest,
              com.thesis.bankingservice.generated.payResponse>(
                service, METHODID_PAYMENT)))
        .build();
  }

  private static abstract class BankingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BankingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.thesis.bankingservice.generated.BankingOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Banking");
    }
  }

  private static final class BankingFileDescriptorSupplier
      extends BankingBaseDescriptorSupplier {
    BankingFileDescriptorSupplier() {}
  }

  private static final class BankingMethodDescriptorSupplier
      extends BankingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BankingMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BankingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BankingFileDescriptorSupplier())
              .addMethod(getPaymentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
