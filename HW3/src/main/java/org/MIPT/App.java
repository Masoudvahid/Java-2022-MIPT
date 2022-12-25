package org.MIPT;

public final class App {
    private App() {
    }

    final static int SIZE = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
//        deadlock();
        livelock();
    }

    /**
     * This method causes deadlock. Different threads block each other and cannot make progress
     */
    public static void deadlock() {
        // YOUR CODE HERE
        //These are the two resource objects
        //we'll try to get locks for
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";
        //Here's the first thread.
        //It tries to lock resource1 then resource2
        Thread t1 = new Thread() {
            public void run() {
                //Lock resource 1
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");
                    //Pause for a bit, simulating some file I/O or
                    //something. Basically, we just want to give the
                    //other thread a chance to run. Threads and deadlock
                    //are asynchronous things, but we're trying to force
                    //deadlock to happen here...
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }

                    //Now wait 'till we can get a lock on resource 2
                    synchronized (resource2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };

        //Here's the second thread.
        //It tries to lock resource2 then resource1
        Thread t2 = new Thread() {
            public void run() {
                //This thread locks resource 2 right away
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");
                    //Then it pauses, for the same reason as the first
                    //thread does
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }

                    //Then it tries to lock resource1.
                    //But wait!  Thread 1 locked resource1, and
                    //won't release it till it gets a lock on resource2.
                    //This thread holds the lock on resource2, and won't
                    //release it till it gets resource1.
                    //We're at an impasse. Neither thread can run,
                    //and the program freezes up.
                    synchronized (resource1) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };

        //Start the two threads.
        //If all goes as planned, deadlock will occur,
        //and the program will never exit.
        t1.start();
        t2.start();

    }

    /**
     * This method causes livelock. Threads are not blocked but they are too busy responding to
     * requests of each other and cannot make progress because of that
     */
    public static void livelock() {
        // YOUR CODE HERE
        final Person husband = new Person("Husband", true);
        final Person wife = new Person("Wife", true);

        final CommonResource s = new CommonResource(husband);

        new Thread(() -> {
            husband.work(s, wife);
        }).start();

        new Thread(() -> {
            wife.work(s, husband);
        }).start();
    }
}

class CommonResource {
    private Person owner;

    public CommonResource(Person d) {
        owner = d;
    }

    public Person getOwner() {
        return owner;
    }

    public synchronized void setOwner(Person d) {
        owner = d;
    }
}

class Person {
    private String name;
    private boolean active;

    public Person(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(CommonResource commonResource, Person otherWorker) {
        while (active) {
            // wait for the resource to become available.
            if (commonResource.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    //ignore
                }
                continue;
            }

            // If other worker is also active let it do it's work first
            if (otherWorker.isActive()) {
                System.out.println(getName() +
                        " : You hangup " +
                        otherWorker.getName());
                commonResource.setOwner(otherWorker);
                continue;
            }

            //now use the commonResource
            System.out.println(getName() + ": working on the common resource");
            active = false;
            commonResource.setOwner(otherWorker);
        }
    }
}