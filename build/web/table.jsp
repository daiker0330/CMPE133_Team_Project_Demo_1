<%@page import="java.util.List"%>
<%@page import="Lab6.Course"%>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Courses</title>
        <meta name="description" content="table">
        <meta name="keywords" content="table">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="renderer" content="webkit">
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="icon" type="image/png" href="assets/i/favicon.png">
        <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
        <meta name="apple-mobile-web-app-title" content="Amaze UI" />
        <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
        <link rel="stylesheet" href="assets/css/admin.css">
    </head>
    <body>

        <div class="am-cf admin-main">

            <!-- content start -->
            <div class="admin-content">

                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">Course List</strong></div>
                </div>

                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                    <tr>
                                        <th class="table-id">ID</th>
                                        <th class="table-title">Name</th>
                                        <th class="table-type">Department</th>
                                        <th class="table-type">Time</th>
                                        <th class="table-set">Operation</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List courses = null;

                                        courses = (List) request.getAttribute("course");
                                        if (courses != null) {
                                            for (int i = 0; i < courses.size(); i++) {
                                                Course c = (Course) courses.get(i);
                                    %>
                                    <tr>
                                        <td><%=c.getNumber()%></td>
                                        <td><a href="#"><%=c.getName()%></a></td>
                                        <td><%=c.getDep()%></td>
                                        <td><%=c.getTime()%></td>
                                        <td>
                                            <div class="am-btn-toolbar">
                                                <div class="am-btn-group am-btn-group-xs">
                                                    <form action="MyServlet" method="get">
                                                        <input type="hidden" name="func" value="enroll">
                                                        <input type="hidden" name="id" value="<%=c.getNumber()%>">
                                                        <input type="hidden" name="name" value="<%=c.getName()%>">
                                                        <input type="hidden" name="description" value="<%=c.toString()%>">
                                                        <input type="submit" name="" value="enroll">
                                                    </form>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <%}
                }%>
                                </tbody>
                            </table>
                            <hr />
                        </form>
                    </div>

                </div>
            </div>
            <!-- content end -->
        </div>

        <a class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

        <footer>
            <hr>
        </footer>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/amazeui.min.js"></script>

        <script src="assets/js/app.js"></script>
    </body>
</html>
