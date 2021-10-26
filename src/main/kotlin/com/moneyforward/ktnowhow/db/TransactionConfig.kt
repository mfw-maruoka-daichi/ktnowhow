package com.moneyforward.ktnowhow.db

import javax.sql.DataSource
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.TransactionManagementConfigurer

@Configuration
@EnableTransactionManagement
class TransactionConfig(val dataSource: DataSource) : TransactionManagementConfigurer {
    override fun annotationDrivenTransactionManager(): TransactionManager = SpringTransactionManager(dataSource)
}