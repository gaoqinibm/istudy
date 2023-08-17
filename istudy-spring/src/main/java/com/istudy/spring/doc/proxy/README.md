## JDK和CGLIB动态代理
    1、JDK动态代理
    利用拦截器(拦截器必须实现InvocationHanlder)加上反射机制生成一个实现代理接口的匿名类，
    在调用具体方法前调用InvokeHandler来处理。
    
    2、CGLIB动态代理
    利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
    
    3、何时使用JDK还是CGLIB？
    1）如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP。
    2）如果目标对象实现了接口，可以强制使用CGLIB实现AOP。
    3）如果目标对象没有实现了接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB之间转换。
    
    4、如何强制使用CGLIB实现AOP？
    1）添加CGLIB库(aspectjrt-xxx.jar、aspectjweaver-xxx.jar、cglib-nodep-xxx.jar)
    2）在Spring配置文件中加入<aop:aspectj-autoproxy proxy-target-class="true"/>
    
    5、JDK动态代理和CGLIB字节码生成的区别？
    1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类。
    2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法，
    并覆盖其中方法实现增强，但是因为采用的是继承，所以该类或方法最好不要声明成final，
    对于final类或方法，是无法继承的。
    
    6、CGlib比JDK快？
    1）使用CGLib实现动态代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，
    在jdk6之前比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，
    因为CGLib原理是动态生成被代理类的子类。
    2）在jdk6、jdk7、jdk8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高于CGLIB代理效率，
    只有当进行大量调用的时候，jdk6和jdk7比CGLIB代理效率低一点，但是到jdk8的时候，jdk代理效率高于CGLIB代理，
    总之，每一次jdk版本升级，jdk代理效率都得到提升，而CGLIB代理消息确有点跟不上步伐。
    
    7、Spring如何选择用JDK还是CGLIB？
    1）当Bean实现接口时，Spring就会用JDK的动态代理。
    2）当Bean没有实现接口时，Spring使用CGlib实现。
    3）可以强制使用CGlib（在spring配置中加入<aop:aspectj-autoproxy proxy-target-class="true"/>）。

## JDK和CGLIB动态代理总结
    JDK代理是不需要第三方库支持，只需要JDK环境就可以进行代理，使用条件:
    1）实现InvocationHandler
    2）使用Proxy.newProxyInstance产生代理对象
    3）被代理的对象必须要实现接口
    CGLib必须依赖于CGLib的类库，但是它需要类来实现任何接口代理的是指定的类生成一个子类，
    覆盖其中的方法，是一种继承但是针对接口编程的环境下推荐使用JDK的代理；

## Spring Boot到底是怎么做到自动配置的？
    Spring Boot 的自动配置是通过约定大于配置的原则来实现的，它主要有以下几个步骤：
    1.使用条件注解：Spring Boot 的自动配置类中使用了很多条件注解，比如 @ConditionalOnClass、@ConditionalOnProperty、@ConditionalOnMissingBean 等，这些注解根据特定的条件来判断是否需要启用某个自动配置类。
    2.扫描项目中的依赖：Spring Boot 会扫描项目的依赖，自动加载所需要的依赖项。
    3.使用默认值：如果没有找到任何配置信息，Spring Boot 会使用默认值来进行自动配置。
    4.提供 Starter 依赖：Spring Boot 还提供了 Starter 依赖，这些 Starter 依赖可以帮助我们快速集成各种常见的第三方库，比如 Spring Boot Starter Web、Spring Boot Starter Data JPA 等。
    通过以上几个步骤，Spring Boot 实现了自动配置的功能。它不仅可以减少开发人员的工作量，还可以提高项目的可维护性和可读性，使开发工作更加高效。

## @ConditionalOnMissingBean 注解的作用详解
    使用场景：引入redis-starter，容器中会有一个bean：RedisTemplate，如果自己再通过@Bean注入一个RedisTemplate，容器中最终会有几个RedisTemplate(1个)，如何实现(@ConditionOnMissingBean)
    @ConditionalOnMissingBean，它是修饰bean的一个注解，主要实现的是，当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，即你的实例只有一个，当你注册多个相同的bean时，会出现异常，以此来告诉人员。

## @Transactional注解是如何实现的
    @Transactional是spring中声明式事务管理的注解配置方式。@Transactional注解可以帮助我们把事务开启、提交或者回滚的操作，通过aop的方式进行管理。
    @Transactional原理是基于spring aop，aop又是动态代理模式的实现

