package gstv.dogapi.core.di

import gstv.dogapi.presentation.view_model.HomeViewModel
import gstv.dogapi.source.DogRepository
import gstv.dogapi.source.DogRepositoryImpl
import gstv.dogapi.source.remote.mapper.BreedsResponseMapper
import gstv.dogapi.source.remote.mapper.DogImageResponseMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dogModule = module {
    mapperFactory { BreedsResponseMapper() }
    mapperFactory { DogImageResponseMapper() }

    single<DogRepository> {
        DogRepositoryImpl(getMapperOf(), getMapperOf())
    }
    viewModel { HomeViewModel(get()) }
}