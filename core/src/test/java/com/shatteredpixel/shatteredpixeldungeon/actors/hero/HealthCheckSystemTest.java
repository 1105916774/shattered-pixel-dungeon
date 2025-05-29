package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCheckSystemTest {

    private HealthCheckStrategy_HealingPotion healingPotionStrategy;
    private HealthCheckStrategy_NoPotion noPotionStrategy;

    @BeforeEach
    void setUp() {
        healingPotionStrategy = new HealthCheckStrategy_HealingPotion();
        noPotionStrategy = new HealthCheckStrategy_NoPotion();
    }

    // ==================== Strategy Message Tests ====================

    @Test
    @DisplayName("Test strategy messages are correct")
    void testStrategyMessages() {
        assertEquals("Warning: Low health! Using healing potion.",
                healingPotionStrategy.getMessage());
        assertEquals("No healing potion",
                noPotionStrategy.getMessage());
    }

    @Test
    @DisplayName("Test strategy messages are not empty")
    void testStrategyMessagesNotEmpty() {
        assertFalse(healingPotionStrategy.getMessage().isEmpty());
        assertFalse(noPotionStrategy.getMessage().isEmpty());
    }

    // ==================== Factory Tests ====================

    @Test
    @DisplayName("Strategy Factory: Create default strategies")
    void testStrategyFactory() {
        List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();

        assertNotNull(strategies, "Strategy list should not be null");
        assertEquals(2, strategies.size(), "Should have 2 strategies by default");

        // Test that all strategies have messages
        for (HealthCheckStrategy strategy : strategies) {
            assertNotNull(strategy.getMessage(), "Strategy message should not be null");
            assertFalse(strategy.getMessage().isEmpty(), "Strategy message should not be empty");
        }
    }

    @Test
    @DisplayName("Strategy Factory: Verify strategy types by messages")
    void testStrategyFactoryTypes() {
        List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();

        boolean hasHealingStrategy = false;
        boolean hasNoPotionStrategy = false;

        for (HealthCheckStrategy strategy : strategies) {
            String message = strategy.getMessage();
            if (message.contains("healing potion") && message.contains("Warning")) {
                hasHealingStrategy = true;
            } else if (message.contains("No healing")) {
                hasNoPotionStrategy = true;
            }
        }

        assertTrue(hasHealingStrategy, "Should include healing potion strategy");
        assertTrue(hasNoPotionStrategy, "Should include no-potion strategy");
    }

    @Test
    @DisplayName("Strategy Factory: Multiple calls return equivalent strategies")
    void testStrategyFactoryConsistency() {
        List<HealthCheckStrategy> strategies1 = HealthCheckStrategyFactory.createDefaultStrategies();
        List<HealthCheckStrategy> strategies2 = HealthCheckStrategyFactory.createDefaultStrategies();

        assertEquals(strategies1.size(), strategies2.size(),
                "Multiple factory calls should return same number of strategies");

        // Verify messages are the same
        for (int i = 0; i < strategies1.size(); i++) {
            assertEquals(strategies1.get(i).getMessage(), strategies2.get(i).getMessage(),
                    "Strategies at same position should have same messages");
        }
    }

    // ==================== Context Tests ====================

    @Test
    @DisplayName("HealthCheckContext: Basic creation")
    void testHealthCheckContextCreation() {
        List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();
        HealthCheckContext context = new HealthCheckContext(strategies);

        assertNotNull(context, "Context should be created successfully");
    }

    @Test
    @DisplayName("HealthCheckContext: Add strategy functionality")
    void testHealthCheckContextAddStrategy() {
        List<HealthCheckStrategy> strategies = new ArrayList<>();
        HealthCheckContext context = new HealthCheckContext(strategies);

        // Add strategies
        assertDoesNotThrow(() -> context.addStrategy(healingPotionStrategy),
                "Adding healing potion strategy should not throw exception");
        assertDoesNotThrow(() -> context.addStrategy(noPotionStrategy),
                "Adding no potion strategy should not throw exception");
    }

    @Test
    @DisplayName("HealthCheckContext: Handle empty strategy list")
    void testHealthCheckContextEmptyList() {
        List<HealthCheckStrategy> emptyStrategies = new ArrayList<>();
        HealthCheckContext context = new HealthCheckContext(emptyStrategies);

        assertNotNull(context, "Context with empty strategy list should be created");
    }

    @Test
    @DisplayName("HealthCheckContext: Multiple strategies creation")
    void testHealthCheckContextMultipleStrategies() {
        List<HealthCheckStrategy> strategies = new ArrayList<>();
        strategies.add(healingPotionStrategy);
        strategies.add(noPotionStrategy);

        HealthCheckContext context = new HealthCheckContext(strategies);
        assertNotNull(context, "Context with multiple strategies should be created");
    }

    // ==================== Strategy Interface Compliance Tests ====================

    @Test
    @DisplayName("All strategies implement interface correctly")
    void testStrategyInterfaceCompliance() {
        List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();

        for (HealthCheckStrategy strategy : strategies) {
            // Test that all interface methods are implemented
            assertNotNull(strategy.getMessage(), "getMessage should return non-null");
            assertFalse(strategy.getMessage().isEmpty(), "getMessage should return non-empty string");
        }
    }

    @Test
    @DisplayName("Individual strategies implement interface correctly")
    void testIndividualStrategyCompliance() {
        // Test healing potion strategy
        assertNotNull(healingPotionStrategy.getMessage());
        assertFalse(healingPotionStrategy.getMessage().isEmpty());

        // Test no potion strategy
        assertNotNull(noPotionStrategy.getMessage());
        assertFalse(noPotionStrategy.getMessage().isEmpty());
    }

    // ==================== Performance Tests ====================

    @Test
    @DisplayName("Performance: Factory should create strategies quickly")
    void testFactoryPerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();
            assertNotNull(strategies);
            assertEquals(2, strategies.size());
        }

        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) < 1000,
                "Creating 1000 strategy lists should take less than 1 second");
    }

    @Test
    @DisplayName("Performance: Context creation should be fast")
    void testContextCreationPerformance() {
        List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            HealthCheckContext context = new HealthCheckContext(new ArrayList<>(strategies));
            assertNotNull(context);
        }

        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) < 1000,
                "Creating 1000 contexts should take less than 1 second");
    }

    @Test
    @DisplayName("Performance: Strategy creation should be fast")
    void testStrategyCreationPerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            HealthCheckStrategy_HealingPotion healing = new HealthCheckStrategy_HealingPotion();
            HealthCheckStrategy_NoPotion noPotion = new HealthCheckStrategy_NoPotion();

            assertNotNull(healing);
            assertNotNull(noPotion);
        }

        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) < 1000,
                "Creating 1000 strategy instances should take less than 1 second");
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Complete workflow: Factory -> Context creation")
    void testCompleteWorkflow() {
        assertDoesNotThrow(() -> {
            // Create strategies using factory
            List<HealthCheckStrategy> strategies = HealthCheckStrategyFactory.createDefaultStrategies();

            // Create context with strategies
            HealthCheckContext context = new HealthCheckContext(strategies);

            // Add additional strategy
            context.addStrategy(new HealthCheckStrategy_HealingPotion());

            // All should work without throwing exceptions
        }, "Complete workflow should execute without exceptions");
    }

    @Test
    @DisplayName("Mixed strategy sources integration")
    void testMixedStrategySources() {
        List<HealthCheckStrategy> mixedStrategies = new ArrayList<>();

        // Add factory-created strategies
        mixedStrategies.addAll(HealthCheckStrategyFactory.createDefaultStrategies());

        // Add manually created strategies
        mixedStrategies.add(new HealthCheckStrategy_HealingPotion());
        mixedStrategies.add(new HealthCheckStrategy_NoPotion());

        HealthCheckContext context = new HealthCheckContext(mixedStrategies);
        assertNotNull(context, "Context should handle mixed strategy sources");

        assertEquals(4, mixedStrategies.size(), "Should have 4 strategies total");
    }
}