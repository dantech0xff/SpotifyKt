package com.creative.spotifykt.di.component

import com.creative.spotifykt.di.DialogFragmentScope
import com.creative.spotifykt.di.module.DialogFragmentModule
import dagger.Component

@DialogFragmentScope
@Component(dependencies = [AppComponent::class],
    modules = [DialogFragmentModule::class])
interface DialogFragmentComponent