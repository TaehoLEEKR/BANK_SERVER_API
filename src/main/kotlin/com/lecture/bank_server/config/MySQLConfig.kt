package com.lecture.bank_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class MySQLConfig (
    @Value("\${datasource.mysql.url}") private val url: String,
    @Value("\${datasource.mysql.username}") private val username: String,
    @Value("\${datasource.mysql.password}") private val password: String,
    @Value("\${datasource.mysql.driver-class-name}") private val driverClassName: String
){
    // 트랜잭션 매니저 추가적인 빈을 등록하는 경우에 대해 대비하기 위해서
    @Bean
    fun dataSource() : DataSource{
        val dataSource = DriverManagerDataSource(url, username, password)
        dataSource.setDriverClassName(driverClassName)
        return dataSource
    }

    @Bean
    fun transactionManager(dataSource: DataSource) : PlatformTransactionManager{
        return DataSourceTransactionManager(dataSource)
    }
}