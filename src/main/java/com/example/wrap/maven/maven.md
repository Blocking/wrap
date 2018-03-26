#maven
  ###Snapshot Dependencies
    快照版本依赖保证你每次build项目时，总是从目标服务器下载最新的jar包依赖版本到你的本地仓库
    我们需要做的仅仅只是在version后追加 -SNAPSHOT 这样maven就会知道他是一个快照版本
    如：<version>1.0-SNAPSHOT</version>
  ###Maven Repositories
    maven仓库是一个存储maven jar包及相应信息依赖的文件夹 在maven的定义中分为三种仓库类型
    -本地仓库
    -中央仓库
    -远程仓库(项目中红)
    maven检索jar包依赖的先后顺序是 本地仓库>中央仓库>远程仓库
    本地仓库是在我们开发者本地电脑上，中央仓库是maven官方指定的远程仓库，远程仓库是我们自己开发者指定的仓库地址，
    一般是我们自己开发的jar包依赖，供我们的同事使用。
    1.本地仓库地址 （在settings.xml中设置）
    2.中央仓库地址 内置
    3.远程仓库地址 一般在项目pom中定义
        如：
            repositories>
               <repository>
                   <id>jenkov.code</id>
                   <url>http://maven.jenkov.com/maven2/lib</url>
               </repository>
            </repositories>
  
  ###定义pom
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                          http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.jenkov</groupId>
        <artifactId>hello-world</artifactId>
        <version>1.0.0</version>
    </project>
    
    如上所述是一个基本的pom结构
    groupId 用来定义你的组织机构名称 比如你的公司或者开源组织
    artifactId 用来定义你的项目名称
    version 即是版本号
    
    其它的项目可以通过上述三个关键描述 定位引用你的项目
    
    1、创建项目根目录 
    2、创建pom定义项目描述
    3、创建项目标准结构
        - src
          - main
            - java
            - resources
            - webapp
          - test
            - java
            - resources
        
        - target
    
    
    
    