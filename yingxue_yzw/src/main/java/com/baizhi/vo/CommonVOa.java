package com.baizhi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonVOa {
    private String message;
    private Object status;

    public static CommonVOa success(String message) {
        CommonVOa oa = new CommonVOa();
        oa.setMessage(message);
        oa.setStatus(200);

        return oa;
    }
    public static CommonVOa fei() {
        CommonVOa oa = new CommonVOa();
        oa.setMessage("操作失败!!!");
        oa.setStatus(400);
        return oa;
    }
}
