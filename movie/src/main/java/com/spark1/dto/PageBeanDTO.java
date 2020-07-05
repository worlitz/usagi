package com.spark1.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageBeanDTO<T> {
    /*
     * 当前页
     * */
    private Integer currentPage;
    /*
     * 总页数
     * */
    private Integer totalPages;
    /*
     * 每页的数量
     * */
    private Integer pageSize;
    /*
     * 数据类型
     * */
    private List<T> rows;
    private Integer sum;
}
