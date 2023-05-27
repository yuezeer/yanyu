package com.xiaomi.webproject.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Author: Andy
 * Date: 2022/4/20
 * Description:
 */
@SuppressWarnings("all")
public class PageResult<T> {

    private int pages;//总页数
    private int pageSize;//每页记录数
    private int total;//总记录数
    private int currentPage;//当前页
    private int prevPage;//上一页
    private int firstPage;//首页
    private int lastPage;//尾页
    private int nextPage;//下一页
    private boolean isFirst;//是否是第一页
    private boolean isLast;//是否是最后一页
    private List<T> data;//每页数据
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    private Class<T> clazz;//泛型类字节码类型
    private Map<String, Object> params;//查询参数
    private String sql;//查询sql
    private String url;//客户请求地址
    private StringBuffer pagination;

    public PageResult(int currentPage, int pageSize, Class<T> clazz, String sql, Map<String, Object> params) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.clazz = clazz;
        this.params = params;
        this.sql = sql;

        //初始化数据
        this.data = getData();
        //上一页
        prevPage = getPrevPage();
        //下一页
        nextPage = getNextPage();
        //首页
        firstPage = getFirstPage();
        //是否是第一页
        isFirst = isFirst();
        //是否是最后一页
        isLast = isLast();

        setPagination();
    }

    public void setPagination() {
        this.pagination = new StringBuffer(getPagination());
    }

    //返回BootStrap分页条
    public String getPagination() {
        pagination = new StringBuffer();
        pagination.append("<nav aria-label=Page navigation>" +
                "  <ul class=pagination>");

        //没有查询参数
        if (params.size() == 0) {
            //拼接首页
            if (currentPage == 1) {
                prevPage = 1;
                pagination.append("<li class=disabled>" +
                        "      <a href=" + url + "&page=" + prevPage + " aria-label=Previous>" +
                        "        <span aria-hidden=true>&laquo;</span>" +
                        "      </a>" +
                        "    </li>");
            } else {
                pagination.append("<li>" +
                        "      <a href=" + url + "&page=" + prevPage + " aria-label=Previous>" +
                        "        <span aria-hidden=true>&laquo;</span>" +
                        "      </a>" +
                        "    </li>");
            }
            //中间页码
            for (int i = 1; i <= pages; i++) {
                if (currentPage == i) {
                    pagination.append("<li class=active><a href=" + url + "&page=" + i + ">" + i +
                            "</a></li>");
                } else {
                    pagination.append("<li><a href=" + url + "&page=" + i + ">" + i +
                            "</a></li>");
                }
            }

            //拼接尾页
            if (currentPage == pages) {
                nextPage = pages;
                pagination.append("<li class=disabled>" +
                        "      <a href=" + url + "&page=" + nextPage + " aria-label=Next>" +
                        "        <span aria-hidden=true>&raquo;</span>" +
                        "      </a>" +
                        "    </li>");
            } else {
                pagination.append("<li>" +
                        "      <a href=" + url + "&page=" + nextPage + " aria-label=Next>" +
                        "        <span aria-hidden=true>&raquo;</span>" +
                        "      </a>" +
                        "    </li>");
            }

        } else {
            //拼接首页
            if (currentPage == 1) {
                prevPage = 1;
                //首页拼接参数
                pagination.append("<li class=disabled>" +
                        "<a href=" + url + "&page=" + prevPage);

            } else {

                pagination.append("<li>" +
                        "<a href=" + url + "&page=" + prevPage);
            }
            //首页拼接参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                pagination.append("&" + entry.getKey() + "=" + entry.getValue());
            }

            pagination.append(" aria-label=Previous>" +
                    "        <span aria-hidden=true>&laquo;</span>" +
                    "      </a>" +
                    "    </li>");

            //拼接中间页码
            for (int i = 1; i <= pages; i++) {
                if (currentPage == i) {
                    pagination.append("<li class=active>" +
                            "<a href=" + url + "&page=" + i);
                } else {
                    pagination.append("<li>" +
                            "<a href=" + url + "&page=" + i);
                }
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    pagination.append("&" + entry.getKey() + "=" + entry.getValue());
                }

                pagination.append(">" + i + "</a></li>");

            }
            //拼接尾页
            if (currentPage == pages) {
                //禁用
                nextPage = pages;
                pagination.append("<li class=disabled>" +
                        "<a href=" + url + "&page=" + nextPage);

            } else {
                pagination.append("<li>" +
                        "<a href=" + url + "&page=" + nextPage);
            }
            //尾页拼接参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                pagination.append("&" + entry.getKey() + "=" + entry.getValue());
            }

            pagination.append(" aria-label=Next>" +
                    "        <span aria-hidden=true>&raquo;</span>" +
                    "      </a>" +
                    "    </li>");
        }

        //去掉模糊查询的%号
        return pagination.toString().replace("%", "");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getData() {
        Object[] objects = null;
        //把Map数据转换成Object数组
        if (params != null) {
            objects = params.values().toArray();
        }
        try {
            data = qr.query(sql, new BeanListHandler<>(clazz), objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean isFirst() {
        return currentPage == 1;
    }

    public boolean isLast() {
        return currentPage == pages;
    }

    public int getPrevPage() {
        if (currentPage == 1) {
            return 1;
        }
        return prevPage = currentPage - 1;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getFirstPage() {
        return 1;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return getPages();
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        if (currentPage == pages) {
            return pages;
        }
        return currentPage + 1;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPages() {
        this.pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        //尾页
        lastPage = pages;
        return pages;
    }

    public int getTotal() {
        return total;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotal(int total) {
        this.total = total;
        //初始化总页数
        this.pages = getPages();
    }

    public String toString() {
        return getPagination();
    }
}
