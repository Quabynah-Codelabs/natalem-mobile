package io.codelabs.natalem.core

import io.codelabs.natalem.viewmodel.NatalemDatabase
import org.koin.dsl.module

val roomModule = module {
    single { NatalemDatabase.getInstance(get()) }
}