package supermarket.model

abstract class Offer protected constructor(
  internal val products: Set<Product>, internal var argument: Double
) {
  open fun discount(unitPrice: Double, quantity: Double): Discount? {
    return null
  }
  open fun discount(catalog: SupermarketCatalog, productQuantities: Map<Product, Double>): Discount? {
    for (productQuantity in productQuantities.entries) {
      val product = productQuantity.key
      val quantity = productQuantity.value

      if(products.contains(product) && products.size == 1) {
        val unitPrice = catalog.getUnitPrice(product)
        return discount(unitPrice, quantity)
      }
    }
    return null
  }

  companion object {
    fun build(offerType: SpecialOfferType, products: Set<Product>, argument: Double): Offer {
      if (offerType === SpecialOfferType.ThreeForTwo) {
        return ThreeForTwo(products, argument)
      }
      if (offerType === SpecialOfferType.TenPercentDiscount) {
        return TenPercentDiscount(products, argument)
      }
      if (offerType === SpecialOfferType.TwoForAmount) {
        return TwoForAmount(products, argument)
      }
      if (offerType === SpecialOfferType.FiveForAmount) {
        return FiveForAmount(products, argument)
      }
      if (offerType === SpecialOfferType.Bundle) {
        return Bundle(products, argument)
      }
      throw Error("No Offer Type for $offerType")
    }
  }


}

private class TwoForAmount(product: Set<Product>, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount? {
    val quantityAsInt = quantity.toInt()
    if (quantityAsInt >= 2) {
      val total = argument * (quantityAsInt / 2) + quantityAsInt % 2 * unitPrice
      val discountN = unitPrice * quantity - total
      return Discount(products, "2 for $argument", discountN)
    }
    return null
  }
}

private class TenPercentDiscount(product: Set<Product>, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount {
    return Discount(products, "$argument% off", quantity * unitPrice * argument / 100.0)
  }
}

private class ThreeForTwo(product: Set<Product>, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount {
    val quantityAsInt = quantity.toInt()
    val numberOfXs = quantityAsInt / 3
    val discountAmount =
      quantity * unitPrice - (numberOfXs.toDouble() * 2.0 * unitPrice + quantityAsInt % 3 * unitPrice)
    return Discount(products, "3 for 2", discountAmount)
  }
}

private class FiveForAmount(product: Set<Product>, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount {
    val quantityAsInt = quantity.toInt()
    val numberOfXs = quantityAsInt / 5
    val discountTotal = unitPrice * quantity - (argument * numberOfXs + quantityAsInt % 5 * unitPrice)
    return Discount(products, "5 for $argument", discountTotal)
  }
}

private class Bundle(products: Set<Product>, argument: Double) : Offer(products, argument) {

  override fun discount(catalog: SupermarketCatalog, productQuantities: Map<Product, Double>): Discount? {
    return Discount(products, "not implemented",  10.0)
  }
}



