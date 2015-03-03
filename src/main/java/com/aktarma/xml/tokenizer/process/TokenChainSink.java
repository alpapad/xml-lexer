package com.aktarma.xml.tokenizer.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.IToken;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class TokenChainSink extends AbstractTokenVisitor {

	private List<TokenVisitor> sinks = new ArrayList<TokenVisitor>();

	public boolean addVisitor(TokenVisitor e) {
		return sinks.add(e);
	}

	public boolean removeVisitor(Object o) {
		return sinks.remove(o);
	}

	public boolean addAllVisitors(Collection<? extends TokenVisitor> c) {
		return sinks.addAll(c);
	}

	public boolean removeAllVisitors(Collection<?> c) {
		return sinks.removeAll(c);
	}

	public boolean retainAllVisitors(Collection<?> c) {
		return sinks.retainAll(c);
	}

	public TokenVisitor setVisitor(int index, TokenVisitor element) {
		return sinks.set(index, element);
	}

	public void addVisitor(int index, TokenVisitor element) {
		sinks.add(index, element);
	}

	@Override
	protected boolean dispatch(IToken token) {
		if (sinks.size() != 0) {
			for (TokenVisitor s : sinks) {
				if (!TokenType.visitType(s, token)) {
					break;
				}
			}
		}
		return true;
	}

	@Override
	public void start() {
		if (sinks.size() != 0) {
			for (TokenVisitor s : sinks) {
				s.start();
			}
		}
	}

	@Override
	public void finish() {
		if (sinks.size() != 0) {
			for (TokenVisitor s : sinks) {
				s.finish();
			}
		}
	}

	@Override
	public void abort() {
		if (sinks.size() != 0) {
			for (TokenVisitor s : sinks) {
				s.abort();
			}
		}
	}
}
