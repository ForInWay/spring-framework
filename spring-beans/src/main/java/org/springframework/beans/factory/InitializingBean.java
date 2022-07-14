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

package org.springframework.beans.factory;

/**
 * 由{@link BeanFactory}设置所有属性后需要做出反应的 bean 实现的接口：例如执行自定义初始化，或仅检查所有强制属性是否已设置。
 * 实现{@code InitializingBean}的另一种方法是指定自定义 init 方法，例如在 XML bean 定义中。有关所有 bean 生命周期方法的列表，
 * 请参阅{@link BeanFactory BeanFactory javadocs}。
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see DisposableBean
 * @see org.springframework.beans.factory.config.BeanDefinition#getPropertyValues()
 * @see org.springframework.beans.factory.support.AbstractBeanDefinition#getInitMethodName()
 */
public interface InitializingBean {

	/**
	 * 在设置所有 bean 属性并满足BeanFactoryAware 、 ApplicationContextAware等后由包含BeanFactory调用。
	 * 此方法允许 bean 实例在设置所有 bean 属性后执行其整体配置和最终初始化的验证。
	 */
	void afterPropertiesSet() throws Exception;

}
