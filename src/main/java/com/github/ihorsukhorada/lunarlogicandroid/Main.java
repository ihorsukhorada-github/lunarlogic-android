package com.github.ihorsukhorada.lunarlogicandroid;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static int[] getDigitsArray(int number) {
        return Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::valueOf).toArray();
    }

    public static int getSumOfNumbers(int[] array) {
        return Arrays.stream(array).sum();
    }

    public static int getChangesToNormalize(int sumOfDigits) {
        return (((sumOfDigits / 3) + 1) * 3) - sumOfDigits;
    }

    public static int concatNumbersOfArray(int[] array) {
        return Integer.parseInt(Arrays.stream(array).boxed().map(String::valueOf).collect(Collectors.joining("")));
    }

    public static void main(String[] args) {
        if(args.length != 3) throw new IllegalArgumentException();

        final int[] changesLeft = {6};

        int[] numbers = Arrays.stream(args)
                .mapToInt(Integer::parseInt).boxed()
                .mapToInt(Integer::intValue).map(number -> {
                    if(number % 3 != 0) {
                        int[] digits = getDigitsArray(number);
                        int changesToNormalize = getChangesToNormalize(getSumOfNumbers(digits));
                        changesLeft[0] =- changesToNormalize;

                        for (int i = 0; i < digits.length; i++) {
                            if((digits[i] + changesToNormalize) <= 9) {
                                digits[i] += changesToNormalize;
                                break;
                            }
                        }

                        number = concatNumbersOfArray(digits);
                    }

                    return number;
                }).toArray();

        System.out.println(Arrays.toString(numbers));
    }
}
