package com.thoughtmechanix.organization.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by gongzhaopeng on 27/12/2017.
 */
@Configuration
public class ThreadLocalConfiguration {
    @Autowired(required = false)
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    @PostConstruct
    public void init() {
        HystrixPlugins hystrixPlugins = HystrixPlugins.getInstance();
        HystrixEventNotifier eventNotifier =
                hystrixPlugins.getEventNotifier();
        HystrixMetricsPublisher metricsPublisher =
                hystrixPlugins.getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy =
                hystrixPlugins.getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook =
                hystrixPlugins.getCommandExecutionHook();

        HystrixPlugins.reset();

        hystrixPlugins = HystrixPlugins.getInstance();
        hystrixPlugins.registerConcurrencyStrategy(
                new ThreadLocalAwareStrategy(existingConcurrencyStrategy));
        hystrixPlugins.registerEventNotifier(eventNotifier);
        hystrixPlugins.registerMetricsPublisher(metricsPublisher);
        hystrixPlugins.registerPropertiesStrategy(propertiesStrategy);
        hystrixPlugins.registerCommandExecutionHook(commandExecutionHook);
    }
}
