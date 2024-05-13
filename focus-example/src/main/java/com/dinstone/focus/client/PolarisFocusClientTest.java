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

import com.dinstone.focus.client.polaris.CircuitBreakInterceptor;
import com.dinstone.focus.client.polaris.PolarisLocatorOptions;
import com.dinstone.focus.example.DemoService;
import com.dinstone.focus.transport.photon.PhotonConnectOptions;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;

public class PolarisFocusClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(FocusClientTest.class);

    public static void main(String[] args) {

        LOG.info("init start");

//        final String pa = "119.91.66.223:8091";// "192.168.1.120:8091";
        ClientOptions option = new ClientOptions("focus.demo.client");

        PolarisLocatorOptions locatorOptions = new PolarisLocatorOptions();
        option.setLocatorOptions(locatorOptions);

        option.setConnectOptions(new PhotonConnectOptions().setConnectPoolSize(1));

        final CircuitBreakInterceptor interceptor = new CircuitBreakInterceptor();
        //option.addInterceptor(interceptor);

        option.setConnectRetry(2).setTimeoutRetry(2);

        FocusClient client = new FocusClient(option);
        DemoService ds = client.importing(DemoService.class, "focus.demo.server");

        LOG.info("int end");

        try {
            ds.hello(null);
        } catch (Exception e) {
            LOG.error("null param error", e);
        }

        try {
            execute(ds, "hot: ");
            execute(ds, "exe: ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.close();
        interceptor.close();
    }

    private static void execute(DemoService ds, String tag) {
        int c = 0;
        long st = System.currentTimeMillis();
        int loopCount = 50000;
        while (c < loopCount) {
            try {
                ds.hello("dinstoneo");
            } catch (Exception e) {
                LOG.error("{} error: {}", c, e.getMessage());
            }
            c++;
        }
        long et = System.currentTimeMillis() - st;
        System.out.println(tag + et + " ms, " + (loopCount * 1000 / et) + " tps");
    }

}
