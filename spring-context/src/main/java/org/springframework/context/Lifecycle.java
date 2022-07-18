/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.context;

/**
 * 定义启动/停止生命周期控制方法的通用接口。典型的用例是控制异步处理。注意：此接口并不暗示特定的自动启动语义。
 * 考虑为此目的实施{@link SmartLifecycle}。
 * 可以由组件（通常是在 Spring 上下文中定义的 Spring bean）和容器（通常是 Spring {@link ApplicationContext}本身）实现。
 * 容器会将启动/停止信号传播到每个容器内应用的所有组件，例如运行时的停止/重新启动场景。
 * 可用于直接调用或通过 JMX 进行管理操作。在后一种情况下， {@link org.springframework.jmx.export.MBeanExporter}通常使用{@link org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler}定义，
 * 将活动控制组件的可见性限制在 Lifecycle 接口中。
 * 请注意，当前的 {@code Lifecycle}接口仅在顶级单例 bean上受支持。在任何其他组件上， {@code Lifecycle}接口将保持未被检测到，因此被忽略。
 * 此外，请注意扩展的{@link SmartLifecycle}接口提供了与应用程序上下文的启动和关闭阶段的复杂集成。
 * @author Juergen Hoeller
 * @since 2.0
 * @see SmartLifecycle
 * @see ConfigurableApplicationContext
 * @see org.springframework.jms.listener.AbstractMessageListenerContainer
 * @see org.springframework.scheduling.quartz.SchedulerFactoryBean
 */
public interface Lifecycle {

	/**
	 * 启动这个组件。
	 * 如果组件已经在运行，则不应抛出异常。
	 * 在容器的情况下，这会将启动信号传播到所有应用的组件。
	 * @see SmartLifecycle#isAutoStartup()
	 */
	void start();

	/**
	 * 停止此组件，通常以同步方式停止，以便组件在此方法返回时完全停止。当需要异步停止行为时，请考虑实施SmartLifecycle及其stop(Runnable)变体。
	 * 请注意，此停止通知不能保证在销毁之前出现：在定期关闭时， Lifecycle bean 将首先收到停止通知，然后再传播一般的销毁回调；
	 * 但是，在上下文生命周期内的热刷新或中止刷新尝试时，将调用给定 bean 的 destroy 方法，而无需预先考虑停止信号。
	 * 如果组件未运行（尚未启动），则不应引发异常。
	 * 在容器的情况下，这会将停止信号传播到所有应用的组件。
	 * @see SmartLifecycle#stop(Runnable)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	void stop();

	/**
	 * 检查此组件当前是否正在运行。
	 * 在容器的情况下，仅当所有应用的组件当前都在运行时才会返回true 。
	 * @return whether the component is currently running
	 */
	boolean isRunning();

}
