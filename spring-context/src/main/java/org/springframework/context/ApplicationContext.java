/*
 * Copyright 2002-2014 the original author or authors.
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

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;

/**
 *
 * 为应用程序提供配置的中央接口。这在应用程序运行时是只读的，但如果实现支持，则可以重新加载。
 * ApplicationContext 提供：
 * 用于访问应用程序组件的 Bean 工厂方法。继承自{@link org.springframework.beans.factory.ListableBeanFactory} 。
 * 以通用方式加载文件资源的能力。继承自{@link org.springframework.core.io.ResourceLoader}接口。
 * 向注册的侦听器发布事件的能力。继承自{@link ApplicationEventPublisher}接口。
 * 解决消息的能力，支持国际化。继承自{@link MessageSource}接口。
 * 从父上下文继承。后代上下文中的定义将始终具有优先权。这意味着，例如，整个 Web 应用程序可以使用单个父上下文，而每个 servlet 都有自己的子上下文，该子上下文独立于任何其他 servlet。
 * 除了标准的{@link org.springframework.beans.factory.BeanFactory}生命周期功能之外，
 * ApplicationContext 实现检测和调用{@link ApplicationContextAware} bean 以及{@link ResourceLoaderAware} 、 {@link ApplicationEventPublisherAware}和{@link MessageSourceAware} bean。
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see ConfigurableApplicationContext
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.core.io.ResourceLoader
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
		MessageSource, ApplicationEventPublisher, ResourcePatternResolver {

	/**
	 * Return the unique id of this application context.
	 * @return the unique id of the context, or {@code null} if none
	 */
	@Nullable
	String getId();

	/**
	 * 返回此上下文所属的已部署应用程序的名称。
	 * @return a name for the deployed application, or the empty String by default
	 */
	String getApplicationName();

	/**
	 * 返回此上下文的友好名称。
	 * @return a display name for this context (never {@code null})
	 */
	String getDisplayName();

	/**
	 * Return the timestamp when this context was first loaded.
	 * @return the timestamp (ms) when this context was first loaded
	 */
	long getStartupDate();

	/**
	 * 返回父上下文，如果没有父上下文，则返回null ，这是上下文层次结构的根。
	 * @return the parent context, or {@code null} if there is no parent
	 */
	@Nullable
	ApplicationContext getParent();

	/**
	 * Expose AutowireCapableBeanFactory functionality for this context.
	 * <p>This is not typically used by application code, except for the purpose of
	 * initializing bean instances that live outside of the application context,
	 * applying the Spring bean lifecycle (fully or partly) to them.
	 * <p>Alternatively, the internal BeanFactory exposed by the
	 * {@link ConfigurableApplicationContext} interface offers access to the
	 * {@link AutowireCapableBeanFactory} interface too. The present method mainly
	 * serves as a convenient, specific facility on the ApplicationContext interface.
	 * <p><b>NOTE: As of 4.2, this method will consistently throw IllegalStateException
	 * after the application context has been closed.</b> In current Spring Framework
	 * versions, only refreshable application contexts behave that way; as of 4.2,
	 * all application context implementations will be required to comply.
	 * @return the AutowireCapableBeanFactory for this context
	 * @throws IllegalStateException if the context does not support the
	 * {@link AutowireCapableBeanFactory} interface, or does not hold an
	 * autowire-capable bean factory yet (e.g. if {@code refresh()} has
	 * never been called), or if the context has been closed already
	 * @see ConfigurableApplicationContext#refresh()
	 * @see ConfigurableApplicationContext#getBeanFactory()
	 */
	AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;

}
