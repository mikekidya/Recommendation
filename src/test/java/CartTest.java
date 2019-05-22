import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CartTest {
    private MainProduct iphone = new MainProduct("iPhone", 5);
    private MainProduct yotaPhone = new MainProduct("YotaPhone", 4);
    private AdditionalProduct iphoneLeather = new AdditionalProduct("iPhone Leather Case", 3);
    private AdditionalProduct iphonePlastic = new AdditionalProduct("iPhone Plastic Case", 4);
    private AdditionalProduct yotaLeater = new AdditionalProduct("Yota Leather Case", 3);
    private AdditionalProduct yotaPlactic = new AdditionalProduct("Yota Plastic Case", 2);

    {
        iphone.addCompatibleAddition(iphoneLeather);
        iphone.addCompatibleAddition(iphonePlastic);
        yotaPhone.addCompatibleAddition(yotaLeater);
        yotaPhone.addCompatibleAddition(yotaPlactic);
    }


    @Test
    public void addTest() {
        Cart cart = new Cart();
        assertTrue(cart.add(iphone));
        assertTrue(cart.add(iphoneLeather));
        assertFalse(cart.add(yotaPlactic));
    }

    @Test
    public void removeTest() {
        Cart cart = new Cart();
        cart.add(iphone);
        assertFalse(cart.remove(yotaPhone));
        cart.add(iphoneLeather);
        assertTrue(cart.remove(iphone));
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    public void getRecommendationsOnlyOneMainInEachAdditionTest() {
        Cart cart = new Cart();
        cart.add(iphone);
        Set<AdditionalProduct> recommendations = cart.getRecommendations();
        assertTrue(recommendations.contains(iphoneLeather));
        assertTrue(recommendations.contains(iphonePlastic));
        assertFalse(recommendations.contains(yotaLeater));
        assertFalse(recommendations.contains(yotaPlactic));
    }

    @Test
    public void doubleMainsTest() {
        Cart cart = new Cart();
        assertTrue(cart.add(iphone));
        assertTrue(cart.add(iphone));
        Set<AdditionalProduct> recommendations = cart.getRecommendations();
        assertTrue(recommendations.contains(iphoneLeather));
        assertTrue(recommendations.contains(iphonePlastic));
        assertFalse(recommendations.contains(yotaLeater));
        assertFalse(recommendations.contains(yotaPlactic));
    }

    @Test
    public void recommendationReducingMainsTest() {
        Cart cart = new Cart();
        assertTrue(cart.add(iphone));
        Set<AdditionalProduct> recommendations = cart.getRecommendations();
        assertTrue(recommendations.contains(iphoneLeather));
        assertTrue(recommendations.contains(iphonePlastic));
        assertFalse(recommendations.contains(yotaLeater));
        assertFalse(recommendations.contains(yotaPlactic));
        assertTrue(cart.add(iphoneLeather));
        recommendations = cart.getRecommendations();
        assertFalse(recommendations.contains(iphoneLeather));
        assertTrue(recommendations.contains(iphonePlastic));
        assertFalse(recommendations.contains(yotaLeater));
        assertFalse(recommendations.contains(yotaPlactic));
    }
}