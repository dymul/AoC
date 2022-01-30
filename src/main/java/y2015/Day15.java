package y2015;

import lib.Day;
import lib.Split;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        Map<String, Ingredient> ingredients = input.stream().map(Ingredient::of).collect(Collectors.toMap(i -> i.name, i -> i));
        Map<String, Integer> mix = new HashMap<>();
        ingredients.forEach((k, v) -> mix.put(k, 1));
        calculateBestMix(ingredients, mix);
        return "" + score(mix, ingredients);
    }

    private void calculateBestMix(Map<String, Ingredient> ingredients, Map<String, Integer> mix) {
        while(mix.values().stream().mapToInt(i -> i).sum() < 100) {
            String newIngredient = null;
            long max = score(mix, ingredients);
            for (String ing : ingredients.keySet()) {
                Map<String, Integer> newMix = new HashMap<>(mix);
                newMix.compute(ing, (k, v) -> v +1 );
                long currScore = score(newMix, ingredients);
                if (currScore > max) {
                    max = currScore;
                    newIngredient = ing;
                }
            }
            if (newIngredient != null) {
                mix.compute(newIngredient, (k,v) -> v+1);
            } else {
                throw new RuntimeException("fuckup :(");
            }
        }
    }

    private long score(Map<String, Integer> mix, Map<String, Ingredient> ingredients) {
        int capacity = mix.entrySet().stream().map(entry -> ingredients.get(entry.getKey()).capacity * entry.getValue()).mapToInt(i -> i).sum();
        int durability = mix.entrySet().stream().map(entry -> ingredients.get(entry.getKey()).durability * entry.getValue()).mapToInt(i -> i).sum();
        int flavor = mix.entrySet().stream().map(entry -> ingredients.get(entry.getKey()).flavor * entry.getValue()).mapToInt(i -> i).sum();
        int texture = mix.entrySet().stream().map(entry -> ingredients.get(entry.getKey()).texture * entry.getValue()).mapToInt(i -> i).sum();
        if (capacity <= 0 || durability <= 0 || flavor <= 0 || texture <= 0) return 0;
        return ((long)capacity)*durability*flavor*texture;
    }

    private int calories(Map<String, Integer> mix, Map<String, Ingredient> ingredients) {
        return mix.entrySet().stream().map(entry -> ingredients.get(entry.getKey()).calories * entry.getValue()).mapToInt(i -> i).sum();
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        Map<String, Ingredient> ingredients = input.stream().map(Ingredient::of).collect(Collectors.toMap(i -> i.name, i -> i));
        Map<String, Integer> mix = new HashMap<>();
        List<Map<String, Integer>> combinations = getCombinations(mix, new LinkedList<>(ingredients.keySet()), ingredients, 500);

        return "" + combinations.stream().mapToLong(map -> score(map, ingredients)).max().getAsLong();
    }

    private List<Map<String, Integer>> getCombinations(Map<String, Integer> mix, List<String> ingredientsLeft,
                                                       Map<String, Ingredient> ingredients, int caloriesLeft) {

        int spoonsLeft = 100 - mix.values().stream().mapToInt(i -> i).sum();
        List<String> newIngredientsLeft = new LinkedList<>(ingredientsLeft);
        String currentIngredient = newIngredientsLeft.remove(0);
        Map<String, Integer> newMix = new HashMap<>(mix);
        List<Map<String, Integer>> result = new LinkedList<>();
        if (newIngredientsLeft.isEmpty()) {
            if (caloriesLeft == ingredients.get(currentIngredient).calories*spoonsLeft) {

                newMix.put(currentIngredient, spoonsLeft);
                result.add(newMix);
            }
            return result;
        } else {
            for (int i = 1; i < spoonsLeft - newIngredientsLeft.size(); i++) {
                newMix.put(currentIngredient, i);
                int caloriesOfCurrentMix = calories(newMix, ingredients);
                if (caloriesOfCurrentMix > 500) {
                    continue;
                }
                List<Map<String, Integer>> combinations = getCombinations(newMix, newIngredientsLeft, ingredients, 500 - caloriesOfCurrentMix);
                result.addAll(combinations);
            }
        }
        return result;
    }

}

class Ingredient {

    int capacity;
    int durability;
    int flavor;
    int texture;
    int calories;
    String name;

    Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;

    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "capacity=" + capacity +
                ", durability=" + durability +
                ", flavor=" + flavor +
                ", texture=" + texture +
                ", calories=" + calories +
                ", name='" + name + '\'' +
                '}';
    }

    static Ingredient of(String in) {
        Split nameAndParams = new Split(in, ": ");
        String name = nameAndParams.get(0);
        Split params = new Split(nameAndParams.get(1).replace("capacity ", "")
                .replace(", durability", "")
                .replace(", flavor", "")
                .replace(", texture", "")
                .replace(", calories", ""), " ");
        return  new Ingredient(name, params.getAsInt(0), params.getAsInt(1), params.getAsInt(2), params.getAsInt(3), params.getAsInt(4));
    }
}
