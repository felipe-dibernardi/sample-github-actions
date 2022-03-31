package br.com.fdbst.sample.githubaction.graph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.`__` as underscore
import org.apache.tinkerpop.gremlin.structure.T
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class GraphTraversalTest {

    @Autowired
    private lateinit var g: GraphTraversalSource

    @Test
    fun populateGraph() {

        val id1 = UUID.randomUUID()
        val id2 = UUID.randomUUID()
        val id3 = UUID.randomUUID()

        g.V().drop().iterate()

        g.addV("Partner 1")
            .property(T.id, id1)
            .property("name", "test")
            .property("country", "example")
            .property("employees", 12345)
            .iterate()

        g.addV("Custom Label")
            .property(T.id, id2)
            .property("name", "Custom id vertex 1")
            .iterate()
        g.addV("Custom Label")
            .property(T.id, id3)
            .property("name", "Custom id vertex 2")
            .iterate()

        g.addE("Edge Label")
            .from(underscore.V<UUID>(id2))
            .to(underscore.V<UUID>(id3))
            .iterate()

        val t: GraphTraversal<*, *> = g.V().limit(3).elementMap<Any>()

        t.forEachRemaining { println(t.toList()) }


    }

}
