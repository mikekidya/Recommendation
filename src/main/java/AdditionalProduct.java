import java.util.HashSet;
import java.util.Set;

public class AdditionalProduct extends Product {
    private Set<MainProduct> compatibleMains = new HashSet<>();

    public AdditionalProduct(String name, int rating) {
        super(name, rating);
    }

    /**
     * Returns all main products which are compatible with this one.
     * @return
     */
    public Set<MainProduct> getCompatibleMains() {
        return compatibleMains;
    }

    /**
     * Adds new main compatible product.
     * @param product new compatible product.
     */
    public void addCompatibleMain(MainProduct product) {
        compatibleMains.add(product);
        if (!product.getCompatibleAdditions().contains(this)) {
            product.addCompatibleAddition(this);
        }
    }
}
