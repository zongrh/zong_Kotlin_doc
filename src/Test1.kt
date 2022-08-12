import javax.print.DocFlavor

/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/


// ------------ 函数定义
// 函数定义使用关键字 fun，参数格式为：参数 : 类型
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 表达式作为函数体，返回类型自动推断
fun sum2(a: Int, b: Int) = a + b

// public 方法则必须明确写出返回类型
public fun sum3(a: Int, b: Int): Int = a + b

// 无返回值的函数(类似java中的void)
fun printSum(a: Int, b: Int): Unit {
    println(a + b)
}

// 如果是返回 Unit类型，则可以省略(对于public方法也是这样)
public fun printSum2(a: Int, b: Int) {
    println(a + b)
}

// 可变长参数函数
fun vars(vararg vararg: Int) {
    for (v in vararg) {
        print(v)
    }
}

// 测试
fun main1() {
    vars(1, 3, 4, 5, 2) // 输出13452
}

// ------------ 定义常量与变量
//可变变量定义：var关键字
//var <标识符> : <类型> = <初始化值>

//不可变量定义：val关键字，只能赋值一次的变量(类似java中的final修饰的变量)
//val <标识符>: <类型> = <初始化值>
val a: Int = 1
val b = 1   //系统自动推断变量类型为INT

fun main2() {
    val c: Int
    c = 2  //如果不在声明时初始化，必须提供变量类型
    print(c)
}

fun main3() {
    var x = 5// 系统自动推断变量类型为INT
    x += 1 //变量可修改
    print(x)
}

//------ 注释
//Kotlin 支持单行和多行注释，实例如下：
// 这是一个单行注释
/* 这是一个多行的
   块注释。 */
//与 Java 不同, Kotlin 中的块注释允许嵌套。

//----- 字符串模板
//$表示一个变量名或者变量值
//$varName 表示变量值
//$(varName.fun()) 表示变量的方法返回值
fun main4() {
    var a = 1
//    模板中的简单名称
    val s1 = "a is $a"
    print(s1)
    a = 2
//    模板中的任意表达式
    val s2 = "${s1.replace("is", "was")} ,but now is $a"
    print(s2)
}

//----- NULL检查机制
//Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式：
//1.字段后加!! 像java一样抛出空异常
//2.字段后加? 可不做处理返回值为null 或者配合 ?: 做空判断处理
fun main5() {
//    类型后边加？表示可为空
    var age: String? = "23"
    println(age)
//    抛出空指针异常
    val ages = age!!.toInt()
    println(ages)
//    不做处理返回null
    val ages1 = age?.toInt()
    println(ages1)
//    age为空返回-1
    val ages2 = age.toInt() ?: -1
    println(ages2)
}
//当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null。
//当 str 中的字符串内容不是一个整数时, 返回 null:
//fun parseInt(str: String): Int? {
//
//}
//以下实例演示如何使用一个返回值可为 null 的函数:

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

//    直接使用 `x * y` 会导致错误, 因为它们可能为 null
    if (x != null && y != null) {
        println(x * y)
    } else {
        println("$arg1 or $arg2 is not number")
    }
}

fun main6() {
    printProduct("5", "4")
    printProduct("c", "4")
    printProduct("a", "b")
    printProduct("0", "3")
}

//或者
fun parseInt2(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct2(arg1: String, arg2: String) {
    val x = parseInt2(arg1)
    val y = parseInt2(arg2)
    if (x == null) {
        println("Wrong number format in arg1 :$arg1")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2 :$arg2")
        return
    }
    // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
    println(x * y)
}

fun main7() {
    printProduct2("3", "9")
    printProduct2("1", "a")
    printProduct2("99", "c")

}

//-----类型检测及自动类型转换
//我们可以使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
//        做过类型判断后，obj会被系统自动转换成String类型
        return obj.length
    }

//    在这里还有一种方法，与Java中instanceof不同，使用!is
//    if (obj !is String) {
//        return xxx
//    }
//这里的obj仍然是Any类型的引用
    return null
}

//或者
fun getStringLength2(obj: Any): Int? {
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}

//甚至
fun getStringLength3(obj: Any): Int? {
    if (obj !is String) {
        return null
    }
    return obj.length
}

//-----区间
//区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
//区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现。以下是使用区间的一些示例:
fun main8() {
    for (i in 1..4) println(i) // 输出“1234”
    for (i in 4..1) println(i) // 什么都不输出
//    if (i in 1..10) { // 等同于 1 <= i && i <= 10
//        println(i)
//    }
//    用step指定步长
    for (i in 1..4 step 2) println(i)
//    用until 函数排除结束元素
    for (i in 1 until 10) println(i)
}
fun main() {
    print("循环输出:")
    for (i in 1..4) print(i)
    print("\n----------------")
    print("设置步长：")
    for (i in 1..4 step 2) print(i) // 输出“13”
    println("\n----------------")
    print("使用 downTo：")
    for (i in 4 downTo 1 step 2) print(i) // 输出“42”
    println("\n----------------")
    print("使用 until：")
    // 使用 until 函数排除结束元素
    for (i in 1 until 4) {   // i in [1, 4) 排除了 4
        print(i)
    }

}











