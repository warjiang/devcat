# 安装thrift-compiler(0.11.0)
mac上可以直接通过`brew install thrift`来安装thrift
也可以从源码安装thrift

# nodejs-nodejs
rpc的客户端和服务端均使用nodejs. 
```
// 将thrift IDL文件翻译成nodejs可用的JS文件
cd /path/to/backend/rpc
thrift -r -o nodejs --gen js:node ./tutorial/tutorial.thrift
// 启动rpc的服务端
cd /path/to/backend/rpc/
node server.js

// 使用rpc的客户端
cd /path/to/backend/rpc/
node client.js
```

# java-java
rpc的客户端和服务端均使用java
```
// 将thrift IDL文件翻译成nodejs可用的JS文件
cd /path/to/backend/rpc
thrift -r -out java/src/java  --gen java ./tutorial/tutorial.thrift
// 启动demo.Server
// 使用demo.Client
```


# nodejs-java
rpc的客户端使用nodejs，rpc的服务端使用java
```
// 根据thrift IDL文件生成目标语言代码过程不再赘述
// 启动demo.Server
// 启动nodejs客户端
```

# java-nodejs
rpc的客户端使用java，rpc的服务端使用nodejs
```
// 根据thrift IDL文件生成目标语言代码过程不再赘述
// 启动nodejs服务端
// 启动demo.Client
```