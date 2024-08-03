package com.example.magnesiumathletesstore.repository

import com.shopify.buy3.GraphClient
import com.shopify.buy3.Storefront
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShopifyRepository @Inject constructor(
    private val graphClient: GraphClient
) {

    suspend fun getAllProducts(): List<Storefront.Product> = withContext(Dispatchers.IO) {
        val query = Storefront.query { rootQuery ->
            rootQuery.products({ args -> args.first(10) }) { productConnectionQuery ->
                productConnectionQuery.edges { productEdgeQuery ->
                    productEdgeQuery.node { productQuery ->
                        productQuery
                            .id()
                            .title()
                            .description()
                            .images({ args -> args.first(1) }) { imageConnectionQuery ->
                                imageConnectionQuery.edges { imageEdgeQuery ->
                                    imageEdgeQuery.node { imageQuery ->
                                        imageQuery.src()
                                    }
                                }
                            }
                            .variants({ args -> args.first(1) }) { variantConnectionQuery ->
                                variantConnectionQuery.edges { variantEdgeQuery ->
                                    variantEdgeQuery.node { productVariantQuery ->
                                        productVariantQuery.price()
                                    }
                                }
                            }
                    }
                }
            }
        }

        val call = graphClient.queryGraph(query)
        val response = call.execute()

        if (response.hasErrors()) {
            throw Exception("GraphQL errors: ${response.errors()}")
        }

        response.data()?.products()?.edges()?.map { it.node } ?: emptyList()
    }

    suspend fun getProductById(productId: String): Storefront.Product? = withContext(Dispatchers.IO) {
        val query = Storefront.query { rootQuery ->
            rootQuery.node(Storefront.ID(productId)) { nodeQuery ->
                nodeQuery.onProduct { productQuery ->
                    productQuery
                        .id()
                        .title()
                        .description()
                        .images({ args -> args.first(1) }) { imageConnectionQuery ->
                            imageConnectionQuery.edges { imageEdgeQuery ->
                                imageEdgeQuery.node { imageQuery ->
                                    imageQuery.src()
                                }
                            }
                        }
                        .variants({ args -> args.first(1) }) { variantConnectionQuery ->
                            variantConnectionQuery.edges { variantEdgeQuery ->
                                variantEdgeQuery.node { productVariantQuery ->
                                    productVariantQuery.price()
                                }
                            }
                        }
                }
            }
        }

        val call = graphClient.queryGraph(query)
        val response = call.execute()

        if (response.hasErrors()) {
            throw Exception("GraphQL errors: ${response.errors()}")
        }

        response.data()?.node() as? Storefront.Product
    }
}
