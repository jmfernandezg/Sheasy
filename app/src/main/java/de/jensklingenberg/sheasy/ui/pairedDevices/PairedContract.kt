package de.jensklingenberg.sheasy.ui.pairedDevices

import de.jensklingenberg.sheasy.ui.common.BaseDataSourceItem
import de.jensklingenberg.sheasy.ui.common.MvpPresenter
import de.jensklingenberg.sheasy.model.Device

interface PairedContract {

    interface View {
            fun setData(list: List<BaseDataSourceItem<*>>)

    }

    interface Presenter : MvpPresenter,
        DeviceListItemViewHolder.OnEntryClickListener {
        fun revokeDevice(appInfo: Device)


    }

}