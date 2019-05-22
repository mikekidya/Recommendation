/** Class representing User in online shop */
public class User {
    private String name;
    private Cart cart = new Cart();

    /**
     * Creates new user.
     * @param name username
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Returns user name
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the current cart of the user
     * @return user cart
     */
    public Cart getCart() {
        return cart;
    }
}
