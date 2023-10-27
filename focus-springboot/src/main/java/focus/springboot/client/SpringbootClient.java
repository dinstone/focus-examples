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
package focus.springboot.client;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.dinstone.focus.client.ClientOptions;
import com.dinstone.focus.client.starter.EnableFocusClient;

import focus.springboot.spi.ImService;

@Configurable
@SpringBootApplication
@EnableFocusClient("focus.springboot.api")
@ComponentScan(basePackages = "focus.springboot.spi")
public class SpringbootClient {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext c = SpringApplication.run(SpringbootClient.class, args);
		try {
			ImService im = c.getBean(ImService.class);

			String m = im.sayHi("apple");
			System.out.println("message is " + m);
		} finally {
			c.close();
		}
	}

	@Bean
	@ConditionalOnMissingBean
	ClientOptions focusClientOptions() {
		return new ClientOptions("focus.springboot.client").connect("127.0.0.1", 2222);
	}

}
