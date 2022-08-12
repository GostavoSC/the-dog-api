package gstv.dogapi.source.remote.mapper

import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.domain.Category
import gstv.dogapi.source.remote.CategoryResponse

class CategoryResponseMapper : Mapper<CategoryResponse, Category> {
    override fun map(from: CategoryResponse) = Category(
        id = from.id,
        name = from.name
    )
}