
/**
 *
 * @param parent
 * @param html
 * @param dataArr
 */
function fillHtmlElement2(parent,div,dataArr,formats) {
    var html = div.html();
    fillHtmlElement(parent,html,dataArr,formats);
}
/**
 *
 * @param parent
 * @param html
 * @param dataArr
 */
function fillHtmlElement(parent,html,dataArr,formats) {

    if(parent === null || parent === undefined){
        return;
    }
    var re = /\$+[A-Za-z0-9.]+\$/g;
    var n=html.match(re);
    for(var i = 0; i < n.length;i++){
        var val= n[i].replace(/\$/g,"");
        var valArr = val.split(".");
        var value;
        for(var j = 0;j < valArr.length;j++){
            var minVal = valArr[j];
            if(j === 0){
                value = dataArr[minVal];
            }else{
                value = value[minVal];
            }
        }
        if(formats !== null && formats !== undefined){
            if($.isArray(formats)){
                for(var v = 0; v < formats.length;v++){
                    var format = formats[v];
                    if(format !== null){
                        if(val === format.key){
                           value = formatValue(format,value);
                            break;
                        }
                    }
                }
            }
        }

        html=html.replace(n[i],value);
    }
    console.log(html);
    parent.append(html);
}

/**
 * 填充表格
 * @param tab 表格
 * @param arr 匹配规则
 * @param dataArr 数据
 */
function fillBinTab(tab,arr,dataArr){
    fillTabClass(tab,null,arr,dataArr,null);
}

/**
 * 填充表格
 * @param tab 表格
 * @param caption 表格主题
 * @param arr 匹配规则
 * @param dataArr 数据
 * @param clazz 表格Class
 */
function fillTabClass(tab,caption,arr,dataArr,clazz) {
    var tabAppend = "";
    var tableName = tab.get(0).tagName;
    if(tableName !== "TABLE"){
        return;
    }
    if(clazz !== null && clazz !== undefined){
        if($.isArray(clazz)){
            for(var c =0; c<clazz.length;c++){
                tab.addClass(clazz[c]);
            }
        }else{
            tab.addClass(clazz);
        }
    }
    if(caption !== null && caption !== undefined ){
        tabAppend +="<caption>"+caption+"</caption>";
    }
    if($.isArray(arr)){
        tabAppend +="<thead><tr>";
        for(var i = 0;i <arr.length;i++){
            var row = arr[i];
            var name = row.name;
            tabAppend +="<th>"+name+"</th>";
        }
        tabAppend +="</tr></thead><tbody>";
        if($.isArray(dataArr)){
            for(var d = 0; d <dataArr.length;d++){
                tabAppend +="<tr>";
                var data = dataArr[d];
                    for(var j =0; j <arr.length;j++){
                        var column = arr[j];
                        var key = column.key;
                        for (items in data){
                            if(items === key){
                                var value = data[items];
                                tabAppend +="<td>"+getElementByTag(column,value)+"</td>";
                                break;
                            }
                        }
                    }
                tabAppend +="</tr>";
            }
        }
        tabAppend +="</tbody>";
    }

    tab.append(tabAppend);
}



/**
 * 拼接表格
 * @param column
 * @param value
 * @returns {*}
 */
function getElementByTag(column,value) {
    var tag = column.type;
    var formatVal = formatValue(column,value);
    var str = formatVal;
    if(tag !== null && tag !== undefined){
        str =  "<"+tag+" ";
        if(column.click !== null && column.click!== undefined){
            str += " onClick=\""+column.click +"\" ";
        }
        if(column.style !== null && column.style!== undefined){
            str += " style=\""+column.style +"\" ";
        }
        if(column.class !== null && column.class!== undefined){
            str += " class=\""+column.class +"\" ";
        }
        if(tag === "img"||tag === "IMG"){
            str +=" src=\""+formatVal +"\" >";
        }else{
            str +=  ">"+formatVal;
        }
        str +=  "</" + tag +">";
    }
    return str;
}

function formatValue(column,value) {

    if(column.format === null || column.format === undefined){
        return value;
    }else{
        return column.format(value);
    }

}


