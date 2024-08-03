package com.example.magnesiumathletesstore.model

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

data class ImageEdgeContainer(
    val edges: List<ImageEdge>
)