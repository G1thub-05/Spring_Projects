package in.digeshwar.controller;

import in.digeshwar.service.DatabaseService;
import in.digeshwar.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class DataController {

    private final RedisService redisService;
    private final DatabaseService databaseService;

    public DataController(RedisService redisService,
                          DatabaseService databaseService) {
        this.redisService = redisService;
        this.databaseService = databaseService;
    }

    @GetMapping("/data")
    public CompletableFuture<String> getData() {

        return redisService.getFromRedis()
                .handle((result, ex) -> {
                    if (ex != null || "REDIS_FAILED".equals(result)) {
                        return databaseService.getFromDatabase();
                    }
                    return CompletableFuture.completedFuture(result);
                })
                .thenCompose(f -> f);
    }
}
