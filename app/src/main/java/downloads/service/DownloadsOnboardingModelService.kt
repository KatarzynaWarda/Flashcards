package downloads.service

import com.example.myapplicationcompose.R
import downloads.data.DownloadsOnboardingModel

class DownloadsOnboardingModelService() : DownloadsOnboardingRepository {

    /*
    tutaj dziedziczymy ponieważ ta klasa ma spełnić kontrakt zdefiniowany
    w repository, implementujemy tu wszystkie metody tej klasy
     */
    override fun getDataFromModel() =
        DownloadsOnboardingModel(
            gotItButton = "OK, GOT IT",
            pagerList = pagerListContent()
        )

    private fun pagerListContent() = listOf(
        DownloadsOnboardingModel.PagerUiData(
            imageUrl = "",
            title = "",
            subtitle = "",
            icon = DownloadsOnboardingModel.PagerUiData.IconType.New("NEW")
        ),
        DownloadsOnboardingModel.PagerUiData(
            imageUrl = "",
            title = "",
            subtitle = "",
            icon = DownloadsOnboardingModel.PagerUiData.IconType.BasicIcon(R.drawable.icons8_close_60)
        ),
        DownloadsOnboardingModel.PagerUiData(
            imageUrl = "",
            title = "",
            subtitle = "",
            icon = DownloadsOnboardingModel.PagerUiData.IconType.BasicIcon(R.drawable.icons8_done_52)
        )
    )
}