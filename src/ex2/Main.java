package ex2;

class MyNode {
    MyNode previous, next;
    String realKey;
    String value;

    MyNode(String realKey, String value) {
        this.realKey = realKey;
        this.value = value;
        this.previous = this.next = this;
    }

    void put(MyNode x) {
        String end = x.realKey;
        MyNode cur = this.next;
        while ((!cur.realKey.equals(this.value)) && (!cur.realKey.equals(x.realKey))) {
            cur = cur.next;
        }
        if (cur.realKey.equals(x.realKey)) {
            cur.value = x.value;
        } else {
            cur = this.next;
            cur.previous = x;
            x.next = cur;
            this.next = x;
            x.previous = this;
        }
    }

    String find(String key) {
        MyNode cur = this.next;
        while ((!cur.realKey.equals(this.value)) && (!cur.realKey.equals(key))) {
            cur = cur.next;
        }
        if (cur.realKey.equals(key)) {
            return cur.value;
        } else {
            return null;
        }
    }
}

class MyHashMap {
    public static final int MAX_POOL = 1000;
    MyNode[] pool = new MyNode[MAX_POOL];

    void put(String key, String value) {
        int index = key.hashCode() % MAX_POOL;
        if (pool[index] == null) {
            pool[index] = new MyNode(key, value);
        } else {
            pool[index].put(new MyNode(key, value));
        }
    }

    String get(String key) {
        int index = key.hashCode() % MAX_POOL;
        if (pool[index] == null) return null;
        else {
            return pool[index].find(key);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        boolean flag = true;
        for (int i = 0; i < MAX_POOL; i++) {
            if (pool[i] == null) continue;
            else {
                MyNode root = pool[i].next;
                if (flag) {
                    flag = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(root.realKey + ":" + root.value);
                root = root.next;
                while (!root.realKey.equals(pool[i].realKey)) {
                    if (flag) {
                        flag = false;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(root.realKey + ":" + root.value);
                    root = root.next;
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

public class Main {
    Main() {
        MyHashMap m = new MyHashMap();
        m.put("dog", "Bosco");
        m.put("dog", "Spot");
        m.put("cat", "Rags");
        //m.put("cat", "POOOL");
        System.out.println(m);
    }

    public static void main(String[] args) {
        new Main();
    }
}
