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

/**
 * 工厂挂钩，允许自定义修改应用程序上下文的 bean 定义，调整上下文底层 bean 工厂的 bean 属性值。
 * 对于针对覆盖应用程序上下文中配置的 bean 属性的系统管理员的自定义配置文件很有用。有关满足此类配置需求的开箱即用解决方案，请参阅PropertyResourceConfigurer及其具体实现。
 * BeanFactoryPostProcessor可以与 bean 定义交互和修改，但不能与 bean 实例交互。这样做可能会导致过早的 bean 实例化，违反容器并导致意外的副作用。如果需要 bean 实例交互，请考虑实现BeanPostProcessor 。
 * 登记
 * ApplicationContext在其 bean 定义中自动检测BeanFactoryPostProcessor bean，并在创建任何其他 bean 之前应用它们。 BeanFactoryPostProcessor也可以通过ConfigurableApplicationContext以编程方式注册。
 * 订购
 * 在ApplicationContext中自动检测到的BeanFactoryPostProcessor bean 将根据org.springframework.core.PriorityOrdered和org.springframework.core.Ordered语义进行排序。
 * 相反，使用ConfigurableApplicationContext以编程方式注册的BeanFactoryPostProcessor bean 将按注册顺序应用；对于以编程方式注册的后处理器，
 * 将忽略通过实现PriorityOrdered或Ordered接口表达的任何排序语义。此外，对于BeanFactoryPostProcessor bean，不考虑@Order注释。
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 06.07.2003
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 * 在标准初始化之后修改应用程序上下文的内部 bean 工厂。所有 bean 定义都将被加载，但还没有 bean 被实例化。这允许覆盖或添加属性，甚至是急切初始化的 bean
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
