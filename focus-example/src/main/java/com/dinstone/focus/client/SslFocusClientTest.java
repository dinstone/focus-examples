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
package com.dinstone.focus.client;

import com.dinstone.focus.example.Account;
import com.dinstone.focus.example.AuthenCheck;
import com.dinstone.focus.invoke.Interceptor.Kind;
import com.dinstone.focus.serialize.protostuff.ProtostuffSerializer;
import com.dinstone.focus.telemetry.TelemetryInterceptor;
import com.dinstone.focus.transport.photon.PhotonConnectOptions;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;

public class SslFocusClientTest {

	private static final Logger LOG = LoggerFactory.getLogger(SslFocusClientTest.class);

	public static void main(String[] args) throws Exception {

		// create a client options, setting applicaiton code
		final String appName = "focus.ssl.client";
		ClientOptions clientOptions = new ClientOptions(appName);
		// add two service instance
		clientOptions.connect("localhost", 3333).connect("127.0.0.1", 3333);

		PhotonConnectOptions connectOptions = new PhotonConnectOptions();
		connectOptions.setEnableSsl(true);
		// setting ssl connetcion
		clientOptions.setConnectOptions(connectOptions);

		// setting serializer type
		clientOptions.setSerializerType(ProtostuffSerializer.SERIALIZER_TYPE);

		// setting interceptor
		OpenTelemetry t = getTelemetry(appName);
		clientOptions.addInterceptor(new TelemetryInterceptor(t, Kind.SERVER));

		FocusClient client = new FocusClient(clientOptions);

		try {
			AuthenCheck ac = client.importing(AuthenCheck.class,
					new ImportOptions("AuthenService").setTimeoutMillis(1000));
			String token = ac.login(new Account("dinstone", "123456"));
			LOG.info("user login success, token is {}", token);
		} finally {
			client.close();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	private static OpenTelemetry getTelemetry(String serviceName) {
		Resource resource = Resource.getDefault()
				.merge(Resource.create(Attributes.of(AttributeKey.stringKey("service.name"), serviceName)));

		SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
				// .addSpanProcessor(BatchSpanProcessor.builder(ZipkinSpanExporter.builder().build()).build())
				.setResource(resource).build();

		OpenTelemetry openTelemetry = OpenTelemetrySdk.builder().setTracerProvider(sdkTracerProvider)
				.setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
				.buildAndRegisterGlobal();
		return openTelemetry;
	}

}
