package com.azamovhudstc.animalwiki.repo.imp

import com.azamovhudstc.animalwiki.data.local.database.dao.VideosDao
import com.azamovhudstc.animalwiki.data.model.GeneralModel
import com.azamovhudstc.animalwiki.data.remote.api.YoutubeApi
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import com.azamovhudstc.animalwiki.repo.NetworkRepository
import com.azamovhudstc.animalwiki.repo.VideosRepository
import com.azamovhudstc.animalwiki.utils.extensions.hasConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class VideosRepositoryImp @Inject constructor(
    private val youtubeApi: YoutubeApi,
    private val videosDao: VideosDao,
    private val networkRepository: NetworkRepository
) : VideosRepository {
    override fun getQuranPlayListsById(
        playListId: String,
        maxResults: Int
    ): Flow<PlayListResponse> = flow<PlayListResponse> {
        var m1norPlaylist =
            youtubeApi.getPlayLists(playListId = playListId, maxResults = maxResults)
        println("Request Url " + m1norPlaylist.raw().request.url.toString())
        if (m1norPlaylist.isSuccessful) {
            emit(m1norPlaylist.body()!!)
        }
    }.flowOn(Dispatchers.IO)
    /*** this fun is get all General Array List Data  youtube videos..
     * get all Videos Data and Cache Room Database get video playLists by playList Id
     * .***/
    override fun getGeneralArrayList(): Flow<ArrayList<GeneralModel>> = flow {
        if (hasConnection()){
            if (videosDao.getAllBooks().isEmpty()) {
                var arrayList = ArrayList<GeneralModel>()


                arrayList.add(
                    GeneralModel(
                        title = "Yovoyyi Tabiat",
                        getQuranPlayListsById(
                            playListId = "PLSe18GjmWaI50saXGdcB_nI6ygbxBMzfC",
                            maxResults = 6
                        ).first()
                    )
                )

                arrayList.add(
                    GeneralModel(
                        "Tabiat Gullari",
                        getQuranPlayListsById("PLnphCU8rCuwro50E0lOKx2feqdM01XKuo", 6).first()
                    )

                )
                arrayList.add(
                    GeneralModel(
                        title = "Aralash tur | Hayvonlar 4K",
                        getQuranPlayListsById(
                            playListId = "PLSe18GjmWaI4KzmkB9F2hNMw7IGYD3MEj",
                            maxResults = 6
                        ).first()
                    )
                )
                arrayList.add(
                    GeneralModel(
                        title = "Tabiat haqidagi hujjatlar",
                        getQuranPlayListsById(
                            playListId = "PLSe18GjmWaI7MHuX3atHNTfUBuz8mUC1I",
                            maxResults = 6
                        ).first()
                    )
                )


                arrayList.add(
                    GeneralModel(
                        "Qushlar",
                        getQuranPlayListsById("PLQlnTldJs0ZQU2Ee9vILZYNxg4LVwMzEG", 6).first()
                    )
                )

                arrayList.forEach {
                    videosDao.addGeneric(it.toGenericEntity())
                }
                emit(arrayList)

            }
            else {
                var arrayList = ArrayList<GeneralModel>()
                videosDao.getAllBooks().forEach {
                    arrayList.add(it.toGeneralModel())
                }
                emit(arrayList)
            }
        }

    }
}