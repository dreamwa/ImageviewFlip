package com.example.myapplication

data class ConfigBean(
    val locationService: LocationService,
    val navigationService: NavigationService,
    val updateService: UpdateService,
    val imageLoadService: ImageLoadService,
    val imageWaterMarkService: ImageWaterMarkService,
    val speechRecognitionService: SpeechRecognitionService,
    val speechSpeakService: SpeechSpeakService
) {
    data class LocationService(val isOpen: Boolean) {

    }

    data class NavigationService(val isOpen: Boolean, val navigationType: String) {

    }

    data class UpdateService(val isOpen: Boolean, val updateUrl: String) {

    }

    data class ImageLoadService(val isOpen: Boolean, val loadType: String) {

    }

    data class ImageWaterMarkService(val isOpen: Boolean) {

    }

    data class SpeechRecognitionService(val isOpen: Boolean, val content: String) {

    }

    data class SpeechSpeakService(val isOpen: Boolean, val content: String) {

    }


}