package downloads.usecase

import downloads.data.DownloadsOnboardingModel
import downloads.service.DownloadsOnboardingModelService
import downloads.service.DownloadsOnboardingRepository

class GetDownloadsOnboardingModel (
    /*
     tutaj tworzymy zmienną z interjefsu ponieważ zadania tej klasy
     są ZALEŻNE od Repository
     (jak wygląda repository tak będzie wyglądać ta klasa)
     */
    private val downloadsOnboardingRepository: DownloadsOnboardingRepository,
) : GetDownloadsOnboardingModelUseCase {

    /*
     suspend -> umożliwia tymczasowe zawieszenie wykonywania danej operacji
     bez blokowania wątku na którym działa
     (kelner nie czeka na zrobienie dania, tylko obsługuje innych klientów)
     */

    /*
     invoke() -> instancja klasy zachowująca się jak funkcja, dzięki czemu
     można używać jej jak obiektu bez wywoływania metody
     (magiczna księga, nie podajesz zaklęcia)
     */

    /*
     Coroutines -> narzędzie do zarządzanai operacjami asynchronicznymi
     wykonywanie wielu zadań równocześnie
     */

    override suspend fun invoke() =
        try{
            downloadsOnboardingRepository.getDataFromModel()
        }
        catch (e : Exception) {
            throw Exception(e)
        }
}