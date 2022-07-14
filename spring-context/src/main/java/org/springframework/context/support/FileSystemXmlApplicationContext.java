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

package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

/**
 * 独立的 XML 应用程序上下文，从文件系统或 URL 中获取上下文定义文件，将普通路径解释为相对文件系统位置（例如“mydir/myfile.txt”）。对于测试工具以及独立环境很有用。
 * 注意：普通路径将始终被解释为相对于当前 VM 工作目录，即使它们以斜杠开头。 （这与 Servlet 容器中的语义一致。）使用显式的“file:”前缀来强制使用绝对文件路径。
 * 配置位置默认值可以通过{@link #getConfigLocations}覆盖，配置位置可以表示具体文件，如“/myfiles/context.xml”或 Ant 样式模式{@link org.springframework.util.AntPathMatcher}，如“/myfiles/*-context.xml”（参见org.springframework.util.AntPathMatcher javadoc 获取模式详细信息）。
 * 注意：在多个配置位置的情况下，以后的 bean 定义将覆盖之前加载的文件中定义的那些。这可以用来通过额外的 XML 文件故意覆盖某些 bean 定义。
 * 这是一个简单的、一站式便利的ApplicationContext。考虑将{@link GenericApplicationContext}类与{@link org.springframework.beans.factory.xml.XmlBeanDefinitionReader}结合使用，以获得更灵活的上下文设置
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see #getResource
 * @see #getResourceByPath
 * @see GenericApplicationContext
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

	/**
	 * Create a new FileSystemXmlApplicationContext for bean-style configuration.
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public FileSystemXmlApplicationContext() {
	}

	/**
	 * Create a new FileSystemXmlApplicationContext for bean-style configuration.
	 * @param parent the parent context
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public FileSystemXmlApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * @param configLocation file path
	 * @throws BeansException if context creation failed
	 */
	public FileSystemXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param configLocations array of file paths
	 * @throws BeansException if context creation failed
	 */
	public FileSystemXmlApplicationContext(String... configLocations) throws BeansException {
		this(configLocations, true, null);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param configLocations array of file paths
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
		this(configLocations, true, parent);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML files.
	 * @param configLocations array of file paths
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
		this(configLocations, refresh, null);
	}

	/**
	 * 使用给定的父级创建一个新的 FileSystemXmlApplicationContext，从给定的 XML 文件加载定义。
	 * @param configLocations array of file paths
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public FileSystemXmlApplicationContext(
			String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
			throws BeansException {

		super(parent);
		setConfigLocations(configLocations);
		if (refresh) {
			refresh();
		}
	}


	/**
	 * 将资源路径解析为文件系统路径。
	 * 注意：即使给定路径以斜杠开头，它也会被解释为相对于当前 VM 工作目录。这与 Servlet 容器中的语义一致。
	 * @param path the path to the resource
	 * @return the Resource handle
	 * @see org.springframework.web.context.support.XmlWebApplicationContext#getResourceByPath
	 */
	@Override
	protected Resource getResourceByPath(String path) {
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		return new FileSystemResource(path);
	}

}
