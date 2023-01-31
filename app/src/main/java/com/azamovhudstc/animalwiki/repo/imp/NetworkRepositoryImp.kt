package com.azamovhudstc.animalwiki.repo.imp

import com.azamovhudstc.animalwiki.repo.NetworkRepository
import com.azamovhudstc.animalwiki.utils.extensions.hasConnection
import javax.inject.Inject

class NetworkRepositoryImp @Inject constructor():NetworkRepository {
    override fun networkState(): Boolean   {
        return hasConnection()
    }

}