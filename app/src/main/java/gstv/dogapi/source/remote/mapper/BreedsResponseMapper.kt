package gstv.dogapi.source.remote.mapper

import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.domain.Breed
import gstv.dogapi.source.remote.BreedResponse

class BreedsResponseMapper : Mapper<BreedResponse, Breed> {
    override fun map(from: BreedResponse) = Breed(
        id = from.id,
        name = from.name,
        origin = from.origin,
        lifeSpan = from.lifeSpan,
        temperament = from.temperament
    )
}