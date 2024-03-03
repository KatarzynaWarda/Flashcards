package downloads.viewmodel

import downloads.data.DownloadsOnboardingModel

sealed interface DownloadsOnboardingUiState {

    object Loading : DownloadsOnboardingUiState

    data class Plain(val model: DownloadsOnboardingModel) : DownloadsOnboardingUiState

}