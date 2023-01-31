package com.azamovhudstc.animalwiki.data.local.models

import com.azamovhudstc.animalwiki.utils.enums.PostTypes
import java.io.Serializable

data class WikiPost(var types: PostTypes, var wikiName: String, var wikiImage: Int):Serializable

