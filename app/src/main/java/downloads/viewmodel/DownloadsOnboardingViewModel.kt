package downloads.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import downloads.service.DownloadsOnboardingRepository
import downloads.usecase.GetDownloadsOnboardingModel
import downloads.usecase.GetDownloadsOnboardingModelUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DownloadsOnboardingViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val getDownloadsOnboardingModelUseCase: GetDownloadsOnboardingModelUseCase,
    private val test : DownloadsOnboardingRepository
) : ViewModel() {

    private val _success = MutableStateFlow<DownloadsOnboardingUiState>(DownloadsOnboardingUiState.Loading)
    val success = _success.asStateFlow()

    /*
    init -> automatycznie wykonuje się podczas tworzenia nowego obiektu klasy,
    inicjalizuje obiekt przed jego użyciem
     */
    init {
        loadDownloadsOnboardingModel()
    }

    fun loadDownloadsOnboardingModel(){
        viewModelScope.launch(ioDispatcher) {
            // naszym wyborem jest getDownloadsOnboardingModelUseCase() gdyż iż ponieważ używa Coroutines
            _success.value = DownloadsOnboardingUiState.Plain(model = getDownloadsOnboardingModelUseCase())
        }
    }

}