/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

//Kotlin 泛型
//泛型，即 "参数化类型"，将类型参数化，可以用在类，接口，方法上。
//与 Java 一样，Kotlin 也提供泛型，为类型安全提供保证，消除类型强转的烦恼。
//声明一个泛型类:

class Box<T>(t: T) {
    var value = t
}

fun main10_1() {
    var boxInt = Box<Int>(1)
    var boxString = Box<String>("apple")
    println(boxInt.value)
    println(boxString.value)
}
//输出：
//1
//apple


//定义泛型类型变量，可以完整的写出类型参数，如果编译器可以自动推定类型参数，也可以省略类型参数
//Kotlin泛型函数的声明与java相同，类型参数要放在函数名的前面
fun <T> boxIn(value: T) = Box(value)
fun main10_2() {
    val box4 = boxIn(1)
    val box5 = boxIn("德玛西亚")
    println(box4.value)
    println(box5.value)
}
//输出：
//1
//德玛西亚


//在调用泛型函数时，如果可以推断出类型参数，可以省略泛型参数
//以下实例创建了泛型函数doPrain,函数根据传入的类型不同做相同处理
fun <T> doPrint(const: T) {
    when (const) {
        is Int -> println("整数数字为： $const")
        is String -> println("字符串转换大小写： ${const.toUpperCase()}")
        else -> println("不是整型，也不是字符串： $const")
    }
}

fun main10_3() {
    val age = 23
    val name = "无极剑圣"
    val bool = true
    doPrint(name)
    doPrint(age)
    doPrint(bool)
}
//输出结果：
//字符串转换大小写： 无极剑圣
//整数数字为： 23
//不是整型，也不是字符串： true


//-----泛型约束
//我们可以使用泛型约束来设定一个给定参数允许的类型，
//kotlin中使用：对泛型的类型上限进行约束
//最常见的约束是上界(upper bound):
fun <T : Comparable<T>> sort(list: List<T>) {
    //...
    println(list.toString())
}

fun main10_4() {
    sort(listOf<String>("无极剑圣", "武器大师"))
    sort(listOf("无极剑圣", "武器大师"))
    sort(listOf(1, 3, 2, 4)) // OK。Int 是 Comparable<Int> 的子类型
//    sort(listOf(HashMap<Int,String>())) // 错误：HashMap<Int, String> 不是 Comparable<HashMap<Int, String>> 的子类型
}

//默认的上界是Any?
//对于多个上界约束条件，可以用where子句
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter {
        it > threshold
    }.map { it.toString() }
}

//-----形变
//Kotlin中没有通配符类型，他有两个其它的东西：
//声明处型变（declaration-site variance）与类型投影（type projections）。
//----声明处型变
//声明处的类型变异使用协变注解修饰符：in、out，消费者 in, 生产者 out。
//使用 out 使得一个类型参数协变，协变类型参数只能用作输出，可以作为返回值类型但是无法作为入参的类型：
//定义一个支持协变的类
class Student10_1<out A>(val a: A) {
    fun mPrintText(): A {
        return a
    }
}

fun main10_5() {
    var sString: Student10_1<String> = Student10_1("A")
    var sAny: Student10_1<Any> = Student10_1<Any>("B")
    var sInt: Student10_1<Int> = Student10_1<Int>(1)
    println(sString.mPrintText())
    println(sAny.mPrintText())
    println(sInt.mPrintText())
}
//输出：
//A
//B
//1

//in 使得一个类型参数逆变，
//逆变类型参数只能用作输入，可以作为入参的类型但是无法作为返回值的类型：
class Student10_2<in A>(a: A) {
    fun mPrintText(a: A) {
        println(a)
    }
}

fun main10_6() {
    var mStudent = Student10_2("德玛西亚")
    var mStudent2 = Student10_2("无极剑圣")
    mStudent2 = mStudent
}


//-----星号投射
//有些时候, 你可能想表示你并不知道类型参数的任何信息, 但是仍然希望能够安全地使用它.
//这里所谓"安全地使用"是指, 对泛型类型定义一个类型投射, 要求这个泛型类型的所有的实体实例,
//都是这个投射的子类型。

//对于这个问题, Kotlin 提供了一种语法, 称为 星号投射(star-projection):

//1.假如类型定义为 Foo<out T> , 其中 T 是一个协变的类型参数,
// 上界(upper bound)为 TUpper ,Foo<> 等价于 Foo<out TUpper> .
// 它表示, 当 T 未知时, 你可以安全地从 Foo<> 中 读取TUpper 类型的值.

//2.假如类型定义为 Foo<in T> , 其中 T 是一个反向协变的类型参数,
// Foo<> 等价于 Foo<inNothing> . 它表示, 当 T 未知时, 你不能安全地向 Foo<> 写入 任何东西.

//3.假如类型定义为 Foo<T> , 其中 T 是一个协变的类型参数,
// 上界(upper bound)为 TUpper , 对于读取值的场合,
// Foo<*> 等价于 Foo<out TUpper> , 对于写入值的场合, 等价于 Foo<in Nothing> .


//如果一个泛型类型中存在多个类型参数, 那么每个类型参数都可以单独的投射.
// 比如, 如果类型定义为interface Function<in T, out U> , 那么可以出现以下几种星号投射:

//1. Function<*, String> , 代表 Function<in Nothing, String> ;
//2. Function<Int, *> , 代表 Function<Int, out Any?> ;
//3. Function<, > , 代表 Function<in Nothing, out Any?> .
//注意: 星号投射与 Java 的原生类型(raw type)非常类似, 但可以安全使用


//关于星号投射，其实就是*代指了所有类型，相当于Any?
//补个例子方便理解：
class A<T>(val t: T, val t2: T, val t3: T)
class Apple(var name: String)

fun main() {
    //实用类
    val a1: A<*> = A(12, "string", Apple("苹果"))
    val a2: A<Any?> = A(12, "String", Apple("橘子"))
    val a3: A<Any?> = A(12, "String", null)
    val apple=a1.t3 //参数类型为Any
    val apple2 = apple as Apple //强转成Apple类
    println(apple2.name)

    //使用数组
    val l: ArrayList<*> = arrayListOf("", 1, 2, 1.2f, Apple("香蕉"))
    for (item in l) {
        println(item.toString())
    }
}










