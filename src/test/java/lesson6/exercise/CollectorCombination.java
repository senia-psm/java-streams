package lesson6.exercise;

import lesson5.exercise.CollectorsExercise;
import lesson5.exercise.CollectorsExercise.Key;
import lesson5.exercise.CollectorsExercise.Value;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorCombination {

    private static class Pair<A, B> {
        private final A a;
        private final B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return a;
        }

        public B getB() {
            return b;
        }
    }

    private static <T, R1, R2> Collector<T, Pair<?, ?>, Pair<R1, R2>> paired(Collector<T, ?, R1> c1,
                                                                             Collector<T, ?, R2> c2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Test
    public void collectKeyValueMap() {
        // TODO see CollectorsExercise::collectKeyValueMap
        // В 1 проход в 2 Map с использованием MapPair и mapMerger
        // final MapPair res2 = pairs.stream()
        //        .collect(new Collector<Pair, MapPair, MapPair>() {

        // Перепишите решение в слещующем виде:
        final List<CollectorsExercise.Pair> pairs = CollectorsExercise.generatePairs(10, 100);

        final Pair<Map<String, Key>, Map<String, List<Value>>> res2 = pairs.stream()
                .collect(
                        paired(
                                mapping(CollectorsExercise.Pair::getKey, toMap(Key::getId, Function.identity(), (x, y) -> x)),
                                mapping(CollectorsExercise.Pair::getValue, groupingBy(Value::getKeyId, toList()))
                        )
                );


        // TODO tests
        throw new UnsupportedOperationException();
    }

}
