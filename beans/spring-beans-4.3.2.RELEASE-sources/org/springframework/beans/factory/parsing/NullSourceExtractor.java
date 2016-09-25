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

package org.springframework.beans.factory.parsing;

import org.springframework.core.io.Resource;

/**
 * Simple implementation of {@link SourceExtractor} that returns {@code null}
 * as the source metadata.
 * SourceExtractor 的简单实现，返回null作为资源元数据
 *
 * <p>This is the default implementation and prevents too much metadata from being
 * held in memory during normal (non-tooled) runtime usage.
 * 这是默认实现，防止在正常运行中内存中有过多的数据
 *
 * @author Rob Harrop
 * @since 2.0
 */
public class NullSourceExtractor implements SourceExtractor {

	/**
	 * This implementation simply returns {@code null} for any input.
	 * 返回空
	 */
	@Override
	public Object extractSource(Object sourceCandidate, Resource definitionResource) {
		return null;
	}

}
