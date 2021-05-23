<%@ page import="or.gelivable.web.EnvUtils" %>
<%@ page import="or.gelivable.web.Env" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Env env = EnvUtils.getEnv();
    env.setRequest(request);
    String p = env.param("p", "");
    request.setAttribute("p", p);

%>

<html>
<head>
    <title>普通文件upload_quick.jsp(必须先通过passport3登录)</title>
    <script type="text/javascript" src="js/upload-1.1.js"></script>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

    <script type="text/javascript">
        function callback3(data, params) {
            var orgPicUrl = '';
            if (data.files) {
                for (var i in data.files) {
                    var file = data.files[i];
                    if (file.isorg == 1) {//是原图
                        var url = file.url;
                        var reg = /^.*_\d*x\d*\.\w*$/;
                        if (url && !reg.test(url)) {
                            orgPicUrl = url;
                        }
                    }
                }
            }
            $('#cover').val(orgPicUrl);
            $("#cover1to1Img").attr("src",orgPicUrl.replace("http:", "").replace("https:", ""));
            $("#cover1to1").show();
        }

        function upload() {
            var uploader_url = "http://t-upc.pconline.com.cn";
            var uploader_systemName = "pc_best";
            var uploader_commands = "1001,1003,1004,1010,1011,1012,1014,1017,1022,1023"; // 1:1封面
            // var uploader_commands = "1015,1020,1021";

            var picvalue = $("#uploadPic").val();
            var pos = picvalue.lastIndexOf(".");
            var lastname = picvalue.substring(pos, picvalue.length); //此处文件后缀名也可用数组方式获得str.split(".")

            var params = {
                application: uploader_systemName,
                keepSrc: 'yes'
            };

            if (lastname.toLowerCase() != ".gif") {
                params['watermark'] = '/data/pc-config/upc/watermark/pc_best.png'; //水印: 1:1封面有 & 2:1封面无
            }
            new Uploader({
                'url': uploader_url + '/upload_quick.jsp',
                'commands': uploader_commands,
                'success': 'callback3',
                'params': params,
                'files': [{'id': 'uploadPic'}]
            }).upload();
        }
    </script>

    <script type="text/javascript">

        $(function () {

            // 点击删除按钮
            $(".removeItem").click(function () {
                if ($("#edit_pics_body tr").size() > 1) {
                    $(this).closest("tr").remove();
                } else {
                    var tr = $(this).closest("tr");
                    tr.find("input").val("");
                    tr.find("img").attr("src", "");

                    // 删除图片相关id
                    tr.find("[name='uploadPic']").removeAttr("id");
                    tr.find("[tag='cover']").removeAttr("id");
                    tr.find("[tag='cover1to1Img']").removeAttr("id");
                    tr.find("[tag='cover1to1']").removeAttr("id");
                    tr.find("[tag='cover1to1']").attr("style", "width: 100%; height: auto;display: none");
                }
            });

            // 点击上传按钮
            $(".uploadItem").click(function () {
                var tr = $(this).closest("tr");

                // 图片相关id 更改
                $('#uploadPic').removeAttr("id");
                $('#cover').removeAttr("id");
                $('#cover1to1Img').removeAttr("id");
                $('#cover1to1').removeAttr("id");

                tr.find("[name='uploadPic']").attr("id", "uploadPic");
                tr.find("[tag='cover']").attr("id", "cover");
                tr.find("[tag='cover1to1Img']").attr("id", "cover1to1Img");
                tr.find("[tag='cover1to1']").attr("id", "cover1to1");

                // 上传
                upload();
            });

            $(".appendRow").click(function () {
                var tr = $("#edit_pics_body tr:first").clone(true);
                tr.find("input").val("");
                tr.find("img").attr("src", "");
                tr.appendTo("#edit_pics_body");

                // 删除图片相关id
                tr.find("[name='uploadPic']").removeAttr("id");
                tr.find("[tag='cover']").removeAttr("id");
                tr.find("[tag='cover1to1Img']").removeAttr("id");
                tr.find("[tag='cover1to1']").removeAttr("id");
                tr.find("[tag='cover1to1']").attr("style", "width: 100%; height: auto;display: none");
            });

            /*$("#editForm").submit(function () {
                $.each($("#edit_pics_body tr"),function (index,item) {
                    $(item).find("[tag='cover']").prop("name","covers["+index+"]");
                });
            });*/

            $(".btn_submit").click(function () {
                $("#editForm").submit();
            });

        });

    </script>
</head>
<body>${p}
<form action="/upc.do" method="post" id="editForm">
<div style="width: 100%; height: auto;">
    <tr style="width: 100%; height: auto;">
        <td>
            <input type="button" value="添加图片" class="ui_input_btn01 appendRow"/>
            <table class="edit_table" cellspacing="0" cellpadding="0" border="0">
                <tbody id="edit_pics_body">

                <c:if test="${empty picList}">
                    <tr>
                        <td>
                            <p>
                                <label>详情图：</label>
                                <input size="60" class="required url textInput" readonly="readonly" name="cover" tag="cover"/> &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="file" name="uploadPic"/>
                                <a href="javascript:;" class="uploadItem">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:;" class="removeItem">删除图片</a>
                            </p>

                            <p tag="cover1to1" style="width: 100%; height: auto;display: none">
                                <label>详情图预览：</label>
                                <img src="" tag="cover1to1Img" width="180px" height="180px" draggable="false"/>
                            </p>
                        </td>
                    </tr>
                </c:if>

                <!-- 新增 -->
                <c:forEach var="item" items="${picList}">
                <tr>
                    <td>
                        <p>
                            <label>详情图：</label>
                            <input size="60" class="required url textInput" readonly="readonly" name="cover" tag="cover" value="${item}"/> &nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="file" name="uploadPic"/>
                            <a href="javascript:;" class="uploadItem">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" class="removeItem">删除图片</a>
                        </p>

                        <p tag="cover1to1" style="width: 100%; height: auto;display: none">
                            <label>详情图预览：</label>
                            <img src="${item}" tag="cover1to1Img" width="180px" height="180px" draggable="false"/>
                        </p>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
</div>
<div>
    <input type="button" value="保存图片" class="ui_input_btn01 btn_submit" />
</div>
</form>

</body>
</html>
