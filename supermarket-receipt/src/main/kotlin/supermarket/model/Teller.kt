package supermarket.model

class Teller(private val catalog: SupermarketCatalog) {
  private val offers = HashMap<Product, Offer>()

  fun addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double) {
    this.offers[product] = Offer.build(offerType, product, argument)
  }

  fun addSpecialOffer(offerType: SpecialOfferType, product: List<Product>, argument: Double) {
    TODO("Not yet implemented")
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
