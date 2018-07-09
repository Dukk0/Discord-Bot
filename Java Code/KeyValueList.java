public class KeyValueList<E> {
    private KeyValueNode<E> first = null;
    private int size = 0;

    public KeyValueList() {
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean set(String var1, E var2) {
        int var3 = this.size;
        this.first = this.set(this.first, var1, var2);

        assert this.contains(var1) && this.get(var1).equals(var2);

        return this.size > var3;
    }

    private KeyValueNode<E> set(KeyValueNode<E> var1, String var2, E var3) {
        if (var1 == null) {
            var1 = new KeyValueNode(var2, var3);
            ++this.size;
        } else if (var1.key.equals(var2)) {
            var1.elem = var3;
        } else {
            var1.next = this.set(var1.next, var2, var3);
        }

        return var1;
    }

    public E get(String var1) {
        assert this.contains(var1) : "Key does not exist";

        return this.get(this.first, var1);
    }

    private E get(KeyValueNode<E> var1, String var2) {
        return var1.key.equals(var2) ? var1.elem : this.get(var1.next, var2);
    }

    public void remove(String var1) {
        assert this.contains(var1) : "Key does not exist";

        this.first = this.remove(this.first, var1);
        --this.size;

        assert !this.contains(var1) : "Key still exists";

    }

    private KeyValueNode<E> remove(KeyValueNode<E> var1, String var2) {
        if (var1.key.equals(var2)) {
            return var1.next;
        } else {
            var1.next = this.remove(var1.next, var2);
            return var1;
        }
    }

    public boolean contains(String var1) {
        return this.contains(this.first, var1);
    }

    private boolean contains(KeyValueNode<E> var1, String var2) {
        if (var1 == null) {
            return false;
        } else {
            return var1.key.equals(var2) ? true : this.contains(var1.next, var2);
        }
    }

    public void clear() {
        this.first = null;
        this.size = 0;
    }

    public String[] keys() {
        String[] var1 = new String[this.size];
        this.keys(this.first, 0, var1);
        return var1;
    }

    private void keys(KeyValueNode<E> var1, int var2, String[] var3) {
        if (var1 != null) {
            var3[var2] = var1.key;
            this.keys(var1.next, var2 + 1, var3);
        }
    }

    public String toString() {
        return this.toString("{", ", ", "}");
    }

    public String toString(String var1, String var2, String var3) {
        String var4 = "";
        String var5 = "";

        for(KeyValueNode var6 = this.first; var6 != null; var6 = var6.next) {
            var5 = var5 + var4 + var6.toString();
            var4 = var2;
        }

        return var1 + var5 + var3;
    }
}