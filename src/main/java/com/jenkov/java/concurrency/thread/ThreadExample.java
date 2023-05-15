package thread;

public class ThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread.getName());//Thread-0
        thread.start();
    }
}
