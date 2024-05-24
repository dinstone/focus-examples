package com.dinstone.focus;

import java.util.concurrent.TimeUnit;

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

public class TelemetryHelper {

    public static OpenTelemetry getTelemetry(String serviceName) {
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
        // jaeger implement, UI: http://192.168.1.120:16686/
        String url = "http://192.168.1.120:4317";
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).setTimeout(2, TimeUnit.SECONDS).build();
    }
}
