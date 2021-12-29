package com.p.pc.java;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Person> queue = new ReferenceQueue<>();

        Person p = new Person(10);
        PersonReference pf = new PersonReference(p, null);
        if(pf.get() == null) System.out.println("Null");
        if(pf.isEnqueued()) {
            System.out.println("Enqueued before GC");
        } else System.out.println("Not enqueued before GC");
        p = null;
        System.gc(); // force GC
        Thread.sleep(2000);
        if(pf.isEnqueued()) {
            if(pf.get() == null) System.out.println("Null");
            System.out.println("Enqueued after GC");
            Reference<? extends Person> poll = queue.poll();
            System.out.println(poll.get());
            poll.clear();
        }
        if(!pf.isEnqueued()) {
            System.out.println("No longer enqueued");
        }
    }
}
class Person {
    int id;
    Person(int id) {this.id = id;}

    @Override
    public String toString() {
        return "com.p.pc.java.Person{" +
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
