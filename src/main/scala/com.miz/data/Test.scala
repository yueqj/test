package com.miz.data

/**
  * 测试scala函数
  */
object Test {

  def main(args: Array[String]): Unit = {

    //递归(阶乘)
//    for (i <- 1 to 10){
//      println(i+"的阶乘是:"+factorial(i))
//    }

    //高阶函数
//    println(apply(layout,10))

    //匿名函数
//    println(sum(3,4))

    //函数柯里化
    var a = 12
    var b = 13
    println(add(a)(b))
  }

  //递归(阶乘)
  def factorial(n : BigInt) : BigInt = {
    if (n == 1){
      1
    }else{
      n * factorial(n-1)
    }
  }

  //高阶函数(可以使用其他函数作为参数或结果)
  def apply(f : Int => String,v :Int) = f(v)
  def layout[A](x : A) = "["+x.toString+"]"

  //匿名函数(=> 左边是参数列表,右边是函数)
  var sum = (a : Int, b : Int) => a + b

  /**
    * 函数柯里化
    * @param a 传入int类型的a,返回一个匿名函数,(b : Int) => a+b
    * @param b 传入b
    * @return a+b
    */
  def add (a : Int)(b : Int) = {
    a + b
  }
}
