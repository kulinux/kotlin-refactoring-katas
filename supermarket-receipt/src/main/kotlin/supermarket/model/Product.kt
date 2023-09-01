package supermarket.model

data class Product private constructor (
  val name: String,
  private val unit: ProductUnit
) {

  fun isQuantityInt() = unit === ProductUnit.Each

  companion object {
    fun each(name: String): Product = Product(name, ProductUnit.Each)
    fun kilo(name: String): Product = Product(name, ProductUnit.Kilo)
  }

  private enum class ProductUnit {
    Kilo, Each
  }
}


