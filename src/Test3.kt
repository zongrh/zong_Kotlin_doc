/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

//Kotlin 条件控制
//----------IF 表达式
//一个 if 语句包含一个布尔表达式和一条或多条语句。

//fun main3_1() {
//    //传统用法
//    var max = a
//    if (a < b) max = b
//
//    //使用else
//    var max: Int
//    if (a > b) {
//        max = a
//    } else {
//        max = b
//    }
//
//    //作为表达式
//    val max = if (a > b) a else b
//    //我们也可以吧IF 表达式的结果赋值给一个变量
//    val max = if (a > b) {
//        print("choose a")
//        a
//    } else {
//        print("choose b")
//        b
//    }
//
//    //这也说明我们不需要像java那种一个三元操作符，因为我们可以使用它简单实现：
//    val c = if (condition) a else b
//}

//----------实例
fun main3_2() {
    var x = 0
    if (x > 0) {
        println("x > 0")
    } else if (x == 0) {
        println("x == 0")
    } else {
        println("x < 0")
    }
    var a = 1
    var b = 2
    val c = if (a >= b) a else b
    println("c 的值为 $c")
}
//输出的结果：
//x == 0
//c 的值为 2

//----------使用区间
//使用in 运算符来检测某个数字是否在指定区间内，区间格式为:x..y
//实例
fun main3_3() {
    val x = 5
    val y = 9
    if (x in 1..9) {
        println("x 在区间内  x = $x")
    }
}
//输出结果：x 在区间内  x = 5


//----------When 表达式
//when 将它的参数和所有的分支条件顺序比较，直到某个分支满足条件。
//when 既可以被当做表达式使用也可以被当做语句使用。如果它被当做表达式，
//     符合条件的分支的值就是整个表达式的值，如果当做语句使用， 则忽略个别分支的值。

//when 类似其他语言的 switch 操作符。其最简单的形式如下：
fun main3_4() {
    when (5) {
        1 -> println("x == 1 ")
        2 -> println("x == 2")
        else -> {
            println("x 不是 1 ，也不是 2")
        }
    }
}

//在when 中 else 同 switch 的 default.
//如果其他分支都不满足条件会将求值else分支
//如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号隔开
fun main3_5() {
    var a = 5
    when (a) {
        0, 1 -> println(" a==0  or a==1")
        else -> println("other  a =$a")
    }
}

//我们可以检测一个值在(in) 或者不在(!in) 一个区间或者集合中：
fun main3_6() {
    val a = 5
    when (a) {
        in 1..10 -> println("a=$a is in the range")
        2 -> println("$a is valid")
        !in 10..20 -> println(" a is outside the range")
        else -> println("none of the above")
    }
}

//另一种可能性是检测一个值是(is)或者不是(!is)一个特定类型的值，
//注意：由于智能转化，你可以访问该类型的方法和属性而无需任何额外的检测
fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> println(false)
}

fun main3_7() {
    hasPrefix(2)
}


//when也可以用来取代 if - else if链，如果不提供参数，
//所有分支条件都是简单的布尔表达式，当一个分支的条件为真时则执行该分支：
//fun main3_8() {
//    when {
//        x.isOdd -> println("x is odd")
//        x.isEven() -> println("x is even")
//        else-> println("x is funny")
//    }
//}
//----------实例
fun main3_9() {
    val x = 0
    when (x) {
        0, 1 -> println("x == 0  or  x == 1")
        else -> println("otherwise")
    }
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {//注意这个代码块
            println("x!=1 ,x!=2")
        }
    }
    when (x) {
        in 0..10 -> println("x 在该区间范围内")
        else -> println("x 不在该区间范围内")
    }
}
//输出结果：
//x == 0  or  x == 1
//x!=1 ,x!=2
//x 在该区间范围内

//when 中使用 in 运算符来判断集合内是否包含某实例
fun main() {
    val item = setOf("apple", "banana", "kiwi", true)
    when {
        "orange" in item -> println("juicy")
        "apple" in item -> println("apple is fine too")
        "kiwi" in item -> println("is true")
    }
}
//输出结果：
//apple is fine too















