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

import java.util.Date;

import com.dinstone.focus.TelemetryHelper;
import com.dinstone.focus.example.OrderRequest;
import com.dinstone.focus.example.OrderResponse;
import com.dinstone.focus.example.OrderService;
import com.dinstone.focus.invoke.Context;
import com.dinstone.focus.invoke.Interceptor;
import com.dinstone.focus.propagate.Baggage;
import com.dinstone.focus.telemetry.TelemetryInterceptor;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;
import io.opentelemetry.api.OpenTelemetry;

public class TracingServiceClient {

    private static final Logger LOG = LoggerFactory.getLogger(TracingServiceClient.class);

    public static void main(String[] args) {

        String appName = "order.client";
        OpenTelemetry openTelemetry = TelemetryHelper.getTelemetry(appName);
        Interceptor tf = new TelemetryInterceptor(openTelemetry, Interceptor.Kind.CLIENT);

        ClientOptions clientOptions = new ClientOptions(appName).connect("localhost", 3303);
        clientOptions.addInterceptor(tf);
        FocusClient client = new FocusClient(clientOptions);

        OrderService oc = client.importing(OrderService.class);

        OrderRequest order = new OrderRequest();
        order.setCt(new Date());
        order.setPoi("1234");
        order.setSn("MDHEWED");
        order.setUid("dinstone");

        try (Context context = Context.create()) {
            Baggage baggage = new Baggage();
            baggage.put("swimlane", "gray");
            context.put(Baggage.ContextKey, baggage);

            OrderResponse o = oc.createOrder(order);
            LOG.info("order id = {}", o.getOid());
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }

    }

}
