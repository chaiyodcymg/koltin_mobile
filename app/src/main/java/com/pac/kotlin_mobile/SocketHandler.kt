package com.pac.kotlin_mobile


import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler  {

    lateinit var mSocket: Socket


    fun setSocket() {
        try {

            val url = URL.URL_API
            mSocket = IO.socket(url)
        } catch (e: URISyntaxException) {

        }
    }


    fun getSocket(): Socket {
        return mSocket
    }


    fun establishConnection() {
        mSocket.connect()
    }


    fun closeConnection() {
        mSocket.disconnect()
    }
}