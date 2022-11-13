package com.rollingpaper.ggeujeogggeujeog.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.rollingpaper.ggeujeogggeujeog.common.constant.DataSourceConst;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile({"local", "prod"})
public class DbReplicationConfig {

	private static final String MASTER = "masterDataSource";
	private static final String SLAVE = "slaveDataSource";
	private static final String ROUTE = "routingDataSource";

	@Bean(MASTER)
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource masterDataSource() {
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(SLAVE)
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slaveDataSource() {
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(ROUTE)
	@DependsOn({MASTER, SLAVE})
	public RoutingDataSource routingDataSource(
		@Qualifier("masterDataSource") DataSource masterDataSource,
		@Qualifier("slaveDataSource") DataSource slaveDataSource
	) {
		RoutingDataSource routingDataSource = new RoutingDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DataSourceConst.MASTER, masterDataSource);
		dataSourceMap.put(DataSourceConst.SLAVE, slaveDataSource);
		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(masterDataSource);

		return routingDataSource;
	}

	@Primary
	@Bean
	@DependsOn(ROUTE)
	public DataSource dataSource(
		@Qualifier("routingDataSource") RoutingDataSource dataSource
	) {
		return new LazyConnectionDataSourceProxy(dataSource);
	}

	class RoutingDataSource extends AbstractRoutingDataSource {

		@Override
		protected Object determineCurrentLookupKey() {
			return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ?
				DataSourceConst.SLAVE : DataSourceConst.MASTER;
		}
	}
}
