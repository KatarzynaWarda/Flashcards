package downloads.service

import downloads.data.DownloadsOnboardingModel

interface DownloadsOnboardingRepository {
    fun getDataFromModel() : DownloadsOnboardingModel
}