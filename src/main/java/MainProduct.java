import java.util.HashSet;
import java.util.Set;

public class MainProduct extends Product {
    private Set<AdditionalProduct> compatibleAdditions = new HashSet<>();

    public MainProduct(String name, int rating) {
        super(name, rating);
    }

    /**
     * Returns all products which are compatible with this one.
     * @return compatible additional products
     */
    public Set<AdditionalProduct> getCompatibleAdditions() {
        return compatibleAdditions;
    }

    /**
     * Adds new additional compatible product.
     * @param product new compatible product.
     */
    public void addCompatibleAddition(AdditionalProduct product) {
        compatibleAdditions.add(product);
        if (!product.getCompatibleMains().contains(this)) {
            product.addCompatibleMain(this);
        }
    }
}
