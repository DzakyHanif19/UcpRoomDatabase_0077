package com.example.ucp2.ui.navigation

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeSpl: AlamatNavigasi {
    override val route = "homesuplier"
}

object DestinasiHomeBrg: AlamatNavigasi {
    override val route = "homebarang"
}

object DestinasiInsertSpl: AlamatNavigasi {
    override val route = "suplier"
}

object DestinasiInsertBrg: AlamatNavigasi {
    override val route = "barang"
}

object DestinasiDetailSpl: AlamatNavigasi {
    override val route = "detail"
    const val idSuplier = "idSuplier"
    val routeWithArg = "$route/{$idSuplier}"
}

object DestinasiDetailBrg: AlamatNavigasi {
    override val route = "detail"
    const val idBarang = "idBarang"
    val routeWithArg = "$route/{$idBarang}"
}

object DestinasiUpdate: AlamatNavigasi {
    override val route = "update"
    const val idBarang = "idBarang"
    val routeWithArg = "$route/{$idBarang}"
}