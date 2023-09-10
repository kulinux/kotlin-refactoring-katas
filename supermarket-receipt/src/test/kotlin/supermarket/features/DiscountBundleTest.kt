package supermarket.features

import org.junit.jupiter.api.Test
import supermarket.model.*
import kotlin.test.assertEquals

class DiscountBundleTest {
  @Test
  fun discountBundle() {
    //When bundle of dental kit is set up
    val catalog = FakeCatalog()
    val toothbrush = Product.each("toothbrush")
    val toothpaste = Product.each("toothpaste")
    catalog.addProduct(toothbrush, 5.0)
    catalog.addProduct(toothpaste, 5.0)
    val teller = Teller(catalog)
    teller.addSpecialOffer(SpecialOfferType.Bundle, listOf(toothbrush, toothpaste), 10.0)

    //And I add to the shopping cart one toothbrush and one toothpaste
    val shoppingCart = ShoppingCart()
    shoppingCart.addItemQuantity(toothbrush, 1.0)
    shoppingCart.addItemQuantity(toothpaste, 1.0)

    //Then I have a 10% discount
    val receipt = teller.checksOutArticlesFrom(shoppingCart)
    assertEquals(9.0, receipt.totalPrice)
  }

}
