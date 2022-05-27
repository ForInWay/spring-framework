/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.core.env;

/**
 * 指示包含和公开{@link Environment}引用的组件的接口。
 * 所有 Spring 应用程序上下文都是 EnvironmentCapable，并且该接口主要用于在框架方法中执行{@code instanceof}检查，
 * 这些方法接受 BeanFactory 实例，这些实例可能实际上是也可能不是 {@link org.springframework.context.ApplicationContext ApplicationContext} 实例，以便与环境交互（如果确实可用）。
 * 如前所述， ApplicationContext扩展了 EnvironmentCapable，因此公开了一个{@link #getEnvironment()}方法；
 * 但是，{@link org.springframework.context.ConfigurableApplicationContext ConfigurableApplicationContext}重新定义{@link ConfigurableEnvironment}并缩小了签名以返回{@link ConfigurableEnvironment}。
 * 效果是 Environment 对象在从 ConfigurableApplicationContext 访问之前是“只读的”，此时它也可以被配置。
 * @author Chris Beams
 * @since 3.1
 * @see Environment
 * @see ConfigurableEnvironment
 * @see org.springframework.context.ConfigurableApplicationContext#getEnvironment()
 */
public interface EnvironmentCapable {

	/**
	 * Return the {@link Environment} associated with this component.
	 */
	Environment getEnvironment();

}
