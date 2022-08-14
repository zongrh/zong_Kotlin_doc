import foo.bar.Baz
import foo.bar.goo

/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

//----------Kotlin 扩展
//Kotlin 可以对一个类的属性和方法进行扩展，且不需要继承或使用 Decorator 模式。
//扩展是一种静态行为，对被扩展的类代码本身不会造成任何影响。

//-----扩展函数
//扩展函数可以在已有的类中添加新的方法，不会对原类做修改，扩展函数定义形式：
//fun receiverType.functionName(params){
//    body
//}

//receiverType：表示函数的接收者，也就是函数扩展对象
//functionName：扩展函数的名称
//params：扩展函数的参数，可以为NULL

//-----例如：
class User8_1(var name: String)

fun User8_1.Print() {
    println("用户名: $name")
}

fun main8_1() {
    User8_1("德玛西亚").Print()
    var user = User8_1("无极剑圣")
    user.Print()
}
//输出：
//用户名: 德玛西亚
//用户名: 无极剑圣


//-----例如：
//下面代码为MutableList添加一个swap函数
/**扩展函数 swap ,调换不同位置的值**/
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] //this 对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main8_2() {
    val l = mutableListOf<Int>(1, 2, 3)
    //位置 2 和位置 0的值做了互换
    l.swap(2, 0) //'swap()' 函数内的'this' 将指向 'l'的值
    println(l.toString())
}
//输出：[3, 2, 1]

//-----拓展函数是静态解析的
//扩展函数是静态解析的，并不是接收者类型的虚拟成员，在调用扩展函数时
//具体被调用的是哪个函数，由调用函数的对象表达式来决定，而不是动态类型决定的

open class C8_1
class D8_1 : C8_1()

fun C8_1.foo() = "C"
fun D8_1.foo() = "d"
fun printFoo(c: C8_1) {
    println(c.foo())
}

fun main8_3() {
    printFoo(D8_1())
}
//输出：C

//若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数
class C8_2 {
    fun foo() {
        println("成员函数")
    }
}

fun C8_2.foo() {
    println("扩展函数")
}

fun C8_2.go() {
    println("扩展函数 go ")
}

fun main8_4() {
    C8_2().foo()
    C8_2().go()
}
//输出：
//成员函数
//扩展函数 go

//扩展一个空对象
//在扩展函数内，可以通过 this 来判断接收者是否为NULL这样，即使接收者为NULL，也可以调用扩展函数，
//例如：
fun Any?.toString(): String {
    if (this == null) return "null"
    //空检测之后，"this"会自动转换为非空类型，所以下面的 toString()
    //解析为 Any 类的成员函数
    return toString()
}

fun main8_5() {
    var t = null
    println(t.toString())
}
//输出：null

//-----扩展属性
//除了函数，Kotlin也支持属性对属性进行扩展
val <T>List<T>.lastIndex: Int
    get() = size - 1

//扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中。
// 初始化属性因为属性没有后端字段 (backing field),所以不允许被初始化，只能由显式提供的getter,setter定义
//class Foo
//val Foo.bar = 1
//扩展属性只能被声明为val

//-----伴生对象的扩展
//如果一个类定义有一个伴生对象，你也可以为伴生对象定义扩展函数和属性
//伴生对象通过'类名'，形式调用伴生对象，伴生对象声明的扩展函数，通过用类名限定符来调用
class MyClass8_1 {
    companion object {  //将被称为'Companion'
    }
}

fun MyClass8_1.Companion.foo() {
    println("伴随对象的扩展函数")
}

val MyClass8_1.Companion.no: Int
    get() = 10

fun main8_6() {
    println("no ${MyClass8_1.no}")
    MyClass8_1.foo()
}
//输出：
//no 10
//伴随对象的扩展函数

//-----扩展的作用域
//通常扩展函数或属性定义在顶级包下：
//fun Baz.goo(){
//    ...
//}

//要使用所定义包之外的一个扩展, 通过import导入扩展的函数名进行使用:
fun usage8_1(baz: Baz) {
    baz.goo()
}


//-----扩展声明为成员
//在同一个内部类中，你可以为另一个类声明扩展
//在这个拓展中，有多个隐含的接收者，其中扩展方法定义所在类的实例称为分发接收者
//而扩展方法的目标类型的实例称为扩展接受者


class D8_3 {
    fun bar() {
        println("D bar")
    }
}

class C8_3 {
    fun baz() {
        println("C baz")
    }

    fun D8_3.foo() {
        bar()// 调用 D.bar
        baz()// 调用 C.baz
    }

