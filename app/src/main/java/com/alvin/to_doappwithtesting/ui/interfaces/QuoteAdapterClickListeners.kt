package com.alvin.to_doappwithtesting.ui.interfaces

import com.alvin.to_doappwithtesting.data.models.quotes.Quotes

interface QuoteAdapterClickListeners {

    fun deleteClicked(quote: Quotes)

    fun itemClicked()

}