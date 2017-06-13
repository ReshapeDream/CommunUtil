
欢迎star和探讨
# CommunUtil
  Used for communication between activity,fragment etc.</br>
  用于activity,fragment等之间通讯</br>
  不仅可以传递变量，还可以传递方法，随时随地的创建一个方法，而在另一个地方调用！</br>
  且不限于activity和fragment</br>
## GetStart
add to your project</br>
添加到你的工程</br>
1.Add it in your root(Project) build.gradle at the end of repositories:</br>
  在project的build.gradle的repositories末尾添加:</br>
```java
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
```
2.Add the dependency to your moudle build.gradle</br>
   在moudle的build.gradle的dependencies下添加:</br>
```java
  dependencies {
    compile 'com.github.ReshapeDream:CommunUtil:v1.0'
  }
``` 
[Link(参考)](https://jitpack.io/#ReshapeDream/CommunUtil/)
## Use
1.add a variable(添加一个变量)
```java
CommunUtil.getInstances().add(variableName,Object);
```
2.add a method (添加一个方法对象)
```java
CommunUtil.getInstances().add(new CommunUtil.MethodClass<T>(methodName,map ){
   @Override
   public T run(Map<String, Object> paramMap) {
      //TODO SOMETHING
      return T;
   }
});
```
3.get a variable(获取一个变量)
```java
T t=CommunUtil.getInstances().getVariable(variableName);
System.out.println("variable:variableName="+t);
```
4.run a method(调用一个方法)
```java
T t=CommunUtil.getInstances().invoke(methodName);
```
ps:T is generics type.(T 是泛型)</br>
   多线程存取要注意存取顺序！
