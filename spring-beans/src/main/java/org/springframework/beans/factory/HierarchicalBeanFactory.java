/*
 * Copyright 2002-2012 the original author or authors.
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

import org.springframework.lang.Nullable;

/**
 * 由可以成为层次结构一部分的 bean 工厂实现的子接口。
 * 可以在 ConfigurableBeanFactory 接口中找到允许以可配置方式设置父级的 bean 工厂的相应{@code setParentBeanFactory}方法。
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 07.07.2003
 * @see org.springframework.beans.factory.config.ConfigurableBeanFactory#setParentBeanFactory
 */
public interface HierarchicalBeanFactory extends BeanFactory {

	/**
	 * 返回父 bean 工厂，如果没有，则返回null 。
	 */
	@Nullable
	BeanFactory getParentBeanFactory();

	/**
	 * 返回本地 bean 工厂是否包含给定名称的 bean，忽略祖先上下文中定义的 bean。
	 * 这是containsBean的替代方法，忽略来自祖先 bean 工厂的给定名称的 bean。
	 * @param name the name of the bean to query
	 * @return whether a bean with the given name is defined in the local factory
	 * @see BeanFactory#containsBean
	 */
	boolean containsLocalBean(String name);

}
