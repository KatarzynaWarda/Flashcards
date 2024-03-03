package downloads.data

import android.os.Parcel
import android.os.Parcelable

data class DownloadsOnboardingModel(
    var gotItButton : String,
    val pagerList: List<PagerUiData>,
){
    /*
     sealed -> ogranicza dziedziczenie tylko od danego typu
     (masz pudełko z autkami to lalki nie włożysz)
     */

    data class PagerUiData(
        val imageUrl: String,
        val title: String,
        val subtitle : String,
        val icon : IconType
    ) {
        sealed interface IconType {
            data class New(val text: String) : IconType
            data class BasicIcon (val icon: Int): IconType
        }
    }
}