package downloads.usecase

import downloads.data.DownloadsOnboardingModel

interface GetDownloadsOnboardingModelUseCase {
    /*
    operator definiuje władne działanie / logikę (?)
     */
    suspend operator fun invoke() : DownloadsOnboardingModel
}