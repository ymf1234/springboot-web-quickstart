package cn.yumengfei.cases.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total; // 总记录数
    private List rows;
}
