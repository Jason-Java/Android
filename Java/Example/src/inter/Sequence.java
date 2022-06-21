package inter;

public class Sequence {
    private Object[] o;
    private int next = 0;

    public Sequence(int size) {
        o = new Object[size];
    }

    public void add(Object x) {
        if (next < o.length) {
            o[next] = x;
            next++;
        }
    }

    public  class SSelector implements Selector {
        int i = 0;

        public boolean end() {
            return i == o.length;
        }

        public Object current() {
            return o[i];
        }

        public void next() {
            if (i < o.length) i++;

        }
    }

    public SSelector getSelector() {
        return new SSelector();
    }
}

