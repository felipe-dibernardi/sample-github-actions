package br.com.fdbst.sample.githubaction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChirpHelloWorldApplication

fun main(args: Array<String>) {
    runApplication<ChirpHelloWorldApplication>(*args)
}
