package com.boot.lea.mybot.dto;


import com.boot.lea.mybot.dto.assist.Create;
import com.boot.lea.mybot.dto.assist.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LiJing
 * @ClassName: UserDTO
 * @Description: 用户传输对象
 * @date 2019/7/30 13:55
 */
@Data
@ApiModel("用户DTO")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 用户ID*/
    @ApiModelProperty(value = "用户ID", example = "88", required = true, dataType = "int")
    @NotNull(message = "{1}不能为空", groups = Update.class)
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "王麻子", required = true, dataType = "string")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符", groups = {Create.class, Update.class})
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = {Create.class, Update.class})
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;

    /**
     * 密码
     */
    private String password;

    /*** 创建时间 */
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private Date createTime;

}