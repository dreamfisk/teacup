package com.dreamfisk.teacup;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by Rick on 2017/2/25.
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<List<Integer>> f = es.submit(() -> {
                    Random r = new Random();
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < 100; ++i) {
                        list.add(r.nextInt() % 1000);
                    }
                    return list;
                }
        );
        List<Integer> i = null;
        try {
            i = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Integer> j = i.parallelStream().filter((Integer x) -> x > 0).collect(Collectors.toList());
        System.out.println(j);

    }
}
