package com.boot.lea.mybot.component;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author LiJing
 * @ClassName: BiFunction
 * @Description: 测试函数接口
 * @date 2019/8/5 10:13
 */

@FunctionalInterface
public interface BiFunction<T, U, R> {

    /**
     * Applies this function to the given arguments.
     * 将此函数应用于给定的参数
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(T t, U u);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     * <p>
     * 返回第一个应用此函数的复合函数
     * 然后将{@code after}函数应用于结果。
     * 如果其中一个函数的计算引发异常，则将其传递给
     * 组合函数的调用者。
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }


    class FunctionTest {
        static void main(String[] args) {
            FunctionTest biFunctionTest2 = new FunctionTest();
            System.out.println(biFunctionTest2.compute(4, 5, (a, b) -> a * b, a -> a * 2));
        }

        int compute(int a, int b, BiFunction<Integer, Integer, Integer> biFunction,
                    Function<Integer, Integer> function) {
            return biFunction.andThen(function).apply(a, b);
        }
    }

}
