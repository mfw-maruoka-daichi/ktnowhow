package com.moneyforward.ktnowhow.rest

import com.moneyforward.ktnowhow.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class UserRouter(private val userService: UserService) {

    @Bean
    fun userRouters() = coRouter {
        "/users".nest {
            GET("") {
                // UserHandlerとか作ってDIするのが良さそう userHandler::getUsersとかで流すだけ
                // todo Resultとかで
                try {
                    // DIできることの確認がやりたくてGraphQL用のServiceを無理やり使ってるだけ
                    // val resUsers = userService.users(100, null, null, null).edges.map { edge ->
                    //     edge.node.let { ResponseUser(it.id.value, it.property.name) }
                    // }
                    val resUsers = listOf(ResponseUser("1", "user1"), ResponseUser("2", "user2"))

                    ServerResponse
                        .ok()
                        .json() // = .contentType(MediaType.APPLICATION_JSON)
                        // .bodyValueAndAwait(ObjectMapper().writeValueAsString(resUsers)) // springがjacksonに依存しているのでjacksonで
                        .bodyValueAndAwait(resUsers) // (data )classならjsonに変換される
                } catch (a: Exception) {
                    // ServerResponse.internalServerError()とか500系返すfunctionは用意されてない
                    ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).buildAndAwait()
                    // こういうやり方もできるが、基本的にExceptionHandlerとかでやるんだろう
                }
            }
            GET("/{id}") {
                val userId = it.pathVariable("id")
                val resUser = ResponseUser(userId, "sampleName")
                ServerResponse
                    .ok()
                    // .json().bodyValueAndAwait(ObjectMapper().writeValueAsString(resUser)) // なくても(data )classならjsonに変換される
                    // とはいえ.json()は書いたほうが良いと思う
                    .bodyValueAndAwait(resUser)
            }
            POST("/") { request ->
                if (!validate(request)) return@POST ServerResponse.badRequest().buildAndAwait()

                TODO()
            }
            PUT("/{id}") {
                val userId = it.pathVariable("id")
                ServerResponse.notFound().buildAndAwait()
            }
        }
    }

    private fun validate(request: ServerRequest): Boolean {
        return false
    }

    private data class ResponseUser(val id: String, val name: String)
}