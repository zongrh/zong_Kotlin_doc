/*
 * @Author nanzong
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

//----------Kotlin 数据类与密封类
//-----数据类
//Kotlin 可以创建一个只包含数据的类，关键字为 data：
data class User(val name: String, val age: Int)

//编译器会自动的从主构造函数中根据所有声明的属性提取以下函数：
//equals()//hashCode()
//toString()格式如"User(val name:String,val age:Int)"
//componentN() functions对应属性，按声明顺序排列
//copy()函数


//如果这些函数在类中被定义了，或者从超类中继承而来，就不再会生成
//为了保证生成代码的一致性以及有意义，数据类都要满足以下条件：

//1.主构造函数至少包含一个参数
//2.所有的主构造函数的参数必须标识为val或者var
//3.数据类不可以被声明为abstract,open,sealed或者inner
//4.数据类不能继承其它类，(但是可以实现接口)

//-----复制
//复制使用 copy() 函数，我们可以使用该函数复制对象并修改部分属性,
//对于上文的 User 类，其实现会类似下面这样：
//data class User9_1(name: String, age: Int)
//fun copy(name: String = this.name, age: Int = this.age) = User9_1(name, age)


//-----实例
//使用copy类复制User数据类，并修改age属性
data class User9_1(val name: String, val age: Int)

fun main9_1() {
    val jack = User9_1(name = "jack", age = 1)
    val jack2 = User9_1("无极剑圣", 27)
    println(jack)
    println(jack2)
    val oldjack = jack.copy(age = 89)
    val othername = jack.copy("德玛西亚")
    println(oldjack)
    println(othername)
}
//输出
//User9_1(name=jack, age=1)
//User9_1(name=无极剑圣, age=27)
//User9_1(name=jack, age=89)
//User9_1(name=德玛西亚, age=1)

//-----数据类以及解构声明
//组件函数允许数据类在解构声明中使用
fun main9_2() {
    val jane = User9_1("jane", 35)
    val (name, age) = jane
    println("$name,$age years of age ")

}
//输出：jane,35 years of age


//-----标准数据类
//标准库提供了Pair和Triple，在大多数情形中，命名数据类是更好的设计选择，
//因为这样可读性更强而且提供了有意义的名字和属性


//-----密封类
//密封类用来表示受限的类继承结构：
//当一个值为有限的几种类型，而不能有其他类型时，在某种意义上他们是枚举类的拓展：
//枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例

//声明一个密封类，用sealed 修饰类，密封类可以有子类，但是所有的子类都必须内潜在密封类中
//seald不能修饰 interface，abstract class （会报waning，但不会出现编译错误）

sealed class Expr9_1
data class Const(val number: Double) : Expr9_1()
data class Sum(val e1: Expr9_1, val e2: Expr9_1) : Expr9_1()
object NotaNumber : Expr9_1()

fun eval(expr91: Expr9_1): Double = when (expr91) {
    is Const -> expr91.number
    is Sum -> eval(expr91.e1) + eval(expr91.e2)
    NotaNumber -> Double.NaN
    // else ->  不需要 'else'子句，因为我们被覆盖住所有的情况
}


//我的理解密封类就是一种专门用来配合when 语句使用的类：
//举个例子，假如在Android中我们有一个view,我们现在想通过when语句设置针对view进行两种操作
//显示和隐藏，那我们可以这样做

//sealed class Uiop9_1 {
//    object Show : Uiop9_1()
//    object Hide : Uiop9_1()
//}

//fun execute9_1(view: View, op: Uiop9_1) = when (op) {
//    Uiop9_1.Show -> view.visibility = View.VISIBLE
//    Uiop9_1.Hide -> view.visibility = View.GONE
//}

//以上功能其实完全可以用枚举实现，但是如果我们现在想加两个操作：
//水平平移和纵向平移，并且还要携带一些数据，
//比如平移了多少距离，平移过程的动画类型等数据，
//用枚举显然就不太好办了，这时密封类的优势就可以发挥了，例如：
//sealed class UIop9_2 {
//    object Show : UIop9_2()
//    object Hide : UIop9_2()
//    class TranslateX(val px: Float) : UIop9_2()
//    class TranslateY(val px: Float) : UIop9_2()
//}
//
//fun execute(view: View, op: UIop9_2) = when (op) {
//    UIop9_2.Show -> view.visibility = View.VISIBLE
//    UIop9_2.Hide -> view.visibility = View.GONE
//    // 这个 when 语句分支不仅告诉 view 要水平移动，还告诉 view 需要移动多少距离，
//    // 这是枚举等 Java 传统思想不容易实现的
//    is UIop9_2.TranslateX -> view.translateX = op.px
//    is UIop9_2.TranslateY -> view.translateY = op.px
//}
//以上代码中，TranslateX 是一个类，它可以携带多于一个的信息，
// 比如除了告诉 view 需要水平平移之外，还可以告诉 view 平移多少像素，
// 甚至还可以告诉 view 平移的动画类型等信息，我想这大概就是密封类出现的意义吧。


//除此之外，如果 when 语句的分支不需要携带除“显示或隐藏view之外的其它信息”时
// （即只需要表明 when 语句分支，不需要携带额外数据时），用 object 关键字创建单例就可以了，
// 并且此时 when 子句不需要使用 is 关键字。只有需要携带额外信息时才定义密封类的子类，
// 而且使用了密封类就不需要使用 else 子句，每当我们多增加一个密封类的子类或单例，
// 编译器就会在 when 语句中给出提示，可以在编译阶段就及时发现错误，
// 这也是以往 switch-case 语句和枚举不具备的功能。
//最后，我们甚至可以把这一组操作封装成一个函数，以便日后调用，如下：

//sealed class UIop9 {
//    object Show : UIop9()
//    object Hide : UIop9()
//    class TranslateX(val px: Float) : UIop9()
//    class TranslateY(val px: Float) : UIop9()
//}
//
//// 先封装一个UI操作列表
//class Ui(val uiOps: List = emptyList()) {
//    operator fun plus(uiOp: UiOp) = Ui(uiOps + uiOp)
//}
//
//// 定义一组操作
//val ui = Ui() +
//        UiOp.Show +
//        UiOp.TranslateX(20f) +
//        UiOp.TranslateY(40f) +
//        UiOp.Hide
//// 定义调用的函数
//fun run(view: View, ui: Ui) {
//    ui.uiOps.forEach { execute(view, it) }
//}
//
//run(view, ui) // 最终调用

