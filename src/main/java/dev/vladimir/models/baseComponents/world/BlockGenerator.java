package dev.vladimir.models.baseComponents.world;

import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Block;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BlockGenerator {
    private static HashMap<Probability, Block> blocks;

    public static void init() {
        blocks = new LinkedHashMap<>();

        blocks.put(new Probability(0.01), GameObjectType.DIAMOND_ORE.getBlock());
        blocks.put(new Probability(0.05), GameObjectType.GOLD_ORE.getBlock());
        blocks.put(new Probability(0.70), GameObjectType.COBBLESTONE.getBlock());
    }

    public static Block getRandomBlock() {
        return blocks.get(
                Probability.of(Math.random())
        );
    }

    static class Probability {
        public double probability;
        private Probability(double probability) {
            this.probability = probability;
        }

        public static Probability of(double random) {
            Probability lastProb = null;
            for (Probability probability : blocks.keySet()) {
                if(random <= probability.probability) {
                    return probability;
                }

                lastProb = probability;
            }

            return lastProb;
        }
    }
}
