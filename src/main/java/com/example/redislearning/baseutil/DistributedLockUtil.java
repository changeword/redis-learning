package com.example.redislearning.baseutil;

public class DistributedLockUtil {

    public static boolean lock(String lockName) {
        System.out.println(Thread.currentThread() + "开始加锁");
        Long result = RedisPoolUtil.setnx(lockName, String.valueOf(System.currentTimeMillis() + 5000));
        if (result != null && result.intValue() == 1) {
            System.out.println(Thread.currentThread() + "加锁成功");
            RedisPoolUtil.expire(lockName, 5);
            System.out.println(Thread.currentThread() + "执行业务逻辑 ");
            RedisPoolUtil.del(lockName);
            return true;
        } else {
            String lockValueA = RedisPoolUtil.get(lockName);
            if (lockValueA != null && Long.parseLong(lockValueA) >= System.currentTimeMillis()) {
                String lockValueB = RedisPoolUtil.getSet(lockName, String.valueOf(System.currentTimeMillis() + 5000));
                if (lockValueB == null || lockValueB.equals(lockValueA)) {
                    System.out.println(Thread.currentThread() + "加锁成功");
                    RedisPoolUtil.expire(lockName, 5);
                    System.out.println(Thread.currentThread() + "执行业务逻辑");
                    RedisPoolUtil.del(lockName);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
