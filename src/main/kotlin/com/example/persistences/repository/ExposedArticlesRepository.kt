package com.example.persistences.repository

import com.example.models.Article
import com.example.models.Articles
import com.example.models.ArticlesRepository
import com.example.persistences.DatabaseSingleton.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class ExposedArticlesRepository : ArticlesRepository {
    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body]
    )

    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues
            ?.singleOrNull()
            ?.let(::resultRowToArticle)
    }
}

val repository: ArticlesRepository = ExposedArticlesRepository().apply {
    runBlocking {
        if (allArticles().isEmpty()) {
            addNewArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}