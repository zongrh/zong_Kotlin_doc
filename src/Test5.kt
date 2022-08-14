/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
//----------Kotlin 类和对象
//-----类定义
//Kotlin 类可以包含：构造函数和初始化代码块、函数、属性、内部类、对象声明。
//Kotlin 中使用关键字 class 声明类，后面紧跟类名：
class Test5 {//类名为Test5
    //大括号内是类体构成
}

//我们也可以定义一个空类
class Empty
class Empty2

//可以在类中定义成员函数
class Run {
    fun foo() {
        println("foo/bar")
    }//成员函数
}

//-----类的属性
//---属性定义
//类的属性可以用关键字var声明可变的，否则使用只读关键字val声明为不可变的
class Test5_2 {
    var name: String = ""
    var url: String = ""
    var city: String = ""
}

//我们可以像普通函数那样使用构造函数创建实体类实例
fun main5_1() {
    val test5 = Test5_2() // kotlin 中没有 new 关键字
}

// Kotlin 中的类可以有一个主构造器，以及一个或者多个构造器，
// 主构造器是头部的一部分，位于类名之后：
class Person constructor(firsName: String) {}

//如果主构造器中没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略
class Person2(firsName: String) {}

//---getter 和 setter
//属性声明的完整语法
//var <propertyName>[: <PropertyName>] [= <property_initializer>]
//[<getter>]
//[<setter>]

//-----实例
class Dog {
    var lastName: String = "zong"
        get() = field.toUpperCase()
        set

    var speed: Int = 100
        get() = field
        set(value) {
            if (value < 20) {
                field = value
            } else {
                field = 0
            }
        }
    var number: Int = -1
    var height: Float = 135.5f
        private set

    var animalName: String = "dog"

    fun animal(name: String) {
        animalName = name
    }

    var sex: String? = "男"
    var age: Int = -1

}

//---测试
fun main5_2() {
    var dog = Dog()
    dog.lastName = "nanzong"
    println("dog lastName : ${dog.lastName}")

    dog.speed = 11
    println("dog speed : ${dog.speed}")

    dog.number = 22
    println("dog number : ${dog.number}")

    println("dog height : ${dog.height}")

}
//输出结果：
//dog lastName : NANZONG
//dog speed : 11
//dog number : 22
//dog height : 135.5

//Kotlin中不能有字段。提供了Backing Fields(后端变量)机制
//备用字段用field关键字声明，field关键词只能用于属性的访问器，如以上实例：
var number: Int = 100
    get() = field // 后端变量
    set(value) {
        if (value > 10) { // 如果传入的值大于 10 返回该值
            field = value
        } else {
            field = -1 // 如果传入的值小于 10 返回 -1
        }
    }

//非空属性在定义的时候初始化，kotlin提供了一种可以延迟初始化的方案，
//使用 lateinit 关键字描述属性：
public class MyTest5 {
    lateinit var dog: Dog
    fun newDog() {
        dog = Dog()
    }

    fun setAnimalName() {
        dog.animal("fox") // dereference directly
    }
}

fun main5_3() {
    var myTest5 = MyTest5()
    myTest5.newDog() //不进行初始化，会报错
    myTest5.dog.lastName = "德玛西亚"
    myTest5.setAnimalName()
    println(myTest5.dog.lastName)
    println(myTest5.dog.animalName)
    println(myTest5.dog.sex)
    myTest5.dog.sex = null
    println(myTest5.dog.sex)
    println(" ${myTest5.dog.age}")
    myTest5.dog.age = 0
    println(myTest5.dog.age)
}
//输出：
//德玛西亚
//fox
//男
//null
//-1
//0

//----------主构造器
//主构造器中不能包含任何代码，初始化代码可以放在初始化代码中，
//初始化代码段使用 init关键字作为前缀：
class Person3 constructor(firsName: String) {
    var name = firsName

    init {
        println("FirstName is $firsName")
    }
}

fun main5_4() {
    var person = Person3("德玛西亚万岁")
    person.name = "无极剑圣"
    println(person.name)
}
//输出：
//FirstName is 德玛西亚万岁
//无极剑圣

//注意:主构造器的参数可以在初始化代码段中使用，也可以在类主体中定义的属性初始化代码中使用
//一种简洁的语法，可以通过主构造器来定义属性并初始化属性值(可以是var or val)
class Dog2(val firsName: String, lastName: String, var age: Int) {}

//-----实例
class People5_1 constructor(name: String) {
    // 大括号是类体构成
    var url: String = "www.demaxiya.com"
    var country: String = "cn"
    var peopleName = name

    init {
        println("初始化 people name : $name")
    }

    fun printTest() {
        println("我是类的函数")
    }

    fun printSiteName() {
        println("peopleName: $peopleName")
    }
}

fun main5_5() {
    val people = People5_1("vn")
    println(people.peopleName)
    println(people.url)
    println(people.country)
    people.printTest()
    people.printSiteName()
}
//输出：
//初始化 people name : vn
//vn
//www.demaxiya.com
//cn
//我是类的函数
//peopleName: vn

//-----次构造函数
//类也可以有二级构造函数，需要添加constructor
//class Person {
//    constructor(parent: Person) {
//        parent.children.add(this)
//    }
//}