    fun caller(d83: D8_3) { //在一个类内部你可以为另一个类声明扩展。
        d83.foo()// 调用扩展函数 在类 C 中 -> 创建 D 的扩展
    }
}

fun main8_7() {
    val c: C8_3 = C8_3()
    val d: D8_3 = D8_3()
    c.caller(d)
}
//输出：
//D bar
//C baz
//在 C 类内，创建了 D 类的扩展。此时，C 被成为分发接受者，
//而 D 为扩展接受者。从上例中，可以清楚的看到


//假如在调用某一个函数，而该函数在分发接受者和扩展接受者均存在，
//则以扩展接收者优先，要引用分发接收者的成员你可以使用限定的 this 语法。
class D8_4 {
    fun bar() {
        println("D bar")
    }
}

class C8_4 {
    fun bar() {
        println("C bar")
    }

    fun D8_4.foo() {
        bar() //调用D.bar(),扩展接收者优先
        this@C8_4.bar()    //调用C.bar()
    }

    fun caller(d: D8_4) {
        d.foo() //调用扩展 函数
    }
}

fun main8_8() {
    val c: C8_4 = C8_4()
    val d: D8_4 = D8_4()
    c.caller(d)
}


//以成员的形式定义的扩展函数, 可以声明为 open , 而且可以在子类中覆盖.
// 也就是说, 在这类扩展函数的派 发过程中, 针对分发接受者是虚拟的(virtual),
// 但针对扩展接受者仍然是静态的。


open class D8_5 {
}

class D8_51 : D8_5() {
}

open class C8_5 {
    open fun D8_5.foo() {
        println("D.foo()  in C ")
    }

    open fun D8_51.foo() {
        println("D1.foo()  in C ")
    }

    fun caller(d: D8_5) {
        d.foo() //调用拓展函数
    }
}

class C8_51 : C8_5() {
    override fun D8_5.foo() {
        println("D.foo() in C1")
    }

    override fun D8_51.foo() {
        println("D1.foo in C1")
    }
}

fun main8_9() {
    C8_5().caller(D8_5())  // 输出 "D.foo in C"
    C8_51().caller(D8_5()) // 输出 "D.foo in C1" —— 分发接收者虚拟解析
    C8_5().caller(D8_51()) // 输出 "D.foo in C" —— 扩展接收者静态解析
}


//-----伴生对象
//伴生对象内的成员相当于 Java 中的静态成员，其生命周期伴随类始终，
//在伴生对象内部可以定义变量和函数，这些变量和函数可以直接用类名引用。

//对于伴生对象扩展函数，有两种形式，一种是在类内扩展，一种是在类外扩展，
//这两种形式扩展后的函数互不影响（甚至名称都可以相同），即使名称相同，它们也完全是两个不同的函数，
//并且有以下特点：
//（1）类内扩展的伴随对象函数和类外扩展的伴随对象可以同名，它们是两个独立的函数，互不影响；
//（2）当类内扩展的伴随对象函数和类外扩展的伴随对象同名时，类内的其它函数优先引用类内扩展的伴随对象函数，
//即对于类内其它成员函数来说，类内扩展屏蔽类外扩展；
//（3）类内扩展的伴随对象函数只能被类内的函数引用，不能被类外的函数和伴随对象内的函数引用；
//（4）类外扩展的伴随对象函数可以被伴随对象内的函数引用，；

class MyClass8_2 {
    companion object {
        val myclassField1: Int = 1
        var myclassField2 = "this is  myclassField2"
        fun companionFun1() {
            println("this is 1st companion function")
            foo()
        }

        fun companionFun2() {
            println("this is 2st companion function")
            companionFun1()
        }


    }

    fun MyClass8_2.Companion.foo() {
        println("foo 伴随对象扩展函数(内容)")
    }

    fun test2() {
        MyClass8_2.foo()
    }

    init {
        test2()
    }
}
val MyClass8_2.Companion.no:Int
    get() {
        return 10
    }
fun MyClass8_2.Companion.foo(){
    println("foo 伴随对象外部扩展函数")
}


fun main() {
    println("no:${MyClass8_2.no}")
    println("field1:${MyClass8_2.myclassField1}")
    println("field2:${MyClass8_2.myclassField2}")
    MyClass8_2.foo()
    MyClass8_2.companionFun2()
}
//输出结果：
//no:10
//field1:1
//field2:this is  myclassField2
//foo 伴随对象外部扩展函数
//this is 2st companion function
//this is 1st companion function
//foo 伴随对象外部扩展函数








