class KeyValueNode<E> {
    final String key;
    E elem;
    KeyValueNode<E> next;

    KeyValueNode(String var1, E var2, KeyValueNode<E> var3) {
        this.key = var1;
        this.elem = var2;
        this.next = var3;
    }

    KeyValueNode(String var1, E var2) {
        this.key = var1;
        this.elem = var2;
        this.next = null;
    }

    public String toString() {
        return "(" + this.key + ", " + this.elem.toString() + ")";
    }
}