//如果类有主构造函数，每个次构造函数都要，
//或直接或间接通过另一个次构造函数代理主构造函数。
//在同一个类中代理另一个构造函数使用 this 关键字：
//class Person(val name: String) {
//    constructor (name: String, age:Int) : this(name) {
//        // 初始化...
//    }
//}

//如果一个非抽象类没有声明构造函数(主构造函数或次构造函数)，
// 它会产生一个没有参数的构造函数。构造函数是 public 。
// 如果你不想你的类有公共的构造函数，你就得声明一个空的主构造函数：
//class DontCreateMe private constructor () {
//}

//注意：在 JVM 虚拟机中，如果主构造函数的所有参数都有默认值，
//编译器会生成一个附加的无参的构造函数，这个构造函数会直接使用默认值。
//这使得 Kotlin 可以更简单的使用像 Jackson 或者 JPA 这样使用无参构造函数来创建类实例的库。
class Customer(val customerName: String = "")


class People5_2 constructor(name: String) {
    // 大括号是类体构成

    var url: String = "wwww.baidu.com"
    var country = "eu"
    var siteName = name

    // 初始化代码
    init {
        println("初始化url: $url")
    }

    //次级构造函数
    constructor(name: String, age: Int) : this(name) {
        println("次级构造函数：name=$name , age=$age")
    }

    fun printTest() {
        println("我是类的函数")
    }
}

fun main5_6() {
    var people52 = People5_2("无极剑圣")
    println(people52.country)
    println(people52.url)
    println(people52.siteName)
    println(people52.siteName)
    people52.printTest()
    println("---------------")
    var people52_2 = People5_2("德玛西亚", 27)
    println(people52_2.country)
    println(people52_2.url)
    println(people52_2.siteName)
    println(people52_2.siteName)
    people52_2.printTest()
}
//输出：
//初始化url: wwww.baidu.com
//eu
//wwww.baidu.com
//无极剑圣
//无极剑圣
//我是类的函数
//---------------
//初始化url: wwww.baidu.com
//次级构造函数：name=德玛西亚 , age=27
//eu
//wwww.baidu.com
//德玛西亚
//德玛西亚
//我是类的函数


//-----抽象类
//抽象是面向对象编程的特征之一，类本身，或类中的部分成员，都可以声明为abstract的，
//抽象成员在类中不存在具体表现
open class Base {
    open fun foo() {}
}

abstract class Derived : Base() {
    override fun foo() {
        super.foo()
    }
}

//-----嵌套类
//我们可以把类嵌套在其他类中，看以下实例：
class Outer constructor(number: String) {   // 外部类
    var mNumber = number

    init {
        val no = mNumber
        println("我是最外层，初始化代码块  no =$no")

    }

    fun printNumber() {
        println("我是最外层，打印的number = $number")
    }

    class Nested(var name: String) {    // 嵌套类
        init {
            println("嵌套类，初始化代码块....")
        }

        fun getName() {
            println("嵌套类，获取到的name = $name")
        }

    }
}

fun main5_7() {
    val demo = Outer.Nested("德玛西亚").getName()
    val demo2 = Outer("111").printNumber()
}
//输出：
//嵌套类，初始化代码块....
//嵌套类，获取到的name = 德玛西亚
//我是最外层，初始化代码块  no =111
//我是最外层，打印的number = 100

//-----内部类
//内部类使用 inner 关键字来表示。
//内部类会带有一个对外部类的对象的引用，所以内部类可以访问外部类成员属性和成员函数。

class Outer2 {
    private val bar: Int = 1
    var v = "成员属性"
    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar  // 访问外部类成员
        fun innerTest() {
            var o = this@Outer2 //获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}

fun main5_8() {
    val demo=Outer2().Inner().foo()
    println(demo)
    val demo2 = Outer2().Inner().innerTest()
//    println(demo2) // 内部类可以引用外部类的成员，例如：成员属性
}
//为了消除歧义，要访问来自外部作用域的 this，
//我们使用this@label，其中 @label 是一个 代指 this 来源的标签。



//-----匿名内部类
//使用对象表达式来创建匿名内部类：
class Test5_1 {
    var v = "成员属性"

    fun setInterFace(test: TestInterFace) {
        test.test()
    }
}

/**
 * 定义接口
 */
interface TestInterFace {
    fun test()
}

fun main() {
    var test=Test5_1()

    test.setInterFace(object : TestInterFace {
        override fun test() {
           println("对象表达式，创建匿名内部类的实例")
        }

    })
}


//-----类的修饰符
//类的修饰符包括 classModifier 和_accessModifier_:
//classModifier: 类属性修饰符，标示类本身特性。

//abstract    // 抽象类
//final       // 类不可继承，默认属性
//enum        // 枚举类
//open        // 类可继承，类默认是final的
//annotation  // 注解类
//accessModifier: 访问权限修饰符

//private    // 仅在同一个文件中可见
//protected  // 同一个文件中或子类可见
//public     // 所有调用的地方都可见
//internal   // 同一个模块中可见

//-----实例
// 文件名：example.kt
//package foo

//private fun foo() {} // 在 example.kt 内可见
//public var bar: Int = 5 // 该属性随处可见
//internal val baz = 6    // 相同模块内可见











