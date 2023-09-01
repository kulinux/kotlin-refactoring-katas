package supermarket.model

import org.approvaltests.Approvals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import supermarket.ReceiptPrinter

class ApprovalSupermarketTest {
  lateinit var catalog: SupermarketCatalog
  lateinit var toothbrush: Product
  lateinit var apples: Product

  @BeforeEach
  fun setUp() {
    toothbrush = Product.each("toothbrush")
    apples = Product.kilo("apples")
    catalog = catalog(apples to 1.99, toothbrush to 0.99)
  }

  @Test
  fun testPriceEach() {
    val cart = shoppingCart(toothbrush to 2.0)

    val printReceipt = printReceipt(Teller(catalog), cart)
    Approvals.verify(printReceipt)
  }

  @Test
  fun testPriceKilo() {
    val cart = shoppingCart(apples to 2.5)

    val printReceipt = printReceipt(Teller(catalog), cart)
    Approvals.verify(printReceipt)
  }

  @Test
  fun testAddingTwice() {
    val cart = shoppingCart(apples to 2.5, apples to 1.0)

    val printReceipt = printReceipt(Teller(catalog), cart)
    Approvals.verify(printReceipt)
  }

  @ParameterizedTest(name = "Special Offer {0}")
  @EnumSource(SpecialOfferType::class)
  fun testOffers(offerType: SpecialOfferType) {
    val cart = shoppingCart(toothbrush to 6.0, apples to 6.5)

    val teller = Teller(catalog)
    teller.addSpecialOffer(offerType, toothbrush, 10.0)

    val printReceipt = printReceipt(teller, cart)
    Approvals.verify(printReceipt, Approvals.NAMES.withParameters(offerType.name))
  }


  private fun shoppingCart(vararg productsWithQty: Pair<Product, Double>): ShoppingCart =
    productsWithQty.fold(ShoppingCart()) { acc, element -> acc.addItemQuantity(element.first, element.second); acc }

  private fun catalog(vararg productsWithPrice: Pair<Product, Double>): SupermarketCatalog =
    productsWithPrice.fold(FakeCatalog()) { acc, element -> acc.addProduct(element.first, element.second); acc }

  private fun printReceipt(teller: Teller, cart: ShoppingCart): String {
    val receipt = teller.checksOutArticlesFrom(cart)
    val receiptPrinter = ReceiptPrinter()
    val printReceipt = receiptPrinter.printReceipt(receipt)
    return printReceipt
  }

}
