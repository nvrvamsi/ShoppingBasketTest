package ShoppingBasketTest;

import org.junit.Test;
import rx.observables.BlockingObservable;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShoppingCalculatorTest {

    @Test
    public void canCalculateThePriceOfABasketOfApples() {
        List<String> basketOfApples = Arrays.asList("Apple", "Apple", "Apple");
        ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfApples);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(105));
    }

    @Test
    public void canCalculateThePriceOfABasketOfBananas() {
        List<String> basketOfBananas = Arrays.asList("Banana", "Banana", "Banana");
        ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfBananas);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(60));
    }

    @Test
    public void canCalculateThePriceOfABasketOfMelons() {
        List<String> basketOfMelons = Arrays.asList("Melon", "Melon", "Melon");
        ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfMelons);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(100));
    }

    @Test
    public void canCalculateThePriceOfABasketOfLimes() {
        List<String> basketOfMelons = Arrays.asList("Lime", "Lime", "Lime", "Lime");
        ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfMelons);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(45));
    }

    @Test
    public void canCalculateThePriceOfAMixedBasketOfItems() {
        List<String> basket = Arrays.asList("Banana", "Banana", "Banana", // 60
                "Apple", "Apple", "Apple",  // 105
                "Melon", "Melon", "Melon",  // 100
                "Lime", "Lime", "Lime", "Lime"); // 45
        ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(310));
    }

    @Test
    public void canCalculateThePriceOfAnEmptyBasket() {
        List<String> basket = Arrays.asList();
        ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(0));
    }
}