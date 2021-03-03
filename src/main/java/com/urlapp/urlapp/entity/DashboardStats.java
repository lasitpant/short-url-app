package com.urlapp.urlapp.entity;
import java.io.Serializable;
public class DashboardStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private Long total;

    public DashboardStats(String name, Long total) {
        super();
        this.name= name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
