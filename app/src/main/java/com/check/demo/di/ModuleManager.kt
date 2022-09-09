package com.check.demo.di

import com.check.demo.api.Check24ApiService
import com.check.demo.datasource.NetworkProductDataSource
import com.check.demo.datasource.ProductDataSource
import com.check.demo.network.HttpClientFactory
import com.check.demo.network.RetrofitFactory
import com.check.demo.repository.ProductRepositoryImpl
import com.check.demo.repository.ProductsRepository
import com.check.demo.ui.product.details.DetailsViewModel
import com.check.demo.ui.product.overview.OverviewViewModel
import com.check.demo.util.DispatcherManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ModuleManager {

    private val utilsModule = module {
        single { Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() }
        single {
            DispatcherManager(
                Dispatchers.Main,
                Dispatchers.IO,
                Dispatchers.Default
            )
        }
    }
    private val networkModule = module {
        single { HttpClientFactory.getHttpClient() }
        single { RetrofitFactory.getApi(get(), get(), Check24ApiService::class.java) }
    }
    private val dataSourceModule = module {
        single<ProductDataSource> { NetworkProductDataSource(get(), get()) }
    }
    private val repositoryDataSource = module {
        single<ProductsRepository> { ProductRepositoryImpl(get()) }
    }
    private val vmModule = module {
        viewModel { OverviewViewModel(get()) }
        viewModel { DetailsViewModel() }
    }
    val modules = listOf(
        utilsModule,
        networkModule,
        dataSourceModule,
        repositoryDataSource,
        vmModule
    )
}
