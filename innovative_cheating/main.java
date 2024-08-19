package innovative_cheating;

import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println(n + " , " + k);
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.next();
        }

        List<Character> list = new ArrayList();

        for (int i = 0; i < n; i++) {
            list.add('-');
        }

        Character[] array = (Character[]) list.toArray();

        RingedList<Character> result = new RingedList<>(array);

        for (int i = 0; i < n; i++) {
            Iterator<String> iterator = Arrays.stream(words).iterator();
            boolean foundAny = false;
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (result.canInsert(next, i)) {
                    result.insert(next, i);
                    iterator.remove();
                    foundAny = true;
                    break;
                }
            }
            if (!foundAny) {
                System.out.println("-1");
                return;
            }
        }
        int breaker = n;
        Node<Character> node = result.root;
        while (breaker != 0) {
            System.out.print(node.value);
            node = node.next;
            breaker--;
        }
    }

}

class RingedList<T> {
    Node<T> root;

    public RingedList(T[] list) {
        if (list.length == 0) {
            return;
        }
        root = new Node<>(list[0]);
        Node<T> curr = root;
        for (int i = 1; i < list.length; i++) {
            Node<T> next = new Node<>(list[i]);
            next.next = root;
            curr.next = next;
            curr = next;
        }
    }
}

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
