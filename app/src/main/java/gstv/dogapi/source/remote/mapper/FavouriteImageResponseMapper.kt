package gstv.dogapi.source.remote.mapper

import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.domain.DogImage
import gstv.dogapi.domain.FavouriteImage
import gstv.dogapi.source.remote.DogImageResponse
import gstv.dogapi.source.remote.FavouriteImageResponse

class FavouriteImageResponseMapper : Mapper<FavouriteImageResponse, FavouriteImage> {
    override fun map(from: FavouriteImageResponse) = FavouriteImage(
        id = from.id,
        image = from.image
    )
}