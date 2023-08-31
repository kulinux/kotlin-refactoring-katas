package supermarket.model

import org.approvaltests.Approvals
import org.junit.jupiter.api.Test
import supermarket.ReceiptPrinter

class SupermarketTest {
  @Test
  fun testSomething() {
    val catalog = FakeCatalog()
    val toothbrush = Product("toothbrush", ProductUnit.Each)
    catalog.addProduct(toothbrush, 0.99)
    val apples = Product("apples", ProductUnit.Kilo)
    catalog.addProduct(apples, 1.99)

    val cart =
      ShoppingCart()
    cart.addItemQuantity(apples, 2.5)

    val teller = Teller(catalog)
    teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0)

    val receipt = teller.checksOutArticlesFrom(cart)

    val receiptPrinter = ReceiptPrinter()

    Approvals.verify(receiptPrinter.printReceipt(receipt))

  }
}
