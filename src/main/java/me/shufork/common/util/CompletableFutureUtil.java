package me.shufork.common.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public abstract class CompletableFutureUtil {

    public static <T> CompletableFuture<Void> allOf(List<CompletableFuture<T>> futures){
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
    }
    /*
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
    }
    */
}
