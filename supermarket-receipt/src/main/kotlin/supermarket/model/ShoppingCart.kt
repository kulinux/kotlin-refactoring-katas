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

  internal fun handleOffers(receipt: Receipt, offers: Map<Product, Offer>, catalog: SupermarketCatalog) {
    for (productQuantity in productQuantities().entries) {
      val product = productQuantity.key
      val quantity = productQuantity.value
      val unitPrice = catalog.getUnitPrice(product)

      offers[product]
        ?.discount(unitPrice, quantity)
        ?.let { receipt.addDiscount(it) }

    }
  }

}
