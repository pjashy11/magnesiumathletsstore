package com.example.magnesiumathletesstore

import android.content.Context
import android.text.TextUtils
import com.example.magnesiumathletesstore.repository.ShopifyRepository
import com.shopify.buy3.GraphClient
import com.shopify.buy3.HttpCachePolicy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val SHOP_PROPERTIES_INSTRUCTION = """
        Add your shop credentials to a shop.properties file in the main app folder (e.g. 'app/shop.properties').
        Include these keys:
        SHOP_DOMAIN=<myshop>.myshopify.com
        API_KEY=0123456789abcdefghijklmnopqrstuvw
    """

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideGraphClient(@ApplicationContext context: Context, httpClient: OkHttpClient): GraphClient {
        val shopUrl = BuildConfig.SHOP_DOMAIN
        if (TextUtils.isEmpty(shopUrl)) {
            throw IllegalArgumentException("$SHOP_PROPERTIES_INSTRUCTION You must add 'SHOP_DOMAIN' entry in app/shop.properties, in the form '<myshop>.myshopify.com'")
        }

        val shopifyApiKey = BuildConfig.API_KEY
        if (TextUtils.isEmpty(shopifyApiKey)) {
            throw IllegalArgumentException("$SHOP_PROPERTIES_INSTRUCTION You must populate the 'API_KEY' entry in app/shop.properties")
        }

        return GraphClient.Companion.build(
            context,
            shopUrl,
            shopifyApiKey,
            { builder ->
                builder.setHttpClient(httpClient)
                builder.httpCache(context.cacheDir) { config ->
                    config.setCacheMaxSizeBytes(1024 * 1024 * 10)
                    config.setDefaultCachePolicy(HttpCachePolicy.CACHE_FIRST.expireAfter(20, TimeUnit.MINUTES))
                    Unit
                }
                Unit
            },
            BuildConfig.DEFAULT_LOCALE
        )
    }

    @Provides
    @Singleton
    fun provideShopifyRepository(graphClient: GraphClient): ShopifyRepository {
        return ShopifyRepository(graphClient)
    }
}
