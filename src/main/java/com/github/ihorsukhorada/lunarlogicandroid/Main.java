package com.github.ihorsukhorada.lunarlogicandroid;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if(args.length != 3) throw new IllegalArgumentException();

        final int[] changesLeft = {6};

        int[] numbers = Arrays.stream(args)
                .mapToInt(Integer::parseInt).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue).map(number -> {
                    if(number % 3 != 0) {
                        int[] digits = Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::valueOf).toArray();
                        int sumOfDigits = Arrays.stream(digits).sum();
                        int changesToNormalize = (((sumOfDigits / 3) + 1) * 3) - sumOfDigits;
                        changesLeft[0] =- changesToNormalize;

                        for (int i = 0; i < digits.length; i++) {
                            if((digits[i] + changesToNormalize) <= 9) {
                                digits[i] += changesToNormalize;
                                break;
                            }
                        }

                        number = Integer.parseInt(Arrays.stream(digits).boxed().map(String::valueOf).collect(Collectors.joining("")));
                    }

                    return number;
                }).toArray();

        System.out.println(Arrays.toString(numbers));
    }
}
