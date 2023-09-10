package supermarket.model

class ShoppingCart {
  private val items = ArrayList<ProductQuantity>()

  internal fun getItems(): List<ProductQuantity> {
    return ArrayList(items)
  }

  private fun productQuantities(): Map<Product, Double> {
    return items.groupBy({ it.product }, { it.quantity })
      .mapValues { it.value.sum() }
  }


  fun addItemQuantity(product: Product, quantity: Double) {
    items.add(ProductQuantity(product, quantity))
  }

  internal fun handleOffers(receipt: Receipt, offers: List<Offer>, catalog: SupermarketCatalog) {
    for(offer in offers) {
      offer.discount(catalog, productQuantities())
        ?.let { receipt.addDiscount(it) }
    }
  }

}
