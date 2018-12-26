# 针对不同数据库设计的 BaseId

所有 `BaseId` 的包名都是 `tk.guozilan.base.model`。

开发时可以直接采用 base-id-vesta，直接通过 vesta 生成全局唯一的 ID。

实际部署时，可以将需要的对应的 jar 包替换到项目的 lib 目录中。

**`BaseId` 中的主键是 `Long id`，所以数据库中必须是 `bigint` 类型，名称为 `id` 的字段作为主键。**

## base-id-mysql

基于 mysql 自增。

## base-id-oracle

基于 oracle 序列，序列 SQL 生成方式参考 [`OracleGenSql`](https://github.com/guozilanTK/base/blob/master/base-id/base-id-oracle/src/main/java/tk/guozilan/base/model/OracleGenSql.java)

这里提供的实现，需要给每个表提供一个 **SEQ_表名大写** 的序列。可以参考实现自己的生成器。

## base-id-sqlserver

基于 sqlserver 数据库自增 id，在 mysql 基础上增加了 `@Column(insertable = false)` 注解。

## base-id-vesta

默认选择的 id 生成方式，可以用于单体应用的生产环境（需要自己测试，本项目仅供参考，不承担任何责任）。

**特别注意**

在 vesta 代码中有如下 VestaService :

```java
@Component
public class VestaService {

```

由于 vesta 用了 `@Component` 注解方式的配置，所以需要扫描 `tk.guozilan.service` 包名。或者你还可以在 Spring XML 中配置下面的 `<bean>`:

```xml
<bean class="tk.guozilan.service.VestaService"/>
```




