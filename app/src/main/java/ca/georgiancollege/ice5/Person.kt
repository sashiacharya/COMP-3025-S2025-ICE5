package ca.georgiancollege.ice5

import android.util.Log

class Person(private var name: String, private var age: Float) {

    // Properties with custom getters and setters
    var Name: String
        get() = name
        set(value) {
            if (value.isNotBlank()) name = value
        }

    var Age: Float
        get() = age
        set(value) {
            if (value >= 0) age = value
        }

    fun sayHello() {
        Log.i("Person","$name says Hello!")
    }

    // Override toString to match constructor parameter order
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}
