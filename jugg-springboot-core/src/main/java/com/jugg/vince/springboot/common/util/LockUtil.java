package com.jugg.vince.springboot.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jugg.vince.springboot.common.redis.RedisService;
import com.jugg.vince.springboot.demo.model.UsersModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Author:   Vince
 * Date:     2018/7/25 上午11:01
 * Description:
 */
@Slf4j
public class LockUtil {

    private static RedisService redisService = (RedisService)SpringUtil.getBean("redisService");

    public static Object get(String key) {
        return redisService.get(key);
    }

    public static void set(String key, Object o) {
        redisService.set(key, o);
    }

    public static void remove(String key) {
        redisService.del(key);
    }

    private LockUtil() {}

    /**
     * data lock
     * @param bill
     * @param seconds
     * @param <T>
     * @return
     */
    public synchronized static <T> T lockBill(T bill, long seconds) {
        try {
            if (bill != null) {
                String lock = getLockKey(bill);
                Object value = redisService.get(lock);
                if (value != null) {
                    log.info("data has been locked before,key:{}", lock);
                    return null;
                }
                if (redisService.setnx(lock, "", seconds)) {
                    log.info("data has been locked,key:{}", lock);
                    return bill;
                }
            }
        } catch (Exception e) {
            log.error("data locked error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * data unlock
     * @param bill
     */
    public synchronized static <T> void unLockBill(T bill) {
        try {
            if (bill != null) {
                String lock = getLockKey(bill);
                Object value = redisService.get(lock);
                if (value != null) {
                    redisService.del(lock);
                }
                log.info("data has been unlocked,key:{}", lock);
            }
        } catch (Exception e) {
            log.error("data unlocked error:{}", e.getMessage());
        }
    }

    /**
     * data lock
     * @param bill
     * @return
     */
    public synchronized static <T> T lockBill(T bill) {
        try {
            if (bill != null) {
                String lock = getLockKey(bill);
                Object value = redisService.get(lock);
                if (value != null) {
                    log.info("data has been locked before,key:{}", lock);
                    return null;
                }
                if (redisService.setnx(lock, "", 0L)) {
                    log.info("data has been locked,key:{}", lock);
                    return bill;
                }
            }
        } catch (Exception e) {
            log.error("data locked error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * datas unlock
     * @param rbList
     */
    public static void unLockBills(List<?> rbList) {
        try {
            if (null != rbList) {
                Iterator<?> it = (Iterator<?>) rbList.iterator();
                while (it.hasNext()) {
                    Object rb = it.next();
                    String lock = getLockKey(rb);
                    Object value = redisService.get(lock);
                    if (value != null) {
                        redisService.del(lock);
                    }
                }
                log.info("success to unlock size:{}", rbList.size());
            }
        } catch (Exception e) {
            log.error("datas unlocked error:{}", e.getMessage());
        }
    }

    /**
     * datas lock
     * @param rbList
     * @return
     */
    public synchronized static <T> List<T> lockBills(List<T> rbList) {
        try {
            if (null != rbList) {
                List<T> result = new ArrayList<>();
                Iterator<T> it = (Iterator<T>) rbList.iterator();
                while (it.hasNext()) {
                    T rb = it.next();
                    String lock = getLockKey(rb);
                    Object value = redisService.get(lock);
                    if (value != null) {
                        log.info("data has been locked before,key:{}", lock);
                        continue;
                    }
                    if (redisService.setnx(lock, "", 0L)) {
                        result.add(rb);
                        log.info("data has been locked,key:{}", lock);
                    }
                }
                log.info("need to lock total:{}, success to lock size:{}", rbList.size(), result.size());
                return result;
            }
        } catch (Exception e) {
            log.error("datas lock error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * getLockKey
     * @param o
     * @return
     * @throws Exception
     */
    public static String getLockKey(Object o) throws Exception {
        String lockKey = "jugg.vince.lock.";
        if (o instanceof UsersModel) {
            Object id = PropertyUtils.getProperty(o, "id");
            String key = String.valueOf(id);
            lockKey = "jugg.vince.lock.demo.users." + key;
        }
        return lockKey;
    }
}
