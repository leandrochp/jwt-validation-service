package com.github.leandrochp.jwtvalidationservice.application.web.request

object JwtPayloadRequestMock {

    fun validTokenPayload() = """
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
        }
    """.trimIndent()

    fun invalidTokenPayload() = """
        {
            "token": "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
        }    
    """.trimIndent()

    fun tokenWithNumberClaimNamePayload() = """
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs"
        }
    """.trimIndent()

    fun tokenWithMoreClaimsPayload() = """
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY"
        }
    """.trimIndent()

    fun tokenWithInvalidPrimeNumberPayload() = """
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiU2VlZCI6IjE0NjIwIiwiTmFtZSI6IlZhbGRpciBBcmFuaGEifQ.pKUpevtQWf-noMGnAcNn0a4MYhisq2m5wpprkCMd8fI"
        }
    """.trimIndent()

    fun tokenWithNameMoreThanLengthPayload() = """
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiU2VlZCI6IjE0NjI3IiwiTmFtZSI6IlZhbGRpciBBcmFuaGEgVmFsZGlyIEFyYW5oYSBWYWxkaXIgQXJhbmhhIFZhbGRpciBBcmFuaGEgVmFsZGlyIEFyYW5oYSBWYWxkaXIgQXJhbmhhIFZhbGRpciBBcmFuaGEgVmFsZGlyIEFyYW5oYSBWYWxkaXIgQXJhbmhhIFZhbGRpciBBcmFuaGEgVmFsZGlyIEFyYW5oYSBWYWxkaXIgQXJhbmhhIFZhbGRpciBBcmFuaGEgVmFsZGlyIEFyYW5oYSBWYWxkaXIgQXJhbmhhIFZhbGRpciBBcmFuaGEgVmFsZGlyIEFyYW5oYSBWYWxkaXIgQXJhbmhhIFZhbGRpciBBcmFuaGEifQ.XlbkOniWqzfvX65nvSpuL2A4mSP8axdBdMXwUtsC9kU"
        }
    """.trimIndent()

    fun tokenWithInvalidRolePayload() = """
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiSW50ZXJuIiwiU2VlZCI6IjE0NjI3IiwiTmFtZSI6IlZhbGRpciBBcmFuaGEifQ.uFDGL9tsw1leuSIp-5nPLoAKHKmjLwqNubFaPEgUJNw"
        }
    """.trimIndent()

    fun tokenEmptyPayload() = """
        {
            "token": "" 
        }
    """.trimIndent()

}