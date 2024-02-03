

module com.java {
    // 模块的全限定名
    requires java.base; // 所有模块都会默认依赖java.base模块

    // 导出此模块中的公共API包给其他模块使用
    //exports com.example.my.module.api;

    // 本模块需要依赖的其他模块
    //requires transitive com.other.module; // 可传递依赖，意味着该模块及其依赖也会被自动导入
   // requires static com.static.module; // 静态依赖，仅在编译时使用，不会影响运行时的模块图

    // 对于opens语句，用于允许反射访问模块内部的包
    opens java.util to com.java;
}