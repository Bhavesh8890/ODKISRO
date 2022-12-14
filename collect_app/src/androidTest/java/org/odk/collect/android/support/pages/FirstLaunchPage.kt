package org.odk.collect.android.support.pages

import org.odk.collect.android.R

class FirstLaunchPage : Page<FirstLaunchPage>() {

    override fun assertOnPage(): FirstLaunchPage {
        assertText(R.string.configure_with_qr_code)
        return this
    }

    fun clickTryCollect(): MainMenuPage {
        scrollToAndClickText(R.string.try_demo)
        return MainMenuPage().assertOnPage()
    }

    fun clickManuallyEnterProjectDetails(): ManualProjectCreatorDialogPage {
        scrollToAndClickText(R.string.configure_manually)
        return ManualProjectCreatorDialogPage().assertOnPage()
    }

    fun clickConfigureWithQrCode(): QrCodeProjectCreatorDialogPage {
        scrollToAndClickText(R.string.configure_with_qr_code)
        return QrCodeProjectCreatorDialogPage().assertOnPage()
    }
}
