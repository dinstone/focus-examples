package com.dinstone.focus;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;

public class PrometheusTest {

	public static void main(String[] args) throws InterruptedException {

		Resource resource = Resource.getDefault().merge(Resource.create(Attributes.empty()));

		String jaegerUrl = "http://192.168.1.120:4317";

//		final String pushgateway = "http://192.168.1.120:9091";

		PeriodicMetricReader metricReader = PeriodicMetricReader
				.builder(OtlpGrpcMetricExporter.builder().setEndpoint(jaegerUrl).build()).build();

		SdkMeterProvider build = SdkMeterProvider.builder().setResource(resource).registerMetricReader(metricReader)
				.build();

		OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder().setMeterProvider(build).buildAndRegisterGlobal();

		Meter mxsm = openTelemetrySdk.getMeter("mxsm");
		MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
		AtomicLong cc = new AtomicLong();
		mxsm.upDownCounterBuilder("process.runtime.jvm.memory.usage").setUnit("Bytes")
				.buildWithCallback(record -> record.record(Runtime.getRuntime().totalMemory(),
						Attributes.of(AttributeKey.stringKey("type"), "heap")));
		mxsm.upDownCounterBuilder("process.runtime.jvm.memory.usage_after_last_gc").setUnit("bytes").buildWithCallback(
				record -> record.record(cc.longValue(), Attributes.of(AttributeKey.stringKey("type"), "heap")));
		LongCounter build1 = mxsm.counterBuilder("mxsm.qqq").setUnit("1").build();
		long i = 1;
		for (;;) {
			cc.set(mxb.getHeapMemoryUsage().getUsed());
			build1.add(i);
			TimeUnit.SECONDS.sleep(1);
		}

	}

}
