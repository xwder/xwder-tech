main() {
  var number = 12;
  printInteger(number);

  dynamic name = 'Bob';
  String name2 = 'Bob';
  Object name3 = 'Bob';

  // 未初始化的变量的初始值为null。即使是具有类型未赋值的变量也是 null，
  // 无论什么类型的变量，在没有赋初始值的情况下，默认值为 null。
  // null也是对象，所以未初始化的 Dart 变量都指向null对象
  int lineCount;
  print(lineCount); // null
  assert(lineCount == null);
  print(lineCount == null);

  int num = 1;
  print(num);
  int num2 = num ++ ;
  print(num2);
  print(num);
}

// 定义函数  单行注释
printInteger(int aNumber) {
  // $variableName（或）${expression} 字符串插值：包括字符串文字内部的变量或表达式的字符串。
  print('The number is $aNumber.');
  print('The number is ${aNumber}.');
}
