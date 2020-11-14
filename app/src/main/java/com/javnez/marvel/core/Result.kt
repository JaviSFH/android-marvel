package com.javnez.marvel.core

sealed class Result<out Data> {

    class Success<out Data>(val data: Data) : Result<Data>()
    class Error(val failure: Failure) : Result<Nothing>()
}