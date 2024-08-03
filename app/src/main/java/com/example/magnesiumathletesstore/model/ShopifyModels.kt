package com.example.magnesiumathletesstore.model

import com.squareup.moshi.JsonClass


data class Product(
    val id: String,
    val title: String,
    val images: ImageEdgeContainer,
    val price: PriceRange,
    val collections: CollectionEdgeContainer,
    val collectionId: String? = null // Add collectionId here
)

data class CollectionEdgeContainer(
    val edges: List<CollectionEdge>
)

data class ImageConnection(
    val edges: List<ImageEdge>
)


data class ImageEdge(
    val node: Image
)


data class Image(
    val src: String
)


data class PriceRange(
    val minVariantPrice: Price
)


data class Price(
    val amount: String,
    val currencyCode: String
)


data class ProductConnection(
    val edges: List<ProductEdge>
)

data class ProductEdgeContainer(
    val edges: List<ProductEdge>
)

data class ProductEdge(
    val node: Product
)

data class CollectionConnection(
    val edges: List<CollectionEdge>
)

data class CollectionEdge(
    val node: Collection
)


data class Collection(
    val id: String
)


data class CartItem(
    val id: String,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int
)

data class ShopifyCollection(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String
)

data class ImageEdgeContainer(
    val edges: List<ImageEdge>
)