public abstract class Product {
    private int rating;
    private String name;

    /**
     * Creates new product.
     * @param name product name
     * @param rating product rating
     */
    public Product(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    /**
     * Returns the rating of product
     * @return the rating of product
     */
    public int getRating() {
        return rating;
    }

    /** Sets the reting of product */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Returns the name of product
     * @return the name of product
     */
    public String getName() {
        return name;
    }
}
