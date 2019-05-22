import java.util.*;

/** Class representing cart for each user */
public class Cart {
    private List<MainProduct> mainProducts = new LinkedList<>();
    private List<AdditionalProduct> additionalProducts = new LinkedList<>();

    /** Clears the cart and proceeds to check out */
    public void checkOut() {
        mainProducts.clear();
        additionalProducts.clear();
    }

    /** Returns main products in cart */
    public Collection<MainProduct> getMainProducts() {
        return mainProducts;
    }

    /** Returns additional products in cart */
    public Collection<AdditionalProduct> getAdditionalProducts() {
        return additionalProducts;
    }

    /** Returns all products in cart */
    public Collection<Product> getProducts() {
        Collection<Product> result = new LinkedList<>(mainProducts);
        result.addAll(additionalProducts);
        return result;
    }

    /**
     * Add product to cart.
     * Checks if product is addition and cart already has compatible main for it.
     * @param product product should be added
     * @return <tt>true</tt> if insertion is completed, <tt>false</tt> otherwise.
     */
    public boolean add(Product product) {
        if (product instanceof MainProduct) {
            mainProducts.add((MainProduct) product);
            return true;
        } else {
            Set<MainProduct> compatible = new HashSet<>(((AdditionalProduct) product).getCompatibleMains());
            compatible.retainAll(mainProducts);
            if (compatible.isEmpty()) {
                return false;
            } else {
                additionalProducts.add((AdditionalProduct) product);
                return true;
            }
        }
    }

    /**
     * Removes product from cart.
     * Checks if product is main and removes all it compatible additions
     * if they are not compatible to anything else.
     * @param product product should be removed
     * @return <tt>true</tt> if removing is completed, <tt>false</tt> otherwise.
     */
    public boolean remove(Product product) {
        if (product instanceof AdditionalProduct) {
            return additionalProducts.remove(product);
        } else {
            Set<AdditionalProduct> compatible = new HashSet<>(((MainProduct) product).getCompatibleAdditions());
            compatible.retainAll(additionalProducts);
            boolean result = mainProducts.remove(product);
            for (AdditionalProduct compatibleProduct : compatible) {
                Set<MainProduct> mains = new HashSet<>(compatibleProduct.getCompatibleMains());
                mains.retainAll(mainProducts);
                if (mains.isEmpty()) {
                    additionalProducts.remove(compatibleProduct);
                }
            }
            return result;
        }
    }

    /**
     * Returns recommendations for user specified by current state of cart.
     * @return recommended additional products
     */
    public Set<AdditionalProduct> getRecommendations() {
        Set<AdditionalProduct> result = new HashSet<>();
        for (MainProduct main : mainProducts) {
            result.addAll(main.getCompatibleAdditions());
        }
        result.removeAll(additionalProducts);
        return result;
    }

}
