package io.github.revxrsal.eventbus;

import org.junit.jupiter.api.Test;

public class EventBusAsmTest {
    private final EventBus eventBus = EventBusBuilder.asm()
            .build();

    @Test
    void submittingIdenticalEventButDifferentPackage() {
        eventBus.submit(API.UserRegisteredEvent.class);
        eventBus.submit(Internal.UserRegisteredEvent.class);
    }

    interface API {
        interface UserRegisteredEvent {

        }
    }

    interface Internal {
        interface UserRegisteredEvent {

        }
    }
}
