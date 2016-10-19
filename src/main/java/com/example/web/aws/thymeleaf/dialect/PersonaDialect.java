package com.example.web.aws.thymeleaf.dialect;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import com.example.web.aws.thymeleaf.processor.element.PersonaBlockElementProcessor;
import com.example.web.aws.thymeleaf.processor.element.PersonaIfElementProcessor;
import com.example.web.aws.thymeleaf.processor.element.PersonaLabelElementProcessor;

public class PersonaDialect extends AbstractDialect {
	private static final String PREFIX = "persona";
	private static final Set<IProcessor> processors = new HashSet<IProcessor>();
	static {
		processors.add(PersonaLabelElementProcessor.create());
		processors.add(PersonaIfElementProcessor.create());
		processors.add(PersonaBlockElementProcessor.create());
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}

	@Override
	public boolean isLenient() {
		return false;
	}

	@Override
	public Set<IProcessor> getProcessors() {
		return Collections.unmodifiableSet(processors);
	}

}
