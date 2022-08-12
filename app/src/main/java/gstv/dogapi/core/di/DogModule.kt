package gstv.dogapi.core.di

import gstv.dogapi.source.DogRepository
import gstv.dogapi.source.DogRepositoryImpl
import gstv.dogapi.source.remote.mapper.BreedsResponseMapper
import org.koin.dsl.module

val dogModule = module {
    mapperFactory { BreedsResponseMapper() }

    single<DogRepository> {
        DogRepositoryImpl(getMapperOf())
    }
}