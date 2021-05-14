import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Person> queue = new ReferenceQueue<>();

        Person p = new Person(10);
        PersonReference pf = new PersonReference(p, queue);
        if(pf.get() == null) System.out.println("Null");
        p = null;
        System.gc();
        Thread.sleep(2000);
        if(pf.isEnqueued()) System.out.println("Enqueued");
        Reference<? extends Person> poll = queue.poll();
        poll.clear();
    }
}
class Person {
    int id;
    Person(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }

    public void clean() {
        System.out.println("Cleaning up");
    }
}

class PersonReference extends PhantomReference<Person> {
    /**
     * Creates a new phantom reference that refers to the given object and
     * is registered with the given queue.
     *
     * <p> It is possible to create a phantom reference with a <tt>null</tt>
     * queue, but such a reference is completely useless: Its <tt>get</tt>
     * method will always return null and, since it does not have a queue, it
     * will never be enqueued.
     *
     * @param referent the object the new phantom reference will refer to
     * @param q        the queue with which the reference is to be registered,
     */
    public PersonReference(Person referent, ReferenceQueue<? super Person> q) {
        super(referent, q);
    }
}
