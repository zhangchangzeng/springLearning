/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.xml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.Resource;

/**
 * Convenience extension of {@link DefaultListableBeanFactory} that reads bean definitions
 * from an XML document. Delegates to {@link XmlBeanDefinitionReader} underneath; effectively
 * equivalent to using an XmlBeanDefinitionReader with a DefaultListableBeanFactory.
 *
 * <p>The structure, element and attribute names of the required XML document
 * are hard-coded in this class. (Of course a transform could be run if necessary
 * to produce this format). "beans" doesn't need to be the root element of the XML
 * document: This class will parse all bean definition elements in the XML file.
 *
 * <p>This class registers each bean definition with the {@link DefaultListableBeanFactory}
 * superclass, and relies on the latter's implementation of the {@link BeanFactory} interface.
 * It supports singletons, prototypes, and references to either of these kinds of bean.
 * See {@code "spring-beans-3.x.xsd"} (or historically, {@code "spring-beans-2.0.dtd"}) for
 * details on options and configuration style.
 *
 * <p><b>For advanced needs, consider using a {@link DefaultListableBeanFactory} with
 * an {@link XmlBeanDefinitionReader}.</b> The latter allows for reading from multiple XML
 * resources and is highly configurable in its actual XML parsing behavior.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 15 April 2001
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory
 * @see XmlBeanDefinitionReader
 * @deprecated as of Spring 3.1 in favor of {@link DefaultListableBeanFactory} and
 * {@link XmlBeanDefinitionReader}
 */
@Deprecated
@SuppressWarnings({"serial", "all"})
public class XmlBeanFactory extends DefaultListableBeanFactory {

	// 创建一个加载xml文件的reader 用来读取xml配置文件
	/*
		XmlBeanDefinitionReader类联系
		public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader
		构造方法
		public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
			super(registry);
		}
		
		AbstractBeanDefinitionReader类联系
		public abstract class AbstractBeanDefinitionReader implements EnvironmentCapable, BeanDefinitionReader
		
		成员变量
		private ResourceLoader resourceLoader;
		private Environment environment;
		
		构造方法
		protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
			Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
			this.registry = registry;

			// Determine ResourceLoader to use.
			if (this.registry instanceof ResourceLoader) {
				this.resourceLoader = (ResourceLoader) this.registry;
			}
			else {
				this.resourceLoader = new PathMatchingResourcePatternResolver();
			}

			// Inherit Environment if possible
			if (this.registry instanceof EnvironmentCapable) {
				this.environment = ((EnvironmentCapable) this.registry).getEnvironment();
			}
			else {
				this.environment = new StandardEnvironment();
			}
		}
		
		此句代码 获取到一个根据 XmlBeanFactory 的实例对象得到的一个设置了 resourceLoader 和 environment 的 reader 
		而 XmlBeanFactory 没有继承和实现 ResourceLoader 和 EnvironmentCapable 所以 reader 的 resourceLoader 和 environment 都是默认的
	*/
	private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);


	/**
	 * Create a new XmlBeanFactory with the given resource,
	 * 根据给定的资源创建一个XmlBeanFactory 实例
	 * which must be parsable using DOM.
	 * @param resource XML resource to load bean definitions from
	 * @throws BeansException in case of loading or parsing errors
	 */
	public XmlBeanFactory(Resource resource) throws BeansException {
		this(resource, null);
	}

	/**
	 * Create a new XmlBeanFactory with the given input stream,
	 * which must be parsable using DOM.
	 * @param resource XML resource to load bean definitions from
	 * @param parentBeanFactory parent bean factory
	 * @throws BeansException in case of loading or parsing errors
	 */
	public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException {
		super(parentBeanFactory);
		this.reader.loadBeanDefinitions(resource);
	}
}
