package api

import model.User

interface RpcData

data class AuthorizationResponse(val user: User? = null, val error: String? = null) : RpcData