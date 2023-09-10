package supermarket.model

class Discount(
  val products: Set<Product>,
  val description: String,
  val discountAmount: Double
)
