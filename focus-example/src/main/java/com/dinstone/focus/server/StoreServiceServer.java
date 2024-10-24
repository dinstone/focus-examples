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
package com.dinstone.focus.server;

import java.io.IOException;

import com.dinstone.focus.TelemetryHelper;
import com.dinstone.focus.client.ClientOptions;
import com.dinstone.focus.client.FocusClient;
import com.dinstone.focus.client.ImportOptions;
import com.dinstone.focus.example.StoreService;
import com.dinstone.focus.example.StoreServiceImpl;
import com.dinstone.focus.example.UserCheckService;
import com.dinstone.focus.invoke.Interceptor;
import com.dinstone.focus.serialize.protobuf.ProtobufSerializer;
import com.dinstone.focus.telemetry.TelemetryInterceptor;
import com.dinstone.focus.transport.photon.PhotonAcceptOptions;
import com.dinstone.focus.transport.photon.PhotonConnectOptions;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;

public class StoreServiceServer {

    private static final Logger LOG = LoggerFactory.getLogger(StoreServiceServer.class);

    public static void main(String[] args) {

        FocusServer sss = createStoreServiceServer();

        LOG.info("server start");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sss.close();
        LOG.info("server stop");
    }

    private static FocusServer createStoreServiceServer() {
        String serviceName = "store.service";
        OpenTelemetry openTelemetry = TelemetryHelper.getTelemetry(serviceName);
        Interceptor tf = new TelemetryInterceptor(openTelemetry, Interceptor.Kind.SERVER);

        ServerOptions serverOptions = new ServerOptions(serviceName);
        serverOptions.listen("localhost", 3302);
        serverOptions.addInterceptor(tf).setAcceptOptions(new PhotonAcceptOptions());
        FocusServer server = new FocusServer(serverOptions);
        UserCheckService userService = createUserServiceRpc(openTelemetry);
        server.exporting(StoreService.class, new StoreServiceImpl(userService));
        server.start();
        return server;
    }

    private static UserCheckService createUserServiceRpc(OpenTelemetry openTelemetry) {
        Interceptor tf = new TelemetryInterceptor(openTelemetry, Interceptor.Kind.CLIENT);

        ClientOptions option = new ClientOptions("user.service.client").connect("localhost", 3301)
                .setConnectOptions(new PhotonConnectOptions()).addInterceptor(tf);
        FocusClient client = new FocusClient(option);

        ImportOptions ro = new ImportOptions(UserCheckService.class.getName())
                .setSerializerType(ProtobufSerializer.SERIALIZER_TYPE);
        return client.importing(UserCheckService.class, ro);
    }

}
