package io.mohammedalaamorsi.restaurants.di


import io.mohammedalaamorsi.restaurants.data.repository.RestaurantsRepositoryImp
import io.mohammedalaamorsi.restaurants.domain.RestaurantsRepository
import io.mohammedalaamorsi.restaurants.domain.usecase.GetRestaurantDetailsUseCase
import io.mohammedalaamorsi.restaurants.domain.usecase.GetRestaurantsUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val domainModule = module {
    singleOf(::RestaurantsRepositoryImp) { bind<RestaurantsRepository>() }
    factoryOf(::GetRestaurantsUseCase)
    factoryOf(::GetRestaurantDetailsUseCase)


}
