// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bankPractice.proto

// Protobuf Java Version: 3.25.1
package com.thesis.bankingservice.generated;

public final class BankPractice {
  private BankPractice() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_thesis_bankingservice_generated_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_thesis_bankingservice_generated_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_thesis_bankingservice_generated_Practice_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_thesis_bankingservice_generated_Practice_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_thesis_bankingservice_generated_PracticeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_thesis_bankingservice_generated_PracticeResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022bankPractice.proto\022#com.thesis.banking" +
      "service.generated\"n\n\007Request\022C\n\006action\030\001" +
      " \001(\01623.com.thesis.bankingservice.generat" +
      "ed.Request.Action\"\036\n\006Action\022\n\n\006CREATE\020\000\022" +
      "\010\n\004FILL\020\001\"k\n\010Practice\022\022\n\npracticeId\030\001 \001(" +
      "\t\022\016\n\006status\030\002 \001(\t\022\014\n\004name\030\003 \001(\t\022\017\n\007surna" +
      "me\030\004 \001(\t\022\r\n\005email\030\005 \001(\t\022\r\n\005phone\030\006 \001(\t\"5" +
      "\n\020PracticeResponse\022\016\n\006result\030\001 \001(\t\022\021\n\tre" +
      "questId\030\002 \001(\t2x\n\007Banking\022m\n\016CreatePracti" +
      "ce\022,.com.thesis.bankingservice.generated" +
      ".Request\032-.com.thesis.bankingservice.gen" +
      "erated.PracticeB\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_thesis_bankingservice_generated_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_thesis_bankingservice_generated_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_thesis_bankingservice_generated_Request_descriptor,
        new java.lang.String[] { "Action", });
    internal_static_com_thesis_bankingservice_generated_Practice_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_thesis_bankingservice_generated_Practice_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_thesis_bankingservice_generated_Practice_descriptor,
        new java.lang.String[] { "PracticeId", "Status", "Name", "Surname", "Email", "Phone", });
    internal_static_com_thesis_bankingservice_generated_PracticeResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_thesis_bankingservice_generated_PracticeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_thesis_bankingservice_generated_PracticeResponse_descriptor,
        new java.lang.String[] { "Result", "RequestId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
