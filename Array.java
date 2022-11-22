package com.training.accolite.week1;

import java.util.*;
import java.io.*;
import java.math.*;

public class Array {
    private final Scanner sc;





    public Array() {
        this(new Scanner(System.in));
    }

    public Array(Scanner sc) {
        this.sc = sc;
    }

    public static void main(String[] args) {
        new Array().run();
    }

    public int run() {
        int result = solve();
        System.out.println(result);
        return result;
    }


    int solve() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[2 * n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            a[i + n] = a[i];
        }

        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        HashMap<Integer, Integer> bIndex = new HashMap<>();
        for (int i = 0; i < m; i++) {
            bIndex.put(b[i], i);
        }

        for (int lenght = n; lenght > 0; lenght--) {
            for (int offset = 0; offset < n; offset++) {
                int[] substring = Arrays.copyOfRange(a, offset, offset + lenght);
                boolean isSubsequence = true;
                boolean wrappedAround = false;
                int indexOfLastGlyph = -1;
                int indexOfFirstGlyph = -1;
                for (int glyph : substring) {
                    if (bIndex.containsKey(glyph)) {
                        if (bIndex.get(glyph) > indexOfLastGlyph) {
                            indexOfLastGlyph = bIndex.get(glyph);

                            if (indexOfFirstGlyph == -1) indexOfFirstGlyph = bIndex.get(glyph);

                            if (wrappedAround && bIndex.get(glyph) > indexOfFirstGlyph) {
                                isSubsequence = false;
                                break;
                            }
                        } else {

                            if (!wrappedAround && bIndex.get(glyph) < indexOfFirstGlyph) {
                                wrappedAround = true;
                            } else {
                                isSubsequence = false;
                                break;
                            }
                        }
                    } else {

                        isSubsequence = false;
                        break;
                    }
                }

                if (isSubsequence) return lenght;
            }
        }
        return 0;
    }

}