package Lists;

public class Stack {
    Node top;

    public Stack() {

    }
    public Stack(Stack stack) {
        top = stack.top;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int key) {
        top = new Node(top, null, key);
    }

    public int pop() {
        int valor = top.getKey();
        top = top.getNext();
        return valor;
    }
}
