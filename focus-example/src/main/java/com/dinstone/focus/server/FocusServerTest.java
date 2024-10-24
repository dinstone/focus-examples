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
import java.util.concurrent.TimeUnit;

import com.dinstone.focus.example.ArithService;
import com.dinstone.focus.example.ArithServiceImpl;
import com.dinstone.focus.example.AuthenService;
import com.dinstone.focus.example.DemoService;
import com.dinstone.focus.example.DemoServiceImpl;
import com.dinstone.focus.example.OrderService;
import com.dinstone.focus.example.OrderServiceImpl;
import com.dinstone.focus.example.UserService;
import com.dinstone.focus.example.UserServiceServerImpl;
import com.dinstone.focus.invoke.Interceptor;
import com.dinstone.focus.serialize.json.JacksonSerializer;
import com.dinstone.focus.serialize.protobuf.ProtobufSerializer;
import com.dinstone.focus.serialize.protostuff.ProtostuffSerializer;
import com.dinstone.focus.telemetry.TelemetryInterceptor;
import com.dinstone.focus.transport.photon.PhotonAcceptOptions;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;

public class FocusServerTest {

    private static final Logger LOG = LoggerFactory.getLogger(FocusServerTest.class);

    public static void main(String[] args) {

        String appName = "focus.example.server";
        OpenTelemetry openTelemetry = getTelemetry(appName);
        Interceptor tf = new TelemetryInterceptor(openTelemetry, Interceptor.Kind.SERVER);

        int port = 3333;
        String portParm = System.getProperty("port");
        if (portParm != null) {
            port = Integer.parseInt(portParm);
        }

        ServerOptions serverOptions = new ServerOptions(appName).listen("localhost", port);
        serverOptions.addInterceptor(tf);

        PhotonAcceptOptions acceptOptions = new PhotonAcceptOptions();
        acceptOptions.setBusinessSize(4);
        acceptOptions.setWorkerSize(2);

        serverOptions.setAcceptOptions(acceptOptions);
        // serverOptions.setSerializerType(ProtostuffSerializer.SERIALIZER_TYPE);

        FocusServer server = new FocusServer(serverOptions);

        server.exporting(UserService.class, new UserServiceServerImpl());
        server.exporting(DemoService.class, new DemoServiceImpl());

        // stuff
        server.exporting(OrderService.class, new OrderServiceImpl(null, null),
                new ExportOptions(OrderService.class.getName())
                        .setSerializerType(ProtostuffSerializer.SERIALIZER_TYPE));
        // json
        server.exporting(OrderService.class, new OrderServiceImpl(null, null),
                new ExportOptions("OrderService").setSerializerType(JacksonSerializer.SERIALIZER_TYPE));

        // export alias service
        server.exporting(AuthenService.class, new AuthenService(), "AuthenService");
        server.exporting(ArithService.class, new ArithServiceImpl(),
                new ExportOptions("ArithService").setSerializerType(ProtobufSerializer.SERIALIZER_TYPE));

        server.start();
        LOG.info("server start");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.close();
        LOG.info("server stop");
    }

    private static OpenTelemetry getTelemetry(String serviceName) {
        Resource resource = Resource.getDefault()
                .merge(Resource.create(Attributes.of(AttributeKey.stringKey("service.name"), serviceName)));

        final SpanExporter exporter = getOltpExporter();

        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(BatchSpanProcessor.builder(exporter).build()).setResource(resource).build();

        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder().setTracerProvider(sdkTracerProvider)
                .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
                .buildAndRegisterGlobal();
        return openTelemetry;
    }

    private static ZipkinSpanExporter getZipkinExporter() {
        final String endpoint = "http://192.168.1.120:9411/api/v2/spans";
        return ZipkinSpanExporter.builder().setEndpoint(endpoint).build();
    }

    private static OtlpGrpcSpanExporter getOltpExporter() {
        // jaeger implement otlp for http://192.168.1.120:16686/search
        String url = "http://192.168.1.120:4317";
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).setTimeout(2, TimeUnit.SECONDS).build();
    }

}
