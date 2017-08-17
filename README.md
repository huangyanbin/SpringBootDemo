
# Spring Boot 示例
------
一个用于练手Spring boot 的demo.

> * 生成json数据，增加Result结果封装
> * 分别使用了Hibernate，mybatis。数据库mysql
> * 全局拦截，过滤
> * redius 缓存
> * 使用freemaker编写网页
> * 尝试写了table.js，根据json返回数据自动生成bootStrap样式表格
根据返回数据自动填充div。

------

## 如何自动生成表格
```js
fillTabClass($("#ArtTable"),"文章列表",[
                            {   name:"ID",
                                key:"id"
                            },
                            {   name:"图标",
                                key:"icon",
                                type:"img",
                                style:"width:40px;height:40px;",class:"img-circle"},
                            {   name:"主题",
                                key:"title",
                                type:"span"
                            },
                            {name:"时间",
                                key:"createTime",
                                format:function (time) {
                                        return timeFormat(time);
                                }
                            }
                ],result.data,"table-bordered");
```
## 如何自动填充数据
```html
<!--自动拼接数据-->
<huang:custom id="articleCustom" style='display:none'>
    <div class='col-md-12 articleItem'>
        <div>
            <img src=$icon$ width='50px' height='50px'/></div>
        <div class='col-md-10'>
            <p>$title$</p>
            <p> <small >$content$</small></p>
            <div class='col-md-11'>
                <div>
                    <img src=$user.icon$ class='img-circle' width='25px' height='25px'/></div>
                <small >$user.nickName$</small></div>
            <p><small>$createTime$</small><p> <hr/></div> </span>
    </div>
</huang:custom>
```
```js
 fillHtmlElement2($("#articleDiv"),$("#articleCustom"),article,
        [
            {   key:"createTime",
                format:function(time) {
                        return timeFormat(time);
                        }
            }
        ]
);
```
###  待完善 

- [ ] 自动填充数据Div不够完善.
- [ ] 自动生成表格可选项较少。





