package supermarket

import supermarket.model.Discount
import supermarket.model.Receipt
import supermarket.model.ReceiptItem
import java.util.*

private const val CARRIAGE_RETURN = "\n"

class ReceiptPrinter @JvmOverloads constructor(private val columns: Int = 40) {
  fun printReceipt(receipt: Receipt): String {
    return handleItems(receipt.getItems()) +
      handleDiscounts(receipt.getDiscounts()) +
      handleTotal(receipt.totalPrice)
  }

  private fun handleItems(items: List<ReceiptItem>): String {
    return items.joinToString("") { handleItem(it) }
  }

  private fun handleDiscounts(discounts: List<Discount>): String {
    return discounts.joinToString("") { handleDiscount(it) }
  }

  private fun handleTotal(totalPrice: Double): String {
    val pricePresentation = String.format(Locale.UK, "%.2f", totalPrice)
    val total = "Total: "
    val whitespaces = whiteSpacesOfLength(this.columns - total.length - pricePresentation.length)

    return "$CARRIAGE_RETURN$total$whitespaces$pricePresentation"
  }


  private fun handleDiscount(discount: Discount): String {
    val productPresentation = discount.products.joinToString { it.name }
    val pricePresentation = String.format(Locale.UK, "%.2f", discount.discountAmount)
    val description = discount.description

    val whiteSpacesOfLength =
      whiteSpacesOfLength(this.columns - 3 - productPresentation.length - description.length - pricePresentation.length)

    return "$description($productPresentation)$whiteSpacesOfLength-$pricePresentation$CARRIAGE_RETURN"
  }


  private fun handleItem(item: ReceiptItem): String {
    val price = String.format(Locale.UK, "%.2f", item.totalPrice)
    val quantity = presentQuantity(item)
    val name = item.product.name
    val unitPrice = String.format(Locale.UK, "%.2f", item.price)

    val whitespaceSize = this.columns - name.length - price.length
    val baseLine = name + whiteSpacesOfLength(whitespaceSize) + price + "$CARRIAGE_RETURN"

    if (item.quantity != 1.0) {
      return "$baseLine  $unitPrice * $quantity$CARRIAGE_RETURN"
    }
    return baseLine
  }

  private fun presentQuantity(item: ReceiptItem): String {
    return if (item.product.isQuantityInt())
      String.format("%x", item.quantity.toInt())
    else
      String.format(Locale.UK, "%.3f", item.quantity)
  }

  private fun whiteSpacesOfLength(whitespaceSize: Int): String {
    val whitespace = StringBuilder()
    for (i in 0 until whitespaceSize) {
      whitespace.append(" ")
    }
    return whitespace.toString()
  }
}
