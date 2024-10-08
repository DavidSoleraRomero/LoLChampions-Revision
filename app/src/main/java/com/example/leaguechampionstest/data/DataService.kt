package com.example.leaguechampionstest.data

interface DataService<T> {

    fun requestAll(): List<T>
    fun requestOne(id: Int): T
    fun create(): T
    fun update(): T
    fun delete(): T

}