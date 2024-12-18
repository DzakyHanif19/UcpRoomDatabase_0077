package com.example.ucp2.depedenciesInjection

import android.content.Context
import com.example.ucp2.data.database.TokoDatabase
import com.example.ucp2.repository.LocalRepoBarang
import com.example.ucp2.repository.LocalRepoSupplier
import com.example.ucp2.repository.RepoBarang
import com.example.ucp2.repository.RepoSupplier

interface InterfaceContainerApp {
    val repoBarang : RepoBarang
    val repoSupplier : RepoSupplier
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repoBarang: RepoBarang by lazy {
        LocalRepoBarang(TokoDatabase.getDatabase(context).barangDao())
    }
    override val repoSupplier: RepoSupplier by lazy {
        LocalRepoSupplier(TokoDatabase.getDatabase(context).supplierDao())
    }
}
