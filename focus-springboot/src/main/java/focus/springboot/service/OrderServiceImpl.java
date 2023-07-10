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
package focus.springboot.service;

import org.springframework.stereotype.Component;

import com.dinstone.focus.annotation.ServiceDefinition;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;

import focus.springboot.api.OrderRequest;
import focus.springboot.api.OrderResponse;
import focus.springboot.api.OrderService;

@Component
@ServiceDefinition
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public OrderResponse createOrder(OrderRequest order) {
		log.info("user is exist:{}", order.getUid());

		return new OrderResponse().setOid(order.getUid() + "-" + order.getPoi() + "-" + order.getSn());
	}

	@Override
	public OrderResponse findOldOrder(OrderRequest order) {
		return new OrderResponse().setOid(order.getUid() + "-" + order.getPoi() + "-" + order.getSn());
	}

}
