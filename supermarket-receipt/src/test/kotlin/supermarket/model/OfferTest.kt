package supermarket.model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OfferTest {
  private val toothbrush = Product.each("toothbrush")
  private val toothpaste = Product.each("toothpaste")
  private val catalog = FakeCatalog()

  @BeforeEach
  private fun setupCatalog() {
    catalog.addProduct(toothbrush, 50.0)
    catalog.addProduct(toothpaste, 50.0)
  }

  @Test
  fun shouldDiscountAn10bundle() {
    val offer = Offer.build(SpecialOfferType.Bundle, setOf(toothbrush, toothpaste), 10.0)

    val discount = offer.discount(catalog, mapOf(toothbrush to 1.0, toothpaste to 1.0))

    assertEquals(10.0, discount?.discountAmount)
  }

  @Test
  fun shouldDiscountAn5bundle() {
    val offer = Offer.build(SpecialOfferType.Bundle, setOf(toothbrush, toothpaste), 5.0)

    val discount = offer.discount(catalog, mapOf(toothbrush to 1.0, toothpaste to 1.0))

    assertEquals(5.0, discount?.discountAmount)
  }


}
