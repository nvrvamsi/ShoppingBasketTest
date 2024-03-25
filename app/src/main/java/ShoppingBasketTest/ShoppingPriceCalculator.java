package ShoppingBasketTest;

import rx.Observable;

import java.util.List;

/**
 * Calculates the Price of a basket of items.
 */
public class ShoppingPriceCalculator {
    public static final int APPLE_PRICE_EACH = 35;
    public static final int BANANA_PRICE_EACH = 20;
    public static final int MELON_PRICE_EACH = 50;
    public static final int LIME_PRICE_EACH = 15;

    private final Observable<String> basketObservable;
    private final Observable<Integer> totalPriceObservable;

    public ShoppingPriceCalculator(List<String> items) {
        basketObservable = Observable.from(items);
        totalPriceObservable = Observable.merge(applePriceEach(), bananaPriceEach(), melonPriceOffer(), limePriceOffer())
                .scan(0, this::sum);
    }

    public Observable<Integer> totalPrice() {
        return totalPriceObservable;
    }

    private Observable<Integer> applePriceEach() {
        return basketObservable.filter("Apple"::equals).map((item) -> APPLE_PRICE_EACH);
    }

    private Observable<Integer> bananaPriceEach() {
        return basketObservable.filter("Banana"::equals).map((item) -> BANANA_PRICE_EACH);
    }

    private Observable<Integer> melonPriceOffer() {
        return basketObservable.filter("Melon"::equals).buffer(2).map((eachOffer) -> MELON_PRICE_EACH);
    }

    private Observable<Integer> limePriceOffer() {
        return basketObservable.filter("Lime"::equals).buffer(3)
                .map((eachOffer) -> eachOffer.size() == 3 ? LIME_PRICE_EACH * 2 : LIME_PRICE_EACH * eachOffer.size());
    }

    private Integer sum(Integer acc, Integer next) {
        return acc + next;
    }
}