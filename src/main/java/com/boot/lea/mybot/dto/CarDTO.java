package com.boot.lea.mybot.dto;


import com.boot.lea.mybot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author LiJing
 * @ClassName: CarDTO
 * @Description: 小车DTO
 * @date 2019/7/31 15:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO implements Serializable {

    /**
     * 制造商
     */
    @NotNull
    private String manufacturer;

    /**
     * 汽车牌照
     */
    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;

    /**
     * 座位数
     */
    @Min(2)
    private int seatCount;

    private List<User> passengers;


    public CarDTO(String manufacturer, String licensePlate, int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
    }
}
