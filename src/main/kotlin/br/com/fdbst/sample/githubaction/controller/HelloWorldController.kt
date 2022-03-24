package br.com.fdbst.sample.githubaction.controller

import br.com.fdbst.sample.githubaction.dtos.HelloResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/hello")
    fun helloWorld(): ResponseEntity<HelloResponse> {
        return ResponseEntity.ok(HelloResponse("Hi from Chirp"))
    }

}
