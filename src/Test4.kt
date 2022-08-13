/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/


//Kotlin 循环控制
//-----For 循环

//for 循环可以对任何提供迭代器（iterator）的对象进行遍历，语法如下:
//for (item in collection) print(item)

//循环体可以是一个代码块：
//for(item:Int in ints){
//...
//}

//如上所述，for可以循环遍历任何提供迭代器的对象。
//如果你想要通过索引遍历一个数组或者list,你可以这样做：
//for (i in array.indices) {
//    println(array[i])
//}

//注意这种"在区间上遍历"会编译成优化的实现而不会创建额外对象
//或者你可以用库函数 withindex:
//for ((index, value) in array.withindex()) {
//    println("the element at $index is $value")
//}

//---------实例
fun main4_1() {
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println("item = $item")
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}
//输出：
//item = apple
//item = banana
//item = kiwi
//item at 0 is apple
//item at 1 is banana
//item at 2 is kiwi


//-----while 与 do...while 循环
//while是最基本的循环，它的结构为：
//while (布尔表达式) {
//循环内容
//}

//do..while循环对于while语句而言，如果不满足条件，则不能进入循环
//但有时候我们需要及时不满足条件，也至少执行一次
//do..while循环 和while循环相似，不同的是：do..while循环至少会执行一次
//do{
//代码语句
//}while();
//-----实例
fun main4_2() {
    println("-----while 使用------")
    var x = 5
    while (x > 0) {
        x--
        println("x = $x")
    }

    println("-----d0..while 使用------")
    println("-----d0..while 即使不满足条件也会执行一次 ------")
    var y = 5
    do {
        y--
        println("y= $y")
    } while (y > 0)
}
//输出结果：
//-----while 使用------
//x = 4
//x = 3
//x = 2
//x = 1
//x = 0
//-----d0..while 使用------
//-----d0..while 即使不满足条件也会执行一次 ------
//y= 4
//y= 3
//y= 2
//y= 1
//y= 0

//-----返回和跳转
//Kotlin 有三种结构化跳转表达式：

//return.默认从最直接包围他的函数或者匿名函数返回
//break.终止最直接包围它的循环
//continue.继续下一次直接包围它的循环
fun main4_3() {
    for (i in 1..10) {
        if (i == 3) continue // i = 3 时跳出当前循环，继续下一次循环
        println(i)
        if (i > 5) break // i = 6 时 跳出循环
    }
}
//输出结果：
//1
//2
//4
//5
//6


//-----Break 和 Continue 标签
//在Kotlin 中任何表达式都可以用标签(label)来标记。标签格式为标识符后跟 @ 符号
//例如：abc@，footBar@都是有效的标签，要为一个表达式加标签，我们只要在其前边加标签即可
//loop@ for (i in 1..100) {
//println(i)
//}

//现在我们可以用标签限制break,或者continue
fun main4_4() {
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (j == 20) break@loop
            println("j = $j")
        }
        //标签限制的break跳转到刚好位于该标签指定的循环后边的执行点，
        // continue继续标签指定的循环下一次迭代
    }
}

//-----标签处返回
//Kotlin 有函数字面量、局部函数和对象表达式。
// 因此 Kotlin 的函数可以被嵌套。 标签限制的 return 允许我们从外层函数返回。
// 最重要的一个用途就是从 lambda 表达式中返回。回想一下我们这么写的时候：
fun main4_5() {
    var ints = intArrayOf(-1, 0, 1, 2, 3, 4)
    var ints2 = listOf<Int>(-1, 0, 1, 2, 3, 4)
    ints.forEach {
        if (it == 0) return
        println(it)
    }
}
//输出：
//-1

//这个return 表达式从最直接包围它的函数即foo 中返回。
//注意，这种非局部的返回只支持传给内联函数的 lambda 表达式。）
//如果我们需要从 lambda 表达式中返回，我们必须给它加标签并用以限制 return。
fun main4_6() {
    var ints = intArrayOf(-1, 0, 1, 2, 3, 4)
    var ints2 = listOf<Int>(-1, 0, 1, 2, 3, 4)
    ints.forEach lit@{
        if (it == 0) return@lit
        println(it)
    }
}


//它只会从 lambda 表达式中返回。通常情况下使用隐式标签更方便。
//该标签与接受该 lambda 的函数同名。
fun main4_7() {
    var ints = intArrayOf(-1, 0, 1, 2, 3, 4)
    var ints2 = listOf<Int>(-1, 0, 1, 2, 3, 4)
    ints.forEach {
        if (it == 0) return@forEach
        println(it)
    }
}

//或者，我们用一个匿名函数替代 lambda 表达式。
//匿名函数内部的 return 语句将从该匿名函数自身返回
fun main() {
    var ints = intArrayOf(-1, 0, 1, 2, 3, 4)
    fun foo() {
        ints.forEach(fun(value: Int) {
            if (value == 0) return
            println(value)
        })
    }
    println(foo())
}





