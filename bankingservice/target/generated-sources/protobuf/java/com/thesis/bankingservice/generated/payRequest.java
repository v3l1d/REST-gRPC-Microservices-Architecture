// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: banking.proto

// Protobuf Java Version: 3.25.1
package com.thesis.bankingservice.generated;

/**
 * Protobuf type {@code com.thesis.bankingservice.generated.payRequest}
 */
public final class payRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.thesis.bankingservice.generated.payRequest)
    payRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use payRequest.newBuilder() to construct.
  private payRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private payRequest() {
    committent_ = "";
    id_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new payRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.thesis.bankingservice.generated.BankingOuterClass.internal_static_com_thesis_bankingservice_generated_payRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.thesis.bankingservice.generated.BankingOuterClass.internal_static_com_thesis_bankingservice_generated_payRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.thesis.bankingservice.generated.payRequest.class, com.thesis.bankingservice.generated.payRequest.Builder.class);
  }

  public static final int COMMITTENT_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object committent_ = "";
  /**
   * <code>string committent = 1;</code>
   * @return The committent.
   */
  @java.lang.Override
  public java.lang.String getCommittent() {
    java.lang.Object ref = committent_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      committent_ = s;
      return s;
    }
  }
  /**
   * <code>string committent = 1;</code>
   * @return The bytes for committent.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCommittentBytes() {
    java.lang.Object ref = committent_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      committent_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AMOUNT_FIELD_NUMBER = 2;
  private long amount_ = 0L;
  /**
   * <code>int64 amount = 2;</code>
   * @return The amount.
   */
  @java.lang.Override
  public long getAmount() {
    return amount_;
  }

  public static final int ID_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object id_ = "";
  /**
   * <code>string id = 3;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 3;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(committent_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, committent_);
    }
    if (amount_ != 0L) {
      output.writeInt64(2, amount_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, id_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(committent_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, committent_);
    }
    if (amount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, amount_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, id_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.thesis.bankingservice.generated.payRequest)) {
      return super.equals(obj);
    }
    com.thesis.bankingservice.generated.payRequest other = (com.thesis.bankingservice.generated.payRequest) obj;

    if (!getCommittent()
        .equals(other.getCommittent())) return false;
    if (getAmount()
        != other.getAmount()) return false;
    if (!getId()
        .equals(other.getId())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + COMMITTENT_FIELD_NUMBER;
    hash = (53 * hash) + getCommittent().hashCode();
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAmount());
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.thesis.bankingservice.generated.payRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.thesis.bankingservice.generated.payRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thesis.bankingservice.generated.payRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.thesis.bankingservice.generated.payRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.thesis.bankingservice.generated.payRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.thesis.bankingservice.generated.payRequest)
      com.thesis.bankingservice.generated.payRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.thesis.bankingservice.generated.BankingOuterClass.internal_static_com_thesis_bankingservice_generated_payRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.thesis.bankingservice.generated.BankingOuterClass.internal_static_com_thesis_bankingservice_generated_payRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.thesis.bankingservice.generated.payRequest.class, com.thesis.bankingservice.generated.payRequest.Builder.class);
    }

    // Construct using com.thesis.bankingservice.generated.payRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      committent_ = "";
      amount_ = 0L;
      id_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.thesis.bankingservice.generated.BankingOuterClass.internal_static_com_thesis_bankingservice_generated_payRequest_descriptor;
    }

    @java.lang.Override
    public com.thesis.bankingservice.generated.payRequest getDefaultInstanceForType() {
      return com.thesis.bankingservice.generated.payRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.thesis.bankingservice.generated.payRequest build() {
      com.thesis.bankingservice.generated.payRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.thesis.bankingservice.generated.payRequest buildPartial() {
      com.thesis.bankingservice.generated.payRequest result = new com.thesis.bankingservice.generated.payRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.thesis.bankingservice.generated.payRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.committent_ = committent_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.amount_ = amount_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.id_ = id_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.thesis.bankingservice.generated.payRequest) {
        return mergeFrom((com.thesis.bankingservice.generated.payRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.thesis.bankingservice.generated.payRequest other) {
      if (other == com.thesis.bankingservice.generated.payRequest.getDefaultInstance()) return this;
      if (!other.getCommittent().isEmpty()) {
        committent_ = other.committent_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (other.getAmount() != 0L) {
        setAmount(other.getAmount());
      }
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              committent_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 16: {
              amount_ = input.readInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 26: {
              id_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object committent_ = "";
    /**
     * <code>string committent = 1;</code>
     * @return The committent.
     */
    public java.lang.String getCommittent() {
      java.lang.Object ref = committent_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        committent_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string committent = 1;</code>
     * @return The bytes for committent.
     */
    public com.google.protobuf.ByteString
        getCommittentBytes() {
      java.lang.Object ref = committent_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        committent_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string committent = 1;</code>
     * @param value The committent to set.
     * @return This builder for chaining.
     */
    public Builder setCommittent(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      committent_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string committent = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCommittent() {
      committent_ = getDefaultInstance().getCommittent();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string committent = 1;</code>
     * @param value The bytes for committent to set.
     * @return This builder for chaining.
     */
    public Builder setCommittentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      committent_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private long amount_ ;
    /**
     * <code>int64 amount = 2;</code>
     * @return The amount.
     */
    @java.lang.Override
    public long getAmount() {
      return amount_;
    }
    /**
     * <code>int64 amount = 2;</code>
     * @param value The amount to set.
     * @return This builder for chaining.
     */
    public Builder setAmount(long value) {

      amount_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>int64 amount = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAmount() {
      bitField0_ = (bitField0_ & ~0x00000002);
      amount_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 3;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 3;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 3;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      id_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      id_ = getDefaultInstance().getId();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string id = 3;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      id_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.thesis.bankingservice.generated.payRequest)
  }

  // @@protoc_insertion_point(class_scope:com.thesis.bankingservice.generated.payRequest)
  private static final com.thesis.bankingservice.generated.payRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.thesis.bankingservice.generated.payRequest();
  }

  public static com.thesis.bankingservice.generated.payRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<payRequest>
      PARSER = new com.google.protobuf.AbstractParser<payRequest>() {
    @java.lang.Override
    public payRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<payRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<payRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.thesis.bankingservice.generated.payRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
