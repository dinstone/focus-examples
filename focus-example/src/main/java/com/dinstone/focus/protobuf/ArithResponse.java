/*
 * Copyright (C) 2019~2023 dinstone<dinstone@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: authir.proto

package com.dinstone.focus.protobuf;

/**
 * Protobuf type {@code message.ArithResponse}
 */
public final class ArithResponse extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:message.ArithResponse)
        ArithResponseOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use ArithResponse.newBuilder() to construct.
    private ArithResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ArithResponse() {
    }

    @java.lang.Override
    @SuppressWarnings({ "unused" })
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new ArithResponse();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ArithResponse(com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new java.lang.NullPointerException();
        }
        com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                case 0:
                    done = true;
                    break;
                case 9: {

                    c_ = input.readDouble();
                    break;
                }
                default: {
                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                        done = true;
                    }
                    break;
                }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        } finally {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return com.dinstone.focus.protobuf.ArithProto.internal_static_message_ArithResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return com.dinstone.focus.protobuf.ArithProto.internal_static_message_ArithResponse_fieldAccessorTable
                .ensureFieldAccessorsInitialized(com.dinstone.focus.protobuf.ArithResponse.class,
                        com.dinstone.focus.protobuf.ArithResponse.Builder.class);
    }

    public static final int C_FIELD_NUMBER = 1;
    private double c_;

    /**
     * <code>double c = 1;</code>
     * 
     * @return The c.
     */
    @java.lang.Override
    public double getC() {
        return c_;
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1)
            return true;
        if (isInitialized == 0)
            return false;

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (c_ != 0D) {
            output.writeDouble(1, c_);
        }
        unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1)
            return size;

        size = 0;
        if (c_ != 0D) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(1, c_);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof com.dinstone.focus.protobuf.ArithResponse)) {
            return super.equals(obj);
        }
        com.dinstone.focus.protobuf.ArithResponse other = (com.dinstone.focus.protobuf.ArithResponse) obj;

        if (java.lang.Double.doubleToLongBits(getC()) != java.lang.Double.doubleToLongBits(other.getC()))
            return false;
        if (!unknownFields.equals(other.unknownFields))
            return false;
        return true;
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + C_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(java.lang.Double.doubleToLongBits(getC()));
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseDelimitedFrom(java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static com.dinstone.focus.protobuf.ArithResponse parseFrom(com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(com.dinstone.focus.protobuf.ArithResponse prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * Protobuf type {@code message.ArithResponse}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:message.ArithResponse)
            com.dinstone.focus.protobuf.ArithResponseOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return com.dinstone.focus.protobuf.ArithProto.internal_static_message_ArithResponse_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return com.dinstone.focus.protobuf.ArithProto.internal_static_message_ArithResponse_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(com.dinstone.focus.protobuf.ArithResponse.class,
                            com.dinstone.focus.protobuf.ArithResponse.Builder.class);
        }

        // Construct using com.dinstone.focus.protobuf.ArithResponse.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            c_ = 0D;

            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return com.dinstone.focus.protobuf.ArithProto.internal_static_message_ArithResponse_descriptor;
        }

        @java.lang.Override
        public com.dinstone.focus.protobuf.ArithResponse getDefaultInstanceForType() {
            return com.dinstone.focus.protobuf.ArithResponse.getDefaultInstance();
        }

        @java.lang.Override
        public com.dinstone.focus.protobuf.ArithResponse build() {
            com.dinstone.focus.protobuf.ArithResponse result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public com.dinstone.focus.protobuf.ArithResponse buildPartial() {
            com.dinstone.focus.protobuf.ArithResponse result = new com.dinstone.focus.protobuf.ArithResponse(this);
            result.c_ = c_;
            onBuilt();
            return result;
        }

        @java.lang.Override
        public Builder clone() {
            return super.clone();
        }

        @java.lang.Override
        public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
            return super.setField(field, value);
        }

        @java.lang.Override
        public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
            return super.clearField(field);
        }

        @java.lang.Override
        public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return super.clearOneof(oneof);
        }

        @java.lang.Override
        public Builder setRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, int index,
                java.lang.Object value) {
            return super.setRepeatedField(field, index, value);
        }

        @java.lang.Override
        public Builder addRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
            return super.addRepeatedField(field, value);
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof com.dinstone.focus.protobuf.ArithResponse) {
                return mergeFrom((com.dinstone.focus.protobuf.ArithResponse) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(com.dinstone.focus.protobuf.ArithResponse other) {
            if (other == com.dinstone.focus.protobuf.ArithResponse.getDefaultInstance())
                return this;
            if (other.getC() != 0D) {
                setC(other.getC());
            }
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
            com.dinstone.focus.protobuf.ArithResponse parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (com.dinstone.focus.protobuf.ArithResponse) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private double c_;

        /**
         * <code>double c = 1;</code>
         * 
         * @return The c.
         */
        @java.lang.Override
        public double getC() {
            return c_;
        }

        /**
         * <code>double c = 1;</code>
         * 
         * @param value
         *            The c to set.
         * 
         * @return This builder for chaining.
         */
        public Builder setC(double value) {

            c_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>double c = 1;</code>
         * 
         * @return This builder for chaining.
         */
        public Builder clearC() {

            c_ = 0D;
            onChanged();
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:message.ArithResponse)
    }

    // @@protoc_insertion_point(class_scope:message.ArithResponse)
    private static final com.dinstone.focus.protobuf.ArithResponse DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new com.dinstone.focus.protobuf.ArithResponse();
    }

    public static com.dinstone.focus.protobuf.ArithResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ArithResponse> PARSER = new com.google.protobuf.AbstractParser<ArithResponse>() {
        @java.lang.Override
        public ArithResponse parsePartialFrom(com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new ArithResponse(input, extensionRegistry);
        }
    };

    public static com.google.protobuf.Parser<ArithResponse> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ArithResponse> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public com.dinstone.focus.protobuf.ArithResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}
