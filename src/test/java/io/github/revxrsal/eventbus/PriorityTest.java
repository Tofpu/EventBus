package io.github.revxrsal.eventbus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityTest {

    static class TestEvent {}

    static class Listeners {

        private final List<String> calls;

        Listeners(List<String> calls) {
            this.calls = calls;
        }

        @SubscribeEvent(priority = EventPriority.LOW)
        public void low(TestEvent e) {
            calls.add("low");
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public void high(TestEvent e) {
            calls.add("high");
        }

        @SubscribeEvent(priority = EventPriority.MONITOR)
        public void monitor(TestEvent e) {
            calls.add("monitor");
        }

        @SubscribeEvent // defaults to NORMAL
        public void normal(TestEvent e) {
            calls.add("normal");
        }
    }

    @Test
    public void testPriorityOrdering() {
        List<String> calls = new ArrayList<>();
        Listeners listeners = new Listeners(calls);

        EventBus bus = EventBusBuilder.reflection().build();
        bus.register(listeners);

        bus.post(new TestEvent()).join();

        // Expected ordering: HIGHEST,HIGH,NORMAL,LOW,LOWEST then MONITOR last.
        // We only registered HIGH, NORMAL, LOW and MONITOR, so expected: high, normal, low, monitor
        assertEquals(4, calls.size());
        assertEquals("high", calls.get(0));
        assertEquals("normal", calls.get(1));
        assertEquals("low", calls.get(2));
        assertEquals("monitor", calls.get(3));
    }
}
