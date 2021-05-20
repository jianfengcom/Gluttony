<%@ page import="or.gelivable.web.EnvUtils" %>
<%@ page import="or.gelivable.web.Env" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <script>
        function callback3(data, params) {
            var orgPicUrl = '';
            if(data.files) {
                for(var i in data.files) {
                    var file = data.files[i];
                    if(file.isorg == 1){//是原图
                        var url = file.url;
                        var reg = /^.*_\d*x\d*\.\w*$/;
                        if(url && !reg.test(url)){
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
            var lastname = picvalue.substring(pos,picvalue.length); //此处文件后缀名也可用数组方式获得str.split(".")

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
</head>
<body>${p}
    <div>
        <p style="width: 100%; height: auto;">
            <label>1:1封面：</label>
            <input size="60" class="required url textInput" readonly="readonly" name="cover" id="cover" /> &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="file" id="uploadPic" name="uploadPic" />
            <input type="button" value="上传" onclick="upload();" />
        </p>

        <p id="cover1to1" style="width: 100%; height: auto;display: none">
            <label>1:1封面预览：</label>
            <img id="cover1to1Img" src="" width="180px" height="180px" draggable="false" />
        </p>
    </div>

</body>
</html>
