public class SingleClassExample {
    private volatile static SingleClassExample instance;

    public static SingleClassExample getInstance() {
        if (instance == null) {
            synchronized (SingleClassExample.class) {
                if (instance == null) {
                    instance = new SingleClassExample();
                }
                return instance;
            }
        }
        return instance;
    }

}
