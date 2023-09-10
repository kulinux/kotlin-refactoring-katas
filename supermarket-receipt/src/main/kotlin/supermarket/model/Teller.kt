package supermarket.model

class Teller(private val catalog: SupermarketCatalog) {
  private var offers = emptyList<Offer>()

  fun addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double) {
    this.offers = this.offers + Offer.build(offerType, setOf(product), argument)
  }

  fun addSpecialOffer(offerType: SpecialOfferType, products: Set<Product>, argument: Double) {
    this.offers = this.offers + Offer.build(offerType, products, argument)
  }

  fun checksOutArticlesFrom(theCart: ShoppingCart): Receipt {
    val receipt = Receipt()
    val productQuantities = theCart.getItems()
    for ((product, quantity) in productQuantities) {
      val unitPrice = this.catalog.getUnitPrice(product)
      receipt.addProduct(product, quantity, unitPrice)
    }
    theCart.handleOffers(receipt, this.offers, this.catalog)

    return receipt
  }


}
