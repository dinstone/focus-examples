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
package focus.springboot.spi;

import org.springframework.stereotype.Service;

import com.dinstone.focus.annotation.ServiceReference;

import focus.springboot.api.OrderRequest;
import focus.springboot.api.OrderResponse;
import focus.springboot.api.OrderService;

@Service
public class ImService {

	@ServiceReference
	OrderService service;

	public String sayHi(String name) throws Exception {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setUid(name);
		OrderResponse res = service.findOldOrder(orderRequest);
		return res.getOid();
	}

}
