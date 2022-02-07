package com.alvin.to_doappwithtesting.utils

import com.alvin.to_doappwithtesting.data.models.quotes.Quotes

object FakeQuotesList {

    fun fakeQuoteList(value: Int = 1): List<Quotes> {
        val list = mutableListOf<Quotes>()
        (0..value).forEach {
            list.add(Quotes(it, "text $it", "author $it", false, it))
        }
        return list
    }

    fun fakeQuoteItem(): Quotes {
        return Quotes(1, "text $1", "author $1", false, 1)
    }

}