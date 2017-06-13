# CommunUtil
## GetStart
add to your project</br>
1.Add it in your root(Project) build.gradle at the end of repositories:
```java
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
```
2.Add the dependency to your moudle build.gradle
```java
  dependencies {
    compile 'com.github.ReshapeDream:CommunUtil:v1.0'
  }
```
[1]: https://jitpack.io/#ReshapeDream/CommunUtil/  
[Link](https://jitpack.io/#ReshapeDream/CommunUtil/)
## Use
1.add a variable
```java
CommunUtil.getInstances().add(variableName,Object);
```
2.add a method 
```java
CommunUtil.getInstances().add(new CommunUtil.MethodClass<T>(methodName,map ){
   @Override
   public T run(Map<String, Object> paramMap) {
      //TODO SOMETHING
      return T;
   }
});
```
3.get a variable
```java
T t=CommunUtil.getInstances().getVariable(variableName);
System.out.println("variable:variableName="+t);
```
4.run a method
```java
T t=CommunUtil.getInstances().invoke(methodName);
```
ps:T is generics type
