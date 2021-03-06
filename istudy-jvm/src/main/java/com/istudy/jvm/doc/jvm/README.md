## JVM概述(JDK1.8)
![Alt text](../jvm/jvm运行时数据区.jpg)

## 堆
![Alt text](../jvm/堆结构.jpg)

    堆是JVM内存占用最大，管理最复杂的一个区域。其唯一的用途就是存放对象实例：几乎所有的对象实例及数组都在堆上进行分配。1.7后，字符串常量池从永久代中剥离出来，存放在堆中。堆有自己进一步的内存分块划分，按照GC分代收集角度的划分请参见上图。

### 堆空间内存分配（默认情况下）
    老年代 ： 三分之二的堆空间
    年轻代 ： 三分之一的堆空间
        eden区： 8/10 的年轻代空间
        survivor0 : 1/10 的年轻代空间
        survivor1 : 1/10 的年轻代空间

## 双亲委派模型      
    双亲委派模式的工作原理的是;如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行，如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器，如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是双亲委派模式，即每个儿子都不愿意干活，每次有活就丢给父亲去干，直到父亲说这件事我也干不了时，儿子自己想办法去完成，这不就是传说中的双亲委派模式.那么这种模式有什么作用呢?
    采用双亲委派模式的是好处是Java类随着它的类加载器一起具备了一种带有优先级的层次关系，通过这种层级关可以避免类的重复加载，当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次。其次是考虑到安全因素，java核心api中定义类型不会被随意替换，假设通过网络传递一个名为java.lang.Integer的类，通过双亲委托模式传递到启动类加载器，而启动类加载器在核心Java API发现这个名字的类，发现该类已被加载，并不会重新加载网络传递的过来的java.lang.Integer，而直接返回已加载过的Integer.class，这样便可以防止核心API库被随意篡改。
    这种双亲委派模式的好处，一个可以避免类的重复加载，另外也避免了java的核心API被篡改