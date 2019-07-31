package com.boot.lea.mybot;

import com.boot.lea.mybot.dto.CarDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybotApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * 1.在setUp()方法中,我们通过ValidatorFactory得到了一个Validator的实例.
     * Validator是线程安全的,并且可以重复使用, 所以我们把它保存成一个类变量.
     * 现在我们可以在test方法中使用这个validator的实例来校验不同的carDTO实例了.
     *
     * 2.validate()方法会返回一个set的ConstraintViolation的实例的集合,
     * 我们可以通过遍历它来查看有哪些验证错误. 前面三个测试用例显示了一些预期的校验约束:
     *
     * ①在manufacturerIsNull()中可以看到manufacturer违反了@NotNull约束
     *
     * ②.licensePlateTooShort()中的licensePlate违反了@Size约束
     *
     * ③.而seatCountTooLow()中则导致seatCount违反了@Min约束
     *
     * 如果一个对象没有校验出问题的话,那么validate() 会返回一个空的set对象.
     *
     * 注意,我们只使用了Bean Validation API中的package javax.validation中的类, 并没有直接调用参考实现中的任何类,所以, 没有任何问题如果切换到其他的实现. */
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        CarDTO car = new CarDTO(null, "DD-AB-123", 4);

        Set<ConstraintViolation<CarDTO>> constraintViolations =
                validator.validate(car);

        assertEquals(1, constraintViolations.size());
        assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void licensePlateTooShort() {
        CarDTO car = new CarDTO("Morris", "D", 4);

        Set<ConstraintViolation<CarDTO>> constraintViolations =
                validator.validate(car);

        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 2 and 14", constraintViolations.iterator().next().getMessage());

    }

    @Test
    public void seatCountTooLow() {
        CarDTO car = new CarDTO("Morris", "DD-AB-123", 1);

        Set<ConstraintViolation<CarDTO>> constraintViolations =
                validator.validate(car);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be greater than or equal to 2", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void carIsValid() {
        CarDTO car = new CarDTO("Morris", "DD-AB-123", 2);

        Set<ConstraintViolation<CarDTO>> constraintViolations =
                validator.validate(car);

        assertEquals(0, constraintViolations.size());
    }

}
