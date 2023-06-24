package sync_async;

import java.util.Iterator;
import java.util.function.Consumer;

public class SyncBlockingTest {
    static class WorkerA {
        Consumer<String> workForA = (message) -> {
            for (int index = 0; index < 5; index++) {
                for (int subIndex = 0; subIndex < Integer.MAX_VALUE; subIndex++) {
                }
                System.out.println("A: doing something.");
            }
            System.out.println("A: " + message);
        };
        Consumer<String> workForB = (message) -> {
            for (int index = 0; index < 5; index++) {
                for (int subIndex = 0; subIndex < Integer.MAX_VALUE; subIndex++) {
                }
                System.out.println("B: doing something.");
            }
            System.out.println("B: " + message);
        };

        void doMyWork() {
            workForA.accept("I'm worker A. And i'm done");
        }

        Consumer<String> giveWorkToB() {
            return workForB;
        }

        static class WorkerB {
            void takeMyWorkAndDoMyWork(Consumer<String> myWork) {
                myWork.accept("I'm worker B. And i'm done");
            }
        }

        public static void main(String[] args) {
            WorkerA a = new WorkerA();
            WorkerB b = new WorkerB();

            b.takeMyWorkAndDoMyWork(a.giveWorkToB());
            a.doMyWork();
        }
    }
}
