/*
 * Copyright 2002-2016 the original author or authors.
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
 * 由希望在 bean 工厂中了解其 bean 名称的 bean 实现的接口。请注意，通常不建议对象依赖于其 bean 名称，
 * 因为这表示对外部配置的潜在脆弱依赖，以及对 Spring API 的可能不必要的依赖。
 * 有关所有 bean 生命周期方法的列表，请参阅{@link BeanFactory BeanFactory javadocs}
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 01.11.2003
 * @see BeanClassLoaderAware
 * @see BeanFactoryAware
 * @see InitializingBean
 */
public interface BeanNameAware extends Aware {

	/**
	 * 在创建此 bean 的 bean 工厂中设置 bean 的名称。
	 * 在填充普通 bean 属性之后但在初始化回调（如InitializingBean.afterPropertiesSet()或自定义初始化方法）之前调用。
	 * @param name 工厂中 bean 的名称。请注意，此名称是工厂中使用的实际 bean 名称，可能与最初指定的名称不同：特别是对于内部 bean 名称，
	 * 实际 bean 名称可能已通过附加“...”后缀而变得唯一。如果需要，
	 * 使用 {@link BeanFactoryUtilsoriginalBeanName(String)} 方法提取原始 bean 名称（不带后缀）
	 */
	void setBeanName(String name);

}
