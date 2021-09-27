package com.example;

import java.util.Arrays;

import org.rribbit.DefaultRequestResponseBus;
import org.rribbit.RRB;
import org.rribbit.RequestResponseBus;
import org.rribbit.creation.ListenerObjectCreator;
import org.rribbit.creation.SpringBeanClassBasedListenerObjectCreator;
import org.rribbit.dispatching.LocalRequestDispatcher;
import org.rribbit.dispatching.RequestDispatcher;
import org.rribbit.execution.ListenerObjectExecutor;
import org.rribbit.execution.MultiThreadedListenerObjectExecutor;
import org.rribbit.processing.LocalRequestProcessor;
import org.rribbit.retrieval.CachedListenerObjectRetriever;
import org.rribbit.retrieval.ListenerObjectRetriever;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListenerObjectCreator creator() {
		SpringBeanClassBasedListenerObjectCreator creator = new SpringBeanClassBasedListenerObjectCreator();
		creator.setPackageNames(Arrays.asList("com.example"));
		// creator.setScanSubpackages(true);
		return creator;
	}

	@Bean
	public RequestResponseBus requestResponseBus(ListenerObjectCreator creator) {
		ListenerObjectRetriever listenerObjectRetriever = new CachedListenerObjectRetriever(creator);
		ListenerObjectExecutor listenerObjectExecutor = new MultiThreadedListenerObjectExecutor();
		LocalRequestProcessor localRequestProcessor = new LocalRequestProcessor(listenerObjectRetriever,
				listenerObjectExecutor);
		RequestDispatcher requestDispatcher = new LocalRequestDispatcher(localRequestProcessor);
		RequestResponseBus requestResponseBus = new DefaultRequestResponseBus(requestDispatcher);
		RRB.setRequestResponseBus(requestResponseBus);
		return requestResponseBus;
	}

}
