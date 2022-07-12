/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * 允许自定义修改新 bean 实例的工厂挂钩 - 例如，检查标记接口或使用代理包装 bean。
 * 通常，通过标记接口等填充 bean 的后处理器将实现postProcessBeforeInitialization ，而使用代理包装 bean 的后处理器通常会实现postProcessAfterInitialization 。
 * 登记
 * ApplicationContext可以在其 bean 定义中自动检测BeanPostProcessor bean，并将这些后处理器应用于随后创建的任何 bean。一个普通的BeanFactory允许以编程方式注册后处理器，将它们应用到通过 bean 工厂创建的所有 bean。
 * 订购
 * 在ApplicationContext中自动检测到的BeanPostProcessor bean 将根据org.springframework.core.PriorityOrdered和org.springframework.core.Ordered语义进行排序。
 * 相反，以编程方式注册到BeanFactory的BeanPostProcessor bean 将按照注册顺序应用；对于以编程方式注册的后处理器，将忽略通过实现PriorityOrdered或Ordered接口表达的任何排序语义。
 * 此外，对于BeanPostProcessor bean，不考虑@Order注释。
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 10.10.2003
 * @see InstantiationAwareBeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see ConfigurableBeanFactory#addBeanPostProcessor
 * @see BeanFactoryPostProcessor
 */
public interface BeanPostProcessor {

	/**
	 * 实例化、依赖注入完毕，
	 * 在调用显示的初始化之前完成一些定制的初始化任务
	 */
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * 实例化、依赖注入、初始化完毕时执行
	 */
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
