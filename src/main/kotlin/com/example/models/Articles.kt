package com.example.models

import org.jetbrains.exposed.sql.Table

data class Article(val id: Long, val title: String, val body: String)

object Articles : Table() {
    val id = long("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    override val primaryKey = PrimaryKey(id)
}

interface ArticlesRepository {
    suspend fun allArticles(): List<Article>
    suspend fun addNewArticle(title: String, body: String): Article?
}