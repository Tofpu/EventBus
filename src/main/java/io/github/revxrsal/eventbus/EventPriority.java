package io.github.revxrsal.eventbus;

/**
 * Priority levels for event listeners.
 *
 * Order of invocation:
 * - HIGHEST -> HIGH -> NORMAL -> LOW -> LOWEST
 * - MONITOR is always invoked last and should not modify event state.
 */
public enum EventPriority {
    LOWEST,
    LOW,
    NORMAL,
    HIGH,
    HIGHEST,
    MONITOR
}
