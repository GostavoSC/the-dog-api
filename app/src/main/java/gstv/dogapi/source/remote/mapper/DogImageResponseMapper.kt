package gstv.dogapi.source.remote.mapper

import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.domain.DogImage
import gstv.dogapi.source.remote.DogImageResponse

class DogImageResponseMapper : Mapper<DogImageResponse, DogImage> {
    override fun map(from: DogImageResponse) = DogImage(
        id = from.id,
        url = from.url
    )
}