package supermarket.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OfferTest {
  @Test
  fun shouldDiscountAn10bundle() {
    val toothbrush = Product.each("toothbrush")
    val toothpaste = Product.each("toothpaste")

    val catalog = FakeCatalog()
    catalog.addProduct(toothbrush, 50.0)
    catalog.addProduct(toothpaste, 50.0)

    val offer = Offer.build(SpecialOfferType.Bundle, setOf(toothbrush, toothpaste), 10.0)

    val discount = offer.discount(catalog, mapOf(toothbrush to 1.0, toothpaste to 1.0))

    assertEquals(10.0, discount?.discountAmount)
  }
}
