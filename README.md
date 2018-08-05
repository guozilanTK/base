# 项目基础

提供所有的基类功能和通用代码。

## base-id 项目

这个项目演示了如何针对不同类型的数据库，详细用法点击 [base-id](https://github.com/guozilanTK/base/tree/master/base-api) 查看。

## base-controller 项目

目前就针对日期在 [`AbstractController`](https://github.com/guozilanTK/base/blob/master/base-controller/src/main/java/tk/guozilan/base/controller/AbstractController.java) 中提供了绑定。

## base-generator 项目

提供了基于 [通用 Mapper](https://github.com/abel533/Mapper) 通用代码生成器的模板。

自己可以根据业务需要设计自己的模板。

## base-service 项目

提供了一个通用的 [`AbstractService`](https://github.com/guozilanTK/base/blob/master/base-service/src/main/java/tk/guozilan/base/service/AbstractService.java) 和
基于 [通用 Mapper](https://github.com/abel533/Mapper) 的 [`GzlMapper`](https://github.com/guozilanTK/base/blob/master/base-service/src/main/java/tk/guozilan/base/service/GzlMapper.java)。

## base-util

通用工具类，目前没有任何代码。

>本项目主要的工具类使用 hutool，无法满足的情况下，在此项目提供。
>
>要尽可能避免在项目中随便实现公共的工具类。

