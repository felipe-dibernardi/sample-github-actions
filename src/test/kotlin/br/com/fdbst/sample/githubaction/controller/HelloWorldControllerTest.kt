package br.com.fdbst.sample.githubaction.controller

import br.com.fdbst.sample.githubaction.dtos.HelloResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HelloWorldControllerTest {

    @Test
    fun shouldReturnResponseSayingHiOnHelloEndpoint() {
        val controller = HelloWorldController()
        val response = controller.helloWorld()
        val hello : HelloResponse = response.body!!
        assertThat(hello.message).isEqualTo("Hi from Chirp")
    }

}
