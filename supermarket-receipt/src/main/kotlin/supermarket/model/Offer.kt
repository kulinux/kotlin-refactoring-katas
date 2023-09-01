package supermarket.model

abstract class Offer protected constructor(
  internal val product: Product, internal var argument: Double
) {
  abstract fun discount(unitPrice: Double, quantity: Double): Discount?

  companion object {
    fun build(offerType: SpecialOfferType, product: Product, argument: Double): Offer {
      if (offerType === SpecialOfferType.ThreeForTwo) {
        return ThreeForTwo(product, argument)
      }
      if (offerType === SpecialOfferType.TenPercentDiscount) {
        return TenPercentDiscount(product, argument)
      }
      if (offerType === SpecialOfferType.TwoForAmount) {
        return TwoForAmount(product, argument)
      }
      if (offerType === SpecialOfferType.FiveForAmount) {
        return FiveForAmount(product, argument)
      }
      throw Error("No Offer Type for $offerType")
    }
  }
}

private class TwoForAmount(product: Product, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount? {
    val quantityAsInt = quantity.toInt()
    if (quantityAsInt >= 2) {
      val total = argument * (quantityAsInt / 2) + quantityAsInt % 2 * unitPrice
      val discountN = unitPrice * quantity - total
      return Discount(product, "2 for $argument", discountN)
    }
    return null
  }
}

private class TenPercentDiscount(product: Product, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount {
    return Discount(product, "$argument% off", quantity * unitPrice * argument / 100.0)
  }
}

private class ThreeForTwo(product: Product, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount {
    val quantityAsInt = quantity.toInt()
    val numberOfXs = quantityAsInt / 3
    val discountAmount =
      quantity * unitPrice - (numberOfXs.toDouble() * 2.0 * unitPrice + quantityAsInt % 3 * unitPrice)
    return Discount(product, "3 for 2", discountAmount)
  }
}

private class FiveForAmount(product: Product, argument: Double) : Offer(product, argument) {

  override fun discount(unitPrice: Double, quantity: Double): Discount {
    val quantityAsInt = quantity.toInt()
    val numberOfXs = quantityAsInt / 5
    val discountTotal = unitPrice * quantity - (argument * numberOfXs + quantityAsInt % 5 * unitPrice)
    return Discount(product, "5 for $argument", discountTotal)
  }
}



