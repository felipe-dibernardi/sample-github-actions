package br.com.fdbst.sample.githubaction.config

import org.apache.tinkerpop.gremlin.driver.Cluster
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GremlinConfiguration {

    @Value("\${gremlin.host}")
    private lateinit var gremlinHost: String

    @Value("\${gremlin.port}")
    private lateinit var gremlinPort: Number

    @Value("\${gremlin.connection-pool.max}")
    private lateinit var gremlinMaxConnectionPoolSize: Number

    @Value("\${gremlin.in-process-per-connection.max}")
    private lateinit var gremlinMaxInProcessPerConnection: Number

    @Value("\${gremlin.simultaneous-usage-per-connection.min}")
    private lateinit var gremlinMinSimultaneousUsagePerConnection: Number

    @Value("\${gremlin.simultaneous-usage-per-connection.max}")
    private lateinit var gremlinMaxSimultaneousUsagePerConnection: Number

    @Bean
    fun cluster(): Cluster {
        return Cluster.build()
            .addContactPoint(gremlinHost)
            .port(gremlinPort.toInt())
            .maxConnectionPoolSize(gremlinMaxConnectionPoolSize.toInt())
            .maxInProcessPerConnection(gremlinMaxInProcessPerConnection.toInt())
            .minSimultaneousUsagePerConnection(gremlinMinSimultaneousUsagePerConnection.toInt())
            .maxSimultaneousUsagePerConnection(gremlinMaxSimultaneousUsagePerConnection.toInt())
            .create()
    }

    @Bean
    fun g(cluster: Cluster): GraphTraversalSource {
        return traversal().withRemote(DriverRemoteConnection.using(cluster))
    }

}
