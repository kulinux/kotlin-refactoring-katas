# The Supermarket Receipt Refactoring Kata

**Note** _The Kotlin source code and this README has been copied from Emily Bache's **The Supermarket Receipt
Refactoring Kata**
repository. To try this kata in a different programming language, please visit
the [original repo](https://github.com/emilybache/SupermarketReceipt-Refactoring-Kata)._

## Description

This is a variation of a popular kata described in http://codekata.com/kata/kata01-supermarket-pricing/. The aim of the
exercise is to build automated tests for this code, refactor it, and add a new feature.

The supermarket has a catalog with different types of products (rice, apples, milk, toothbrushes,...). Each product has
a price, and the total price of the shopping cart is the total of all the prices of the items. You get a receipt that
details the items you've bought, the total price and any discounts that were applied.

The supermarket runs special deals, e.g.

- Buy two toothbrushes, get one free. Normal toothbrush price is €0.99
- 20% discount on apples, normal price €1.99 per kilo.
- 10% discount on rice, normal price €2.49 per bag
- Five tubes of toothpaste for €7.49, normal price €1.79
- Two boxes of cherry tomatoes for €0.99, normal price €0.69 per box.

These are just examples: the actual special deals change each week.

Create some test cases and aim to get good enough code coverage that you feel confident to do some refactoring.

When you have good test cases, identify code smells such as Long Method, Feature Envy. Apply relevant refactorings.

When you're confident [README.md](README.md)you can handle this code, implement the new feature described below

## New feature: discounted bundles

The owner of the system has a new feature request. They want to introduce a new kind of special offer - bundles. When
you buy all the items in a product bundle you get 10% off the total for those items. For example you could make a bundle
offer of one toothbrush and one toothpaste. If you then you buy one toothbrush and one toothpaste, the discount will be
10% of €0.99 + €1.79. If you instead buy two toothbrushes and one toothpaste, you get the same discount as if you'd
bought only one of each - ie only complete bundles are discounted.

## New feature: HTML receipt

Currently we print a traditional ticket receipt. Now beeing a modern business we'd like to be able to print or send a
HTML version of the same receipt. All the data and number formatting should be the same. However the layout should be
html. You don't have to worry about the HTML template - a designer will care of that - but we do need someone to keep
duplication between the reports to a bare minimum.

## Start with the refactoring

If you would like to just do the refactoring part of this exercise, you can instead check out the 'with_tests' branch.
Those tests have reasonably good coverage and should support most kinds of refactorings you'd like to do.

## Use this exercise in a Learning Hour

You can use the [Supermarket Receipt](https://sammancoaching.org/kata_descriptions/supermarket_receipt.html) Kata in
learning hours if you are using the [Samman](https://sammancoaching.org) coaching method.

## License

```
The MIT License (MIT)

Copyright (c) 2018 Emily Bache

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
