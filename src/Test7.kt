/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

//----------Kotlin 接口
//Kotlin 接口与 Java 8 类似，使用 interface 关键字定义接口，允许方法有默认实现：
interface MyInterface7_1 {
    fun bar()//已实现
    fun foo() {//未实现
        //可选择的方法体
        println("foo/bar")
    }
}

//----------实现接口
//一个类或对象可以实现一个或者多个接口
class Child7_1 : MyInterface7_1 {
    override fun bar() {
        //方法体
    }
}

//----------实例
interface MyInterface7_2 {
    fun bar()
    fun foo() {
        //可选择的方法体
        println("foo/bar")
    }
}

class Child7_2 : MyInterface7_2 {
    override fun bar() {
        //方法体
        println("bar")
    }
}

fun main7_2() {
    Child7_2().bar()
    Child7_2().foo()
}
//输出结果为：
//bar
//foo

//----------接口中的属性
//接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性：
interface MyInterface7_3 {
    var name: String
}

class MyIml7_3(override var name: String) : MyInterface7_3 {}
class MyIml7_3_2() : MyInterface7_3 {
    override var name: String
        get() = "德玛西亚"
        set(value) {}
}

fun main7_3() {
    MyIml7_3_2().name = "无极剑圣"
    println(MyIml7_3_2().name)
}


//-----实例
interface MyInterface7_4 {
    var name: String //name属性，抽象的
    fun bar()
    fun foo() { //可选择的方法体
        println("foo/bar")
    }
}

class Child7_4 : MyInterface7_4 {
    override var name: String = "德玛西亚" //重写属性
//    override var name: String //重写属性
//        get() = TODO("Not yet implemented")
//        set(value) {}

    override fun bar() {
        println("bar")
    }
}

fun main7_4() {
    Child7_4().foo()
    Child7_4().bar()
    println(Child7_4().name)
}
//输出：
//foo
//bar
//德玛西亚


//----------函数重写
//实现多个接口时，可能会遇到同一种方法继承多个实现的问你题。例如：
interface A7_1 {
    fun foo() { //已实现
        println("A")
    }

    fun bar() //未实现，没有方法体，是抽象的
}

interface B7_1 {
    fun foo() { //已实现
        println("B")
    }

    fun bar() { //已实现
        println("bar")
    }
}

class C7_1 : A7_1 {
    override fun bar() { //重写
        println("bar")
    }
}

class D7_4 : A7_1, B7_1 {
    override fun foo() {
        super<A7_1>.foo()
        super<B7_1>.foo()
    }

    override fun bar() {
        super<B7_1>.bar()
    }
}

fun main() {
    D7_4().foo()
    D7_4().bar()
}
//输出：
//A
//B
//bar


