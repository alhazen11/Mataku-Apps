package com.apps.mataku.ui.review

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class ReviewModule {
    @Provides
    fun provideReviewPresenter(reviewInteractor: ReviewInteractor): ReviewPresenter {
        return ReviewPresenterImpl(reviewInteractor, null)
    }

    @Provides
    @AppScope
    fun provideReviewInteractor(api : DataDbApi): ReviewInteractor{
        return ReviewInteractorImpl(api)
    }

}