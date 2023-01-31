package com.azamovhudstc.animalwiki.data.local.models

import java.io.Serializable

class WikiPostData(var liked: String, var imgData: String, var title: String, var des: String):Serializable {

    override fun toString(): String {
        return "WikiData(liked='$liked', imgData='$imgData', title='$title', des='$des')"
    }

}
