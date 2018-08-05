<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tableClass.shortClassName}</title>
    <link rel="stylesheet" href="${r"${pageContext.request.contextPath}"}/static/layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <div class="layui-btn-group demoTable">
        <button class="layui-btn layui-btn-normal" lay-filter="${tableClass.lowerCaseName}Dialog">新增</button>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs12">
            <table class="layui-hide" id="test"></table>
        </div>
    </div>
</div>

<script type="text/html" id="${tableClass.lowerCaseName}Toolbar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div style="display: none;padding: 10px 20px;padding-left: 0;" id="${tableClass.lowerCaseName}Form">
    <form class="layui-form" action="" lay-filter="${tableClass.lowerCaseName}">
        <#if tableClass.pkFields??>
            <#list tableClass.pkFields as field>
        <input type="hidden" name="${field.fieldName}"/>
            </#list>
        </#if>
        <#if tableClass.baseFields??>
            <#list tableClass.baseFields as field>
        <div class="layui-form-item">
            <label class="layui-form-label"><#if field.remarks??>${field.remarks}<#else>${field.fieldName}</#if></label>
            <div class="layui-input-block">
                <input type="text" name="${field.fieldName}" required  lay-verify="required" placeholder="请输入<#if field.remarks??>${field.remarks}<#else>${field.fieldName}</#if>" autocomplete="off" class="layui-input<#if field.jdbcDateColumn || field.jdbcTimeColumn> layui-date</#if>">
            </div>
        </div>
            </#list>
        </#if>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="${tableClass.lowerCaseName}">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<#if tableClass.lowerCaseName?ends_with("y")>
    <#assign names = tableClass.lowerCaseName[0..tableClass.lowerCaseName?length - 2] + 'ies'>
<#else>
    <#assign names = tableClass.lowerCaseName + 's'>
</#if>
<script src="${r"${pageContext.request.contextPath}"}/static/layui/layui.js"></script>
<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        var layer = layui.layer,
                form = layui.form,
                table = layui.table,
                laydate = layui.laydate,
                $ = layui.$;

        if($('.layui-date').length > 0){
            laydate.render({
                elem: '.layui-date'
            });
        }

        var ${tableClass.lowerCaseName}Table = (function () {
            var tableId = 'test';
            table.render({
                elem: '#' + tableId,
                url:'${r"${pageContext.request.contextPath}"}/${names}',
                cellMinWidth: 80 /*全局定义常规单元格的最小宽度，layui 2.2.1 新增*/,
                page: true,
                cols: [[
                    <#if tableClass.allFields??>
                        <#list tableClass.baseFields as field>
                    {field:'${field.fieldName}', title: '<#if field.remarks??>${field.remarks}<#else>${field.fieldName}</#if>'},
                        </#list>
                    </#if>
                    {fixed: 'right', width:178, align:'center', toolbar: '#${tableClass.lowerCaseName}Toolbar'}
                ]],
                request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'
                },
                response: {
                    statusName: 'success',
                    statusCode: true,
                    msgName: 'msg',
                    countName: 'total',
                    dataName: 'rows'
                }
            });

            //监听工具条
            table.on('tool', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        ${tableClass.lowerCaseName}Api.del(data.id, function () {
                            ${tableClass.lowerCaseName}Table.reload();
                        });
                        layer.close(index);
                    });
                } else if(obj.event === 'edit'){
                    ${tableClass.lowerCaseName}Dialog.open('编辑', data);
                }
            });

            return {
                reload: function(){
                    table.reload(tableId);
                }
            }
        })();

        form.on('submit(${tableClass.lowerCaseName})', function(data){
            ${tableClass.lowerCaseName}Api.save(data.field, function(){
                ${tableClass.lowerCaseName}Table.reload();
                ${tableClass.lowerCaseName}Dialog.close();
            });
            return false;
        });

        var ${tableClass.lowerCaseName}Api = (function(){
            return {
                save: function(data, callback){
                    $.ajax({
                        url: '${r"${pageContext.request.contextPath}"}/${names}/',
                        data: data,
                        type: 'POST',
                        success: function(response) {
                            if(response.success){
                                if(typeof(callback) == 'function'){
                                    callback();
                                }
                            } else {
                                layer.msg(response.msg);
                            }
                        }
                    });
                },
                del: function(id, callback){
                    $.ajax({
                        url: '${r"${pageContext.request.contextPath}"}/${names}/' + id,
                        type: 'DELETE',
                        success: function(response) {
                            if(response.success){
                                if(typeof(callback) == 'function'){
                                    callback();
                                }
                            } else {
                                layer.msg(response.msg);
                            }
                        }
                    });
                }
            }
        })();

        var ${tableClass.lowerCaseName}Dialog = (function(){
            var ${tableClass.lowerCaseName}DialogIndex;
            var ${tableClass.lowerCaseName}Form = $('#${tableClass.lowerCaseName}Form');
            return {
                open: function(title, data){
                    ${tableClass.lowerCaseName}Form.children('form')[0].reset();
                    ${tableClass.lowerCaseName}Form.find('input[type="hidden"]').val('');
                    ${tableClass.lowerCaseName}DialogIndex = layer.open({
                        type: 1,
                        title: title,
                        area: '600px',
                        content: ${tableClass.lowerCaseName}Form
                    });
                    if(data){
                        form.val("${tableClass.lowerCaseName}", data);
                    }
                },
                close: function(){
                    layer.close(${tableClass.lowerCaseName}DialogIndex);
                }
            }
        })();

        $('.layui-btn[lay-filter="${tableClass.lowerCaseName}Dialog"]').on('click', function(){
            ${tableClass.lowerCaseName}Dialog.open('新增');
        });

    });
</script>
</body>
</html>
