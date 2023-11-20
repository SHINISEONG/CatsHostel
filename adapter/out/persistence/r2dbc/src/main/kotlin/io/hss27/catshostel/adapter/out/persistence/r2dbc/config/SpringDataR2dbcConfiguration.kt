package io.hss27.catshostel.adapter.out.persistence.r2dbc.config

import io.hss27.catshostel.adapter.out.persistence.r2dbc.impl.R2dbcCatRepository
import io.r2dbc.mssql.MssqlConnectionConfiguration
import io.r2dbc.mssql.MssqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.r2dbc.core.DatabaseClient

/**
 * 스프링 데이터 R2dbc 기반 데이터 모듈 빈 설정 메타정보(Spring Data R2DBC 활성화 및 빈 설정 오버라이드)
 *
 * @author springrunner.kr@gmail.com
 */
@Configuration
@EnableR2dbcRepositories
class SpringDataR2dbcConfiguration : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory {
        val configuration = MssqlConnectionConfiguration.builder()
            .host("localhost")
            .database("cat_database")
            .username("imtsoft")
            .password("Imt050311")
            .build()

        return MssqlConnectionFactory(configuration)
    }


    @Bean
    fun connectionFactoryInitializer(databaseClient: DatabaseClient) = ConnectionFactoryInitializer().apply {
        setConnectionFactory(databaseClient.connectionFactory)
        setDatabasePopulator(
            ResourceDatabasePopulator(
//                ClassPathResource("schema.sql"),
//                ClassPathResource("data.sql")
            )
        )
    }

    @Bean
    fun r2dbcCatRepository(template: R2dbcEntityTemplate) = R2dbcCatRepository(template)
}
