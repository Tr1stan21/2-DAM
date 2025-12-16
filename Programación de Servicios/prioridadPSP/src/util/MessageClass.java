package util;

    public class MessageClass implements Comparable<MessageClass>{
    String text;
    int id;

    public MessageClass(String text, int priority) {
        this.text = text;
        this.id = priority;
    }

    @Override
    public int compareTo(MessageClass other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + text;
    }

}
