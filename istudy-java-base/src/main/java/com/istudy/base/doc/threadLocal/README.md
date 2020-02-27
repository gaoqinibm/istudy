#ThreadLocal
    ThreadLocal提供一个线程（Thread）局部变量，访问到某个变量的每一个线程都拥有自己的局部变量。说白了，ThreadLocal就是想在多线程环境下去保证成员变量的安全。

##使用场景
    最常见的ThreadLocal使用场景为用来解决数据库连接、Session管理等

##存储结构key,value
    key 当前线程为key
    value 需要存储的数据为value(即副本)

##get,set,initialValue和remove
    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }

    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }

    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }

    public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
    }

##数据结构
    set需要首先获得当前线程对象Thread；
    然后取出当前线程对象的成员变量ThreadLocalMap；
    如果ThreadLocalMap存在，那么进行KEY/VALUE设置，KEY就是ThreadLocal；
    如果ThreadLocalMap没有，那么创建一个；
    说白了，当前线程中存在一个Map变量，KEY是ThreadLocal，VALUE是你设置的值。

