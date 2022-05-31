/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.beans.factory.parsing;

import java.util.ArrayDeque;

import org.springframework.lang.Nullable;

/**
 * 简单的基于{@link ArrayDeque}的结构，用于在解析过程中跟踪逻辑位置。
 * 在解析阶段的每个点以特定于阅读器的方式将{@link Entry entries}添加到 ArrayDeque。
 * 调用{@link #toString()}将在解析阶段呈现当前逻辑位置的树形视图。此表示旨在用于错误消息。
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
public final class ParseState {

	/**
	 * Internal {@link ArrayDeque} storage.
	 */
	private final ArrayDeque<Entry> state;


	/**
	 * Create a new {@code ParseState} with an empty {@link ArrayDeque}.
	 */
	public ParseState() {
		this.state = new ArrayDeque<>();
	}

	/**
	 * Create a new {@code ParseState} whose {@link ArrayDeque} is a clone
	 * of the state in the passed-in {@code ParseState}.
	 */
	private ParseState(ParseState other) {
		this.state = other.state.clone();
	}


	/**
	 * Add a new {@link Entry} to the {@link ArrayDeque}.
	 */
	public void push(Entry entry) {
		this.state.push(entry);
	}

	/**
	 * Remove an {@link Entry} from the {@link ArrayDeque}.
	 */
	public void pop() {
		this.state.pop();
	}

	/**
	 * Return the {@link Entry} currently at the top of the {@link ArrayDeque} or
	 * {@code null} if the {@link ArrayDeque} is empty.
	 */
	@Nullable
	public Entry peek() {
		return this.state.peek();
	}

	/**
	 * Create a new instance of {@link ParseState} which is an independent snapshot
	 * of this instance.
	 */
	public ParseState snapshot() {
		return new ParseState(this);
	}


	/**
	 * Returns a tree-style representation of the current {@code ParseState}.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(64);
		int i = 0;
		for (ParseState.Entry entry : this.state) {
			if (i > 0) {
				sb.append('\n');
				for (int j = 0; j < i; j++) {
					sb.append('\t');
				}
				sb.append("-> ");
			}
			sb.append(entry);
			i++;
		}
		return sb.toString();
	}


	/**
	 * Marker interface for entries into the {@link ParseState}.
	 */
	public interface Entry {
	}

}
