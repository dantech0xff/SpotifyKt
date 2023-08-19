package com.creative.spotifykt.di.module

import com.creative.spotifykt.core.ui.BaseDialogFragment
import dagger.Module

@Module
class DialogFragmentModule(private val dialogFragment: BaseDialogFragment<*>)