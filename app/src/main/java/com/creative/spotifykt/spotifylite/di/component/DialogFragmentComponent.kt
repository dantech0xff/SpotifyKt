package com.creative.spotifykt.spotifylite.di.component

import com.creative.spotifykt.spotifylite.di.DialogFragmentScope
import com.creative.spotifykt.spotifylite.di.module.DialogFragmentModule
import dagger.Component

@DialogFragmentScope
@Component(dependencies = [AppComponent::class],
    modules = [DialogFragmentModule::class])
interface DialogFragmentComponent