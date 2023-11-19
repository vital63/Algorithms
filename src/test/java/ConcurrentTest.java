import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConcurrentTest {

    @RepeatedTest(value = 100, name = "{currentRepetition}/{totalRepetitions}")
    @Execution(ExecutionMode.CONCURRENT)
    void testAtomic() {
        Int anInt = new Int();

        Callable<Void> task = () -> {
            for (int j = 0; j < 1000; j++) {
                anInt.inc();
            }
            return null;
        };
        List<Callable<Void>> tasks = Collections.nCopies(100, task);
        new ForkJoinPool(10).invokeAll(tasks);
        assertThat(anInt.getValue()).isEqualTo(100_000);
    }

    private static class Int {

        private final AtomicLong value = new AtomicLong(0);

        public void inc() {
            value.incrementAndGet();
        }

        public long getValue() {
            return value.get();
        }
    }

    @Test
    void parallelStreamBasic() {
        List<Integer> numbers = IntStream.range(1, 11).boxed().toList();
        numbers.parallelStream().forEach(i -> System.out.print(i + " "));
    }

    @Test
    void parallelStreamWithSpecifiedPool() {
        List<Integer> numbers = IntStream.range(1, 11).boxed().toList();

        ForkJoinPool pool = new ForkJoinPool(1);

        pool.submit(
                () -> numbers.parallelStream().forEach(i -> System.out.print(i + " "))
        ).join();
    }
}