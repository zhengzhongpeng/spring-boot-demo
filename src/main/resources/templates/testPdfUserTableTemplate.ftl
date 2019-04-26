<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        @page  {
            size: A4;
        }
        html{
            font-family: SimSun;
            font-size: 18px;
        }
        .t{
            text-align: 2em;
        }
        font{
            color: brown;
        }
        .div_img{
            width: auto;
            height: 500px;
            background: url("http://img3.duitang.com/uploads/item/201508/13/20150813212158_Rhcsd.jpeg")   no-repeat ;
        }
    </style>
</head>
<body>

    <p>Hello Dear:</p>
    <br/>
    <p class="t">&nbsp;&nbsp;&nbsp;&nbsp;这是一封<font > SpringBoot Mail FreeMarker </font>邮件测试。如有收到此邮件，请勿回复。谢谢！</p>
    <p class="t">当前时间</p>
    <div>
        <table >
            <tr>
                <td>ID</td>
                <td>姓名</td>
                <td>别名</td>
                <td>电话</td>
                <td>邮箱</td>
            </tr>
            <#list userList as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.alias}</td>
                    <td>${user.phone}</td>
                    <td>${user.email}</td>
                </tr>
            </#list>
        </table>
    </div>
    <div class="div_img">
    </div>
    <p class="t">如有疑问请联系 ：zhengzhongpeng@163.com</p>
    <div class="div_img"></div>
    <div class="div_img"></div>
</body>
</html>