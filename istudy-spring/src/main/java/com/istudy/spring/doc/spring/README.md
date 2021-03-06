## spring中BeanFactory和FactoryBean的区别
    BeanFactory是个Factory，也就是IOC容器或对象工厂，
    FactoryBean是个Bean。
    在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的。但对FactoryBean而言，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,它的实现与设计模式中的工厂模式和修饰器模式类似

## spring中的前置处理器和后置处理器区别
    前置处理器
    BeanFactoryPostProcess
    前置：实例化对象之前
    这个机制允许我们在实例化相应对象之前对注册到容器中的BeanDefinition的存储信息进行修改。可以根据这个机制对Bean增加其它信息。修改Bean定义的某些属性值。
    想自定义前置处理器需要实现BeanFactoryPostProcess接口。当一个容器存在多种前置处理的时候，可以让前置处理器的实现类同时继承Ordered接口。
    Spring容器提供了数种现成的前置处理器，常见的如：
    PropertyPlaceholderConfigurer：允许在xml文件中使用占位符。将占位符代表的资源单独配置到简单的Properties文件中加载
    PropertyOverrideConfigurer：不同于PropertyPlaceholderConfigurer的是，该类用于处理容器中的默认值覆为新值的场景
    CustomEditorConfigurer：此前的两个前置处理器处理的均是BeanDefinition.通过把BeanDefinition的数据修改达到目的。CustomEditorConfigurer没有对BeanDefinition做任何变动。负责的是将后期会用到的信息注册到容器之中。例如将类型转换器注册到BeanDefinition中。供BeanDefinition将获取到的String类型参数转换为需要的类型。

    后置处理器
    BeanPostProcessor
    后置：实例化对象之后
    实例后的对象，初始化之前BeanBeforePostProcessor
    实例后的对象，初始化之后BeanBeforePostProcessor
    
    实例化和初始化的区别？
    1、实例化----实例化的过程是一个创建Bean的过程，即调用Bean的构造函数，单例的Bean放入单例池中。
    2、初始化----初始化的过程是一个赋值的过程，即调用Bean的setter，设置Bean的属性。
    
## Spring Bean的生命周期
    Spring Bean的生命周期分为四个阶段和多个扩展点。扩展点又可以分为影响多个Bean和影响单个Bean。整理如下：
    四个阶段
    实例化 Instantiation
    属性赋值 Populate
    初始化 Initialization
    销毁 Destruction
    
>> 多个扩展点
    
    影响多个Bean
    BeanPostProcessor
    InstantiationAwareBeanPostProcessor
    
    影响单个Bean
    Aware
    Aware Group1
    BeanNameAware
    BeanClassLoaderAware
    BeanFactoryAware
    Aware Group2
    EnvironmentAware
    EmbeddedValueResolverAware
    ApplicationContextAware(ResourceLoaderAware\ApplicationEventPublisherAware\MessageSourceAware)
    
    生命周期
    InitializingBean
    DisposableBean

## 循环依赖