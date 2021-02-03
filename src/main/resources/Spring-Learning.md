Inversion of Control is a principle in software engineering by which the control of objects or portions of a program is transferred to a container or framework. By contrast with traditional programming, in which 
our custom code makes calls to a library, IoC enables a framework to take control of the flow of a program and make calls to our custom code.

An IoC container creates, configures, and connects objects while also managing their lifecycle. The container gets instructions on these areas from configuration metadata given by the user. Dependency injection (DI)
is a concept that defines how multiple classes should be connected. This is one example of Inversion of Control. You don’t need to connect services and components explicitly in code when using dependency injection.

Benefits of Spring :- 
1. Spring is lightweight in resource use, with the basic Spring Framework only costing 2MB of storage.
2. Spring’s transaction management interface can scale to either a local transaction on a single database to global transactions using the JTA module
3. Exception handling is easy thanks to plentiful API resources for handling exceptions in each module.
4. Achieves loose coupling via IOC by allowing objects to give their dependencies to other objects rather without dependent objects.
5. Spring supports Aspect-oriented programming, a paradigm that separates application business logic from system services.


Spring supports five scopes for beans: 
1. Singleton: Scopes a bean definition to be restricted to a single instance per Spring IoC container
2. Prototype: Scopes a single bean to enable any number of instances.
3. Request: Scopes a bean definition to a single HTTP request within an ApplicationContext
4. Session: Scopes a bean definition to an HTTP session within an ApplicationContext
5. Global-session: Scopes a bean definition to a Global HTTP

Bean lifecycle :- There are seven steps to the Bean lifecycle:
1. Instantiate: The bean is instantiated by the Spring container using the bean’s definition found in the XML configuration file.
2. Populate properties: Spring populates all the defined properties from the XML file using dependency injection.
3. Set Bean Name: Spring passes the bean’s identifier to the setBeanName() method If the bean uses the BeanNameAware interface.
4. Set Bean factory: Spring passes the beanfactory to the setBeanFactory() method if the bean is configured to use the BeanFactoryAware interface.
5. Pre Initialization: Spring calls any BeanPostProcessors associated with the bean using the postProcessorBeforeInitialization() method.
6. Initialization: The bean is then initialized. Any special initialization process specified in the init-method is followed.
7. Post Initialization: All defined postProcessAfterInitialization() methods are called. Now the bean is complete. Beans that implement DisposableBean will be deleted using the destroy() after their job is finished.


Joinpoints represent any point in a program where an action is taken. Examples of a joinpoint include when handling an exception or a method is executed. When using AOP, only method executions are joinpoints.

An Advice is the action taken at a given joinpoint. AOP uses an Advice as an interceptor before the method’s execution is complete.
Before: These are advices that execute before the joinpoint methods. They’re marked using the @Before annotation mark.
After returning: These execute after the joinpoint’s method completes executing without issue. They’re marked using the @AfterReturning annotation mark.
After throwing: These execute only if the joinpoint method ends by throwing an exception. They’re marked using the @AfterThrowing annotation mark.
After: These execute after a joinpoint method, regardless of how it completes. They’re marked using the @After annotation mark.
Around: These execute before and after a joinpoint and are marked using the @Around annotation mark.

Data Access Object (DAO) support is a set of tools that make it easier to work with data access technologies like Hibernate and JDO with improved consistency.

The 3 main parts of MVC are:
1. DispatcherServlet: This part of MVC manages all the HTTP requests, and responses that interact with the program. The DispatcherServlet first receives relevant handler 
    mapping from the configuration file and then passes off the request to the controller.
2. WebApplicationContext: This acts as an extension of the plain ApplicationContext with extra features necessary for web applications. It can uniquely resolve themes
    and automatically decide which servlet it is associated with.
3. Controllers: These are beans within the DispatcherServlet that act as filters between user input and application response. Controllers take user input, decide 
    if it should be transformed into either a View or a Model, and finally returns the transformed input to the View Resolver for review.
    
DispatcherServlet :- 
1. Handler Mapping: An interface that defines the mapping between handler and request objects. Can be used to create a custom mapping strategy.
2. Controller: Determines the app’s response to user input by sorting input requests by their desired outcome. Input is either immediately returned 
    with a View or is transformed into a Model before being passed to the view-resolver.
3. View-Resolver: Takes and renders Models from the controller by mapping between View names and actual Views.

Spring Boot :- t is a project built on top of Spring to simplify the task of deploying Java applications. Its two major components are the Spring Framework and Embedded 
    HTTP Servers.
    
Configuration Annotation :- Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and
    service requests for those beans at runtime.
Spring Boot auto-configuration :- It attempts to automatically configure your Spring application based on the jar dependencies that you have added. For example, 
    if HSQLDB is on your classpath, and you have not manually configured any database connection beans, then Spring Boot auto-configures an in-memory database. 
Component-scan :- Configures component scanning directives for use with @Configuration classes. 
SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes. 

Spring Actuator :-  It includes a number of additional features that help us to monitor and manage the Spring Boot application. Enabling/disabling the 
    actuator is easy; the simplest way is to enable features to add the dependency (Maven/Gradle) to the spring-boot-starter-actuator, i.e. Starter.
    
Que :- Can we override or replace the Embedded Tomcat server in Spring Boot? 
Ans :- Yes, we can replace the Embedded Tomcat with any other servers by using the Starter dependencies. You can use spring-boot-starter-jetty 
    or spring-boot-starter-undertow as a dependency for each project as you need.

